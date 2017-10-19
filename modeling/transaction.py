import numpy
import random

from constants import *
import constants
from calculations import *


class Transaction(object):
    @staticmethod
    def run():
        # smo1
        creation_time = env.now
        with system_1.request() as request_1:
            queue_1_length.append(len(system_1.queue))
            yield request_1
            queue_1_waiting_time.append(env.now - creation_time)

            service_time = get_service_time(constants.mods[0])
            system_1_service_duration.append(service_time)
            yield env.timeout(service_time)

        if numpy.random.randint(1, 100000) > (Q * 100000):
            #smo3
            system_3_transactions_intervals.append(env.now - constants.system_3_enter_time)
            constants.system_3_enter_time = env.now

            if len(system_3.queue) >= E3:
                constants.lost_3 += 1
                return

            queue_3_enter_time = env.now

            with system_3.request() as request_3:
                queue_3_length.append(len(system_3.queue))
                yield request_3

                queue_3_waiting_time.append(env.now - queue_3_enter_time)

                # exp uni det
                service_time = get_service_time(constants.mods[2])
                system_3_service_duration.append(service_time)
                yield env.timeout(service_time)

        else:
            # smo2
            system_2_transactions_intervals.append(env.now - constants.system_2_enter_time)
            constants.system_2_enter_time = env.now
            if len(system_2.queue) >= E2:
                constants.lost_2 += 1
                return

            queue_2_enter_time = env.now

            with system_2.request() as request_2:
                queue_2_length.append(len(system_2.queue))
                yield request_2

                queue_2_waiting_time.append(env.now - queue_2_enter_time)
                # erl det exp
                service_time = get_service_time(constants.mods[1])
                system_2_service_duration.append(service_time)
                yield env.timeout(service_time)

        global_time.append(env.now - creation_time)


def get_expovariative_service_time():
    return random.expovariate(1 / MB)


def get_determined_service_time():
    return MB


def get_erlang_service_time():
    return numpy.random.gamma(2, MB/2, 1)


def get_uniform_service_time():
    interval = calculate_confidence_interval(calculate_SE(MB * 0.3, 2))
    service_time = numpy.random.uniform(MB - interval, MB + interval, 1)
    return service_time


def get_service_time(mode):
    if mode == 'exp':
        return get_expovariative_service_time()
    elif mode == 'det':
        return get_determined_service_time()
    elif mode == 'erl':
        return get_erlang_service_time()
    elif mode == 'uni':
        return get_uniform_service_time()
    else:
        return get_expovariative_service_time()
