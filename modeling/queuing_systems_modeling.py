from __future__ import print_function

import simpy
import time
import numpy
from transaction import *


def device_loading(total_working_time, device):
    return numpy.sum(device)/total_working_time

def main():
    transaction_cnt = 0

    while transaction_cnt < TRANSACTIONS_NUMBER:

        transaction_cnt += 1
        beta = 1/LAMBDA

        yield env.timeout(numpy.random.exponential(beta, 1))
        transaction = Transaction(env, transaction_cnt, system_1)

        env.process(transaction.run())

    time.sleep(0.5)

    print("HERE")
    print(leave_2, leave_3)
    print("first zagruz = ", device_loading(env.now, service_duration_1))
    print("second zagruz = ", device_loading(env.now, service_duration_2))
    print("third zagruz = ", device_loading(env.now, service_duration_3))


if __name__ == "__main__":
    env.process(main())
    env.run()



