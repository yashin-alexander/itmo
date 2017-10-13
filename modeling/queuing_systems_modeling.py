from __future__ import print_function

import simpy
import time
import numpy
from transaction import *


def device_loading(total_working_time, device):
    # print(total_working_time, device)
    return numpy.sum(device)/total_working_time


def transaction_wait_time(time_list):
    # print(numpy.sum(time_list), len(time_list))
    return numpy.sum(time_list) / len(time_list)


def queue_legnts(lengths_list):
    return numpy.sum(lengths_list) / len(lengths_list)

def main():
    transaction_cnt = 0

    while transaction_cnt < TRANSACTIONS_NUMBER:

        transaction_cnt += 1
        beta = 1/LAMBDA

        yield env.timeout(numpy.random.exponential(beta, 1))
        transaction = Transaction(env, transaction_cnt, system_1)

        env.process(transaction.run())

    time.sleep(0.5)


if __name__ == "__main__":
    env.process(main())
    env.run()

    # print("HERE")
    # print(leave_2, leave_3)

    print("first zagruz = ", device_loading(env.now, service_duration_1))
    print("second zagruz = ", device_loading(env.now, service_duration_2))
    print("third zagruz = ", device_loading(env.now, service_duration_3))

    print("first ozhid = ", transaction_wait_time(queue_1_waiting_time))
    print("second ozhid = ", transaction_wait_time(queue_2_waiting_time))
    print("third ozhid = ", transaction_wait_time(queue_3_waiting_time))

    print("first ocher = ", queue_legnts(queue_1_lengths))
    print("second ocher = ", queue_legnts(queue_2_lengths))
    print("third ocher = ", queue_legnts(queue_3_lengths))


