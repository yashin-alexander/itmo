from __future__ import print_function
import simpy
import sys
from numpy import mean, std
from transaction import *
from calculations import *


def main():
    transaction_cnt = 0
    while transaction_cnt < TRANSACTIONS_NUMBER:
        transaction_cnt += 1
        interval = numpy.random.exponential(1/LAMBDA)
        system_1_transactions_intervals.append(interval)
        yield env.timeout(interval)
        transaction = Transaction(env, transaction_cnt, system_1)

        env.process(transaction.run())


if __name__ == "__main__":
    proc = env.process(main())
    env.run()

    p_lost_2 = calculate_probability_of_losing(constants.lost_2, len(system_2_transactions_intervals))
    p_lost_3 = calculate_probability_of_losing(constants.lost_3, len(system_3_transactions_intervals))

    print("\nzagr1 = ", calculate_loading(system_1_service_duration, system_1_transactions_intervals, k=K))
    print("zagr2 = ", calculate_loading(system_2_service_duration, system_2_transactions_intervals, p=p_lost_2))
    print("zagr3 = ", calculate_loading(system_3_service_duration, system_3_transactions_intervals, p=p_lost_3))

    print("\nnagr1 = ", calculate_loading(system_1_service_duration, system_1_transactions_intervals, max=K))
    print("nagr2 = ", calculate_loading(system_2_service_duration, system_2_transactions_intervals))
    print("nagr3 = ", calculate_loading(system_3_service_duration, system_3_transactions_intervals))

    print("\nozhid1 = ", mean(queue_1_waiting_time))
    print("ozhid2 = ", mean(queue_2_waiting_time))
    print("ozhid3 = ", mean(queue_3_waiting_time))

    print("\ndlina1 = ", calculate_average_lenght(system_1_transactions_intervals, queue_1_waiting_time, 0))
    print("dlina2 = ", calculate_average_lenght(system_2_transactions_intervals, queue_2_waiting_time, p_lost_2))
    print("dlina3 = ", calculate_average_lenght(system_3_transactions_intervals, queue_3_waiting_time, p_lost_3))

    print("\nprebyvanie1 = ", mean(system_1_service_duration)+mean(queue_1_waiting_time))
    print("prebyvanie2 = ", mean(system_2_service_duration)+mean(queue_2_waiting_time))
    print("prebyvanie3 = ", mean(system_3_service_duration)+mean(queue_3_waiting_time))

    print("\nlifecycle = ", mean(global_time))
    print("varcoef = ", std(global_time) / mean(global_time))

    print("\nlost2 = ", p_lost_2)
    print("lost3 = ", p_lost_3)
