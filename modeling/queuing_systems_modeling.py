from __future__ import print_function

import sys
import simpy
import time
import numpy
from transaction import *


def device_loading(total_working_time, device, k=1):
    # print(total_working_time, device)
    try:
        return numpy.sum(device)/(k*total_working_time)
    except:
        return "none"


def device_abusing(working_time):
    try:
        return LAMBDA*(numpy.sum(working_time)/len(working_time))
    except:
        return "none"


def transaction_wait_time(time_list):
    # print(numpy.sum(time_list), len(time_list))
    # print(time_list)
    try:
        return numpy.sum(time_list) / len(time_list)
    except:
        return "none"


def queue_legnts(lengths_list):
    # print(lengths_list)
    # for i in range(len(lengths_list)):
    #     lengths_list[i] = lengths_list[i]*queue_waiting_time[i]

    try:
        return numpy.sum(lengths_list) / len(lengths_list)
    except:
        return "none"


def smo_ave_time(smo_time_list):
    try:
        return numpy.sum(smo_time_list) / len(smo_time_list)
    except:
        return "none"


def calculate_varcoef(smo_time_list):
    beta = 1 / LAMBDA
    try:
        return (numpy.std(smo_time_list)/(numpy.sum(smo_time_list) / len(smo_time_list)))
    except:
        return "none"


def calculate_leaves(leaves):
    try:
        return leaves/TRANSACTIONS_NUMBER
    except:
        return "none"


def main():
    transaction_cnt = 0
    numpy.random.seed(SEED)

    while transaction_cnt < TRANSACTIONS_NUMBER:

        transaction_cnt += 1
        beta = 1/LAMBDA

        yield env.timeout(numpy.random.exponential(beta, 1))
        transaction = Transaction(env, transaction_cnt, system_1)

        env.process(transaction.run())


if __name__ == "__main__":
    global SEED
    SEED = int(sys.argv[1])

    env.process(main())
    env.run()
    time.sleep(0.5)

    print("first zagruz = ", device_loading(env.now, service_duration_1, K))
    print("second zagruz = ", device_loading(env.now, service_duration_2))
    print("third zagruz = ", device_loading(env.now, service_duration_3))

    print("\nfirst nagruz = ", device_abusing(service_duration_1))
    print("second nagruz = ", device_abusing(service_duration_2))
    print("third nagruz = ", device_abusing(service_duration_3))

    print("\nfirst ozhid = ", transaction_wait_time(queue_1_waiting_time))
    print("second ozhid = ", transaction_wait_time(queue_2_waiting_time))
    print("third ozhid = ", transaction_wait_time(queue_3_waiting_time))

    print("\nfirst queue length = ", queue_legnts(queue_1_lengths))
    print("second queue length = ", queue_legnts(queue_2_lengths))
    print("third queue length = ", queue_legnts(queue_3_lengths))

    print("\nfirst smo time = ", smo_ave_time(service_duration_1))
    print("second smo time = ", smo_ave_time(service_duration_2))
    print("third smo time = ", smo_ave_time(service_duration_3))

    print("\ntransact lifecycle time = ", queue_legnts(global_time))
    print("all varcoef = ", calculate_varcoef(global_time))

    print("\nall leave = ", calculate_leaves(leave_2[0] + leave_3[0]))




