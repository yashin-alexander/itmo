import simpy
import numpy
import random
from constants import *
from transaction import *


class Transaction(object):
    def __init__(self, env, name, system):
        self.name = name
        self.env = env
        self.system = system

    def run(self):

        queue_1_len[0] += 1
        creation_time = self.env.now

        with self.system.request() as request_1:
            yield request_1

            queue_1_len[0] -= 1

            wait = self.env.now - creation_time
            queue_1_waiting_time.append(self.env.now - creation_time)
            queue_1_lengths.append(queue_1_len[0])

            work_1 = random.expovariate(1/MB)

            service_duration_1.append(work_1)
            yield self.env.timeout(work_1)

            queue_1_service_time.append(self.env.now - creation_time)

            # print(u"FIRST - name: {0} - serving: {1} - waiting: {2}".format(
            #     self.name, work_1, wait))

            if numpy.random.randint(1, 1000) > (Q * 1000):
                #smo3
                if queue_3_len[0] == E3:
                    # print("poka from3 my name is{}".format(self.name))
                    leave_3[0] += 1
                    pass

                queue_3_len[0] += 1
                queue_3_comes_time = self.env.now
                self.system = system_3
                with self.system.request() as request_3:
                    yield request_3

                    queue_3_len[0] -= 1
                    wait = self.env.now - queue_3_comes_time
                    queue_3_waiting_time.append(self.env.now - queue_3_comes_time)
                    queue_3_lengths.append(queue_3_len[0])

                    work_3 = random.expovariate(1/MB)
                    service_duration_3.append(work_3)
                    yield self.env.timeout(work_3)

                    queue_3_service_time.append(self.env.now - queue_3_comes_time)
                    # print(u"THIRD - name: {0} - serving: {1} - waiting: {2}".format(
                    #     self.name, work_3, wait))
            else:
                # smo2
                if queue_2_len[0] == E2:
                    # print("poka from2 my name is{}".format(self.name))
                    leave_2[0] += 1
                    pass

                queue_2_len[0] += 1
                queue_2_comes_time = self.env.now
                self.system = system_2
                with self.system.request() as request_2:
                    yield request_2

                    queue_2_len[0] -= 1
                    wait = self.env.now - queue_2_comes_time
                    queue_2_waiting_time.append(self.env.now - queue_2_comes_time)
                    queue_2_lengths.append(queue_2_len[0])

                    work_2 = random.expovariate(1/MB)
                    service_duration_2.append(work_2)
                    yield self.env.timeout(work_2)

                    queue_2_service_time.append(self.env.now - queue_2_comes_time)
                    # print(u"SECOND - name: {0} - serving: {1} - waiting: {2}".format(
                    #     self.name, work_2, wait))
        # print(leave_2[0], leave_3[0])

        global_time.append(self.env.now - creation_time)
        # print(global_time[0], numpy.sum(service_duration_1))
