import simpy
import numpy
import random

from constants import *
import constants
from transaction import *


class Transaction(object):

    def __init__(self, env, name, system):
        self.name = name
        self.env = env
        self.system = system

    def run(self):

        # smo1
        creation_time = self.env.now
        with self.system.request() as request_1:
            yield request_1

            queue_1_waiting_time.append(self.env.now - creation_time)

            service = random.expovariate(1/MB)
            system_1_service_duration.append(service)
            yield self.env.timeout(service)

        if numpy.random.randint(1, 100000) > (Q * 100000):
            #smo3
            system_3_transactions_intervals.append(self.env.now - constants.system_3_enter_time)
            constants.system_3_enter_time = self.env.now

            if constants.queue_3_length >= E3:
                constants.lost_3 += 1
                return

            constants.queue_3_length += 1

            queue_3_enter_time = self.env.now
            self.system = system_3
            with self.system.request() as request_3:
                yield request_3

                constants.queue_3_length -= 1
                queue_3_waiting_time.append(self.env.now - queue_3_enter_time)

                service = random.expovariate(1/MB)
                system_3_service_duration.append(service)
                yield self.env.timeout(service)

        else:
            # smo2
            system_2_transactions_intervals.append(self.env.now - constants.system_2_enter_time)
            constants.system_2_enter_time = self.env.now
            if constants.queue_2_length >= E2:
                constants.lost_2 += 1
                return

            constants.queue_2_length += 1
            queue_2_enter_time = self.env.now

            self.system = system_2
            with self.system.request() as request_2:
                yield request_2

                constants.queue_2_length -= 1

                queue_2_waiting_time.append(self.env.now - queue_2_enter_time)

                work_2 = random.expovariate(1/MB)
                system_2_service_duration.append(work_2)
                yield self.env.timeout(work_2)

        global_time.append(self.env.now - creation_time)
