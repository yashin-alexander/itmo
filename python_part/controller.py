import simpy
import numpy
import progressbar

from constants import (MONSTERS_NUMBER,
                       TIMEZONES_NUMBER,
                       VISITING_HOURS,
                       TIMID_CHILDREN,
                       TIMEZONES_POPULATION,
                       HOUR,
                       DURATION_OF_FRIGHT)

queue_length = 0
monsters_stuck = 0
doors_stuck = 0
processed_doors = 0

bar = progressbar.ProgressBar(widgets=[
    progressbar.Timer(), ' ',
    progressbar.AnimatedMarker(markers='<3 '),
    progressbar.AnimatedMarker(markers='3 <'),
    progressbar.AnimatedMarker(markers=' <3'),
    progressbar.AnimatedMarker(markers='<3 '),
    progressbar.AnimatedMarker(markers='3 <'),
    progressbar.AnimatedMarker(markers=' <3'),
    progressbar.AnimatedMarker(markers='<3 '),
    progressbar.AnimatedMarker(markers='3 <'),
    progressbar.AnimatedMarker(markers=' <3'),

    progressbar.Bar(),
    progressbar.ETA()
])


class ChildDoor(object):
    @staticmethod
    def run():
        global queue_length, monsters_stuck, doors_stuck, processed_doors
        queue_length += 1
        with scenario_one.request() as request:  # SMO 1
            yield request
            queue_length -= 1

            if (processed_doors % round((sum/100), 0)) == 0:
                bar.update(round(processed_doors/sum*100), 0)

            brave = (numpy.random.randint(0, high=100) > TIMID_CHILDREN)

            if not brave:
                service_time = numpy.random.uniform(DURATION_OF_FRIGHT[0], DURATION_OF_FRIGHT[1])
                processed_doors += 1
                yield env.timeout(service_time)
            else:
                with brave_child.request() as brave_child_request:  # SMO 2
                    doors_stuck += 1
                    yield brave_child_request
                    doors_stuck -= 1
                    monsters_stuck += 1
                    service_time = HOUR*VISITING_HOURS[0][0]
                    yield env.timeout(service_time)


def source_doors(env):
    ind = 0
    while env.now < (HOUR*VISITING_HOURS[0][0]):
        ind += 1
        doors_number = TIMEZONES_POPULATION[int(env.now/HOUR)]

        for _ in range(doors_number):
            env.process(ChildDoor.run())
        yield env.timeout(HOUR)


sum = 0
for i in range(TIMEZONES_NUMBER[0][0]):
    sum += TIMEZONES_POPULATION[i]
print(sum)

env = simpy.Environment()
scenario_one = simpy.Resource(env, capacity=MONSTERS_NUMBER[0][0])
brave_child = simpy.Resource(env, capacity=MONSTERS_NUMBER[0][0])

env.process(source_doors(env))
env.run(until=HOUR*VISITING_HOURS[0][0])
print("\nNot processed doors {}. Monsters stuck {}. Processed doors {}"
      .format(queue_length + doors_stuck, monsters_stuck, processed_doors))
