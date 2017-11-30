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


class ChildDoor(object):
    @staticmethod
    def run():
        time = env.now
        with scenario_one.request() as request:
                yield request
                # service_time = numpy.random.uniform(DURATION_OF_FRIGHT[0], DURATION_OF_FRIGHT[1])
                yield env.timeout(14)
                wait = env.now
                print("There {} door processed, time = {}".format(queue_length, wait - time))


def source_doors(env):
    ind = 0
    while env.now < (HOUR*VISITING_HOURS[0][0]):
        ind += 1
        # doors_number = TIMEZONES_POPULATION[env.now/HOUR]
        doors_number = 20
        print("{} HOUR is now running".format(env.now/HOUR+1))

        global queue_length
        queue_length += doors_number
        print(queue_length)
        while queue_length > 0:
            env.process(ChildDoor.run())
            queue_length -= 1
        yield env.timeout(HOUR)


env = simpy.Environment()
scenario_one = simpy.Resource(env, capacity=2)
env.process(source_doors(env))
env.run(until=HOUR*VISITING_HOURS[0][0])













# def generate():
#     bar = progressbar.ProgressBar(widgets=[
#         ' [', progressbar.Timer(), '] ',
#         progressbar.AnimatedMarker(markers='<3 '),
#         progressbar.AnimatedMarker(markers='3 <'),
#         progressbar.AnimatedMarker(markers=' <3'),
#         progressbar.AnimatedMarker(markers='<3 '),
#         progressbar.AnimatedMarker(markers='3 <'),
#         progressbar.AnimatedMarker(markers=' <3'),
#         progressbar.AnimatedMarker(markers='<3 '),
#         progressbar.AnimatedMarker(markers='3 <'),
#         progressbar.AnimatedMarker(markers=' <3'),
#
#         progressbar.Bar(),
#     ])
#
#     for i in range(TIMEZONES_POPULATION["0"]):
#         interval = HOUR
#         yield env.timeout(interval)
#         env.process(Transaction.run())
#
#         if i % round((TIMEZONES_POPULATION["0"]/100), 0) == 0:
#             bar.update(i/(TIMEZONES_POPULATION["0"]/100))
