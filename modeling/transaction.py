import numpy
import random

from constants import *
import constants


class Transaction(object):
    @staticmethod
    def run():
        # smo1
        creation_time = env.now
        with system_1.request() as request_1:
            queue_1_length.append(len(system_1.queue))
            yield request_1
            queue_1_waiting_time.append(env.now - creation_time)
            service = random.expovariate(1/MB)
            system_1_service_duration.append(service)
            yield env.timeout(service)

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

                service = random.expovariate(1/MB)
                system_3_service_duration.append(service)
                yield env.timeout(service)

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

                work_2 = random.expovariate(1/MB)
                system_2_service_duration.append(work_2)
                yield env.timeout(work_2)

        global_time.append(env.now - creation_time)