from __future__ import print_function

import simpy
import time
import numpy
from transaction import *


def main():
    transaction_cnt = 0

    while transaction_cnt < TRANSACTIONS_NUMBER:

        transaction_cnt += 1
        beta = 1/LAMBDA

        yield env.timeout(numpy.random.exponential(beta, 1))
        transaction = Transaction(env, transaction_cnt, system_1)

        env.process(transaction.run())

    time.sleep(0.5)

    # print("HERE")
    # print(leave_2, leave_3)


if __name__ == "__main__":
    env.process(main())
    env.run()



