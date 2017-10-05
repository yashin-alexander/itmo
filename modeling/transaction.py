import simpy
import numpy
from constants import *
from transaction import *



queue_1_lengths = []
queue_1_waiting_time = []
queue_1_service_time = []

queue_2_lengths = []
queue_2_waiting_time = []
queue_2_service_time = []

queue_3_lengths = []
queue_3_waiting_time = []
queue_3_service_time = []




class Transaction(object):
    def __init__(self, env, name, system):
        self.name = name
        self.env = env
        self.system = system

    def run(self):

        global queue_len_1

        global queue_2_len

        global queue_3_len
        global queue_3_comes_time


        queue_len_1 += 1
        creation_time = self.env.now

        with self.system.request() as request_1:
            yield request_1

            queue_len_1 -= 1

            wait = self.env.now - creation_time
            queue_1_waiting_time.append(self.env.now - creation_time)
            queue_1_lengths.append(queue_len_1)

            service_duration = numpy.random.exponential(MB)
            yield self.env.timeout(service_duration)

            queue_1_service_time.append(self.env.now - creation_time)

            print(u"Я {0}; Я обслуживался {1} единиц времени, и  ждал в очереди {2} единиц времени".format(
                self.name, self.env.now - creation_time, wait))

            if numpy.random.randint(1, 1000) > (Q * 1000):
                #smo3
                if queue_3_len > 1:
                    print("poka from3")
                    return

                queue_3_len += 1
                queue_3_comes_time = self.env.now
                self.system = system_3
                with self.system.request() as request_3:
                    yield request_3

                    queue_3_len -= 1
                    wait = self.env.now - queue_3_comes_time
                    queue_3_waiting_time.append(self.env.now - queue_3_comes_time)
                    queue_3_lengths.append(queue_3_len)

                    service_duration = numpy.random.exponential(MB)
                    yield self.env.timeout(service_duration)

                    queue_3_service_time.append(self.env.now - queue_3_comes_time)
                    print(u"Я {0}; Я обслуживался THIRD {1} времени, ждал в очереди {2} единиц времени".format(
                        self.name, self.env.now - queue_3_comes_time, wait))



            else:
                return

                #smo2
