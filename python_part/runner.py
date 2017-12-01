import simpy
import numpy
import progressbar

from constants import (MONSTERS_NUMBER,
                       TIMEZONES_NUMBER,
                       VISITING_HOURS,
                       TIMID_CHILDREN,
                       TIMEZONES_POPULATION,
                       TIME_DELAY,
                       HOUR,
                       STUCK_TIME,
                       DURATION_OF_FRIGHT)

bar = progressbar.ProgressBar(widgets=[
    progressbar.Timer(),
    progressbar.AnimatedMarker(markers='<3 '),
    progressbar.AnimatedMarker(markers='3 <'),
    progressbar.AnimatedMarker(markers=' <3'),
    progressbar.AnimatedMarker(markers='<3 '),
    progressbar.AnimatedMarker(markers='3 <'),
    progressbar.AnimatedMarker(markers=' <3'),
    progressbar.AnimatedMarker(markers='<3 '),
    progressbar.AnimatedMarker(markers='3 <'),
    progressbar.AnimatedMarker(markers=' <3'),

    progressbar.Bar()
])


class Runner:
    def __init__(self):
        self.total_timezones_population = 0
        self.queue_length = 0
        self.monsters_stuck = 0
        self.doors_stuck = 0
        self.processed_doors = 0
        self.env = simpy.Environment()
        self.scenario_num = 0
        self.experiment_num = 0

    def execute(self, scenario_num, experiment_num):
        self.scenario_one = simpy.Resource(self.env, capacity=MONSTERS_NUMBER[scenario_num][experiment_num])
        self.brave_child = simpy.Resource(self.env, capacity=MONSTERS_NUMBER[scenario_num][experiment_num])

        for i in range(TIMEZONES_NUMBER[scenario_num]):
            self.total_timezones_population += TIMEZONES_POPULATION[i+TIME_DELAY]

        self.env.process(self.source_doors(self.env))
        self.env.run(until=HOUR*VISITING_HOURS[scenario_num])
        print("\nNot processed doors {}. Monsters stuck {}. Processed doors {}"
              .format(self.queue_length + self.doors_stuck, self.monsters_stuck, self.processed_doors))

        print("Monster productivity {} %/per monster"
              .format(self.processed_doors/(self.total_timezones_population*MONSTERS_NUMBER[scenario_num][experiment_num])))

    def source_doors(self, env):
        ind = 0
        while env.now < (HOUR * VISITING_HOURS[self.scenario_num]):
            ind += 1
            doors_number = TIMEZONES_POPULATION[int(env.now / HOUR)]

            for _ in range(doors_number):
                env.process(self.run())
            yield env.timeout(HOUR)

    def run(self):
        self.queue_length += 1
        with self.scenario_one.request() as request:  # SMO 1
            yield request
            self.queue_length -= 1

            if (self.processed_doors % round((self.total_timezones_population/100), 0)) == 0:
                bar.update(round(self.processed_doors / self.total_timezones_population * 100), 0)

            brave = (numpy.random.randint(0, high=100) > TIMID_CHILDREN[self.scenario_num][self.experiment_num])

            if not brave:
                service_time = numpy.random.uniform(DURATION_OF_FRIGHT[0], DURATION_OF_FRIGHT[1])
                self.processed_doors += 1
                yield self.env.timeout(service_time)
            else:
                with self.brave_child.request() as brave_child_request:  # SMO 2
                    self.doors_stuck += 1
                    yield brave_child_request
                    self.doors_stuck -= 1
                    self.monsters_stuck += 1
                    service_time = STUCK_TIME
                    yield self.env.timeout(service_time)
