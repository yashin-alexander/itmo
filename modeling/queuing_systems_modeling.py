from __future__ import print_function

import simpy
import numpy
from constants import *
from transaction import *




def main():
    transaction_cnt = 0

    while transaction_cnt < 100:  # Посетители приходят 10 часов подряд CONSUMER_TIME = 3600*10

        transaction_cnt += 1
        beta = 1/LAMBDA

        yield env.timeout(numpy.random.exponential(beta, 1))
        transaction = Transaction(env, transaction_cnt, system_1)

        env.process(transaction.run())






# def main():


if __name__ == "__main__":
    env.process(main())
    env.run()


