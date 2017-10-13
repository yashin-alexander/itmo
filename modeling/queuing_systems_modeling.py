from numpy import mean, minimum, std
from transaction import *


def main():
    proc = env.process(generate())
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

    print("\ndlina1 = ", mean(queue_1_length))
    print("dlina2 = ", mean(queue_2_length))
    print("dlina3 = ", mean(queue_3_length))

    print("\nprebyvanie1 = ", mean(system_1_service_duration) + mean(queue_1_waiting_time))
    print("prebyvanie2 = ", mean(system_2_service_duration) + mean(queue_2_waiting_time))
    print("prebyvanie3 = ", mean(system_3_service_duration) + mean(queue_3_waiting_time))

    print("\nlifecycle = ", mean(global_time))
    print("varcoef = ", std(global_time) / mean(global_time))

    print("\nlost2 = ", p_lost_2)
    print("lost3 = ", p_lost_3)


def generate():
    transaction_cnt = 0
    while transaction_cnt < TRANSACTIONS_NUMBER:
        transaction_cnt += 1
        interval = numpy.random.exponential(1/LAMBDA)
        system_1_transactions_intervals.append(interval)
        yield env.timeout(interval)

        env.process(Transaction.run())


def calculate_loading(array_b, array_intervals, max=1, k=1, p=0):
    b = mean(array_b)
    lyambda = 1 / mean(array_intervals)
    return minimum((1 - p) * b * lyambda / k, max)


def calculate_probability_of_losing(lost, element_number):
    return lost / element_number


if __name__ == "__main__":
    main()
