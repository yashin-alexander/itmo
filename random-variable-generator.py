import random
import math
import sys

A = 40
B = 41
V = 64000
G = 12800   # left border
D = 115200  # right border
E = 12800
YO = 7

NUMBER_OF_STEPS = 1000
STEP = round(((D - G) / 1000), 1)


def make_numbers_count():
    return int(sys.argv[1])


def make_iteration_list():
    iteration_list = {}
    for i in range(0, NUMBER_OF_STEPS+1):
        iteration_list[i] = round(G + STEP*i, 1)
    return iteration_list


def even_distribution(number):
    result_list = {}

    for i in range(number):
        value = random.randint(0, NUMBER_OF_STEPS+1)        # specify borders for generation
        try:
            result_list[value] = result_list[value] + 1
        except:
            result_list[value] = 1

    return result_list


def exp_distribution(number):
    result_list = {}

    for i in range(number):
        value = round(random.expovariate(0.000019531))  # 1/(64000-12800)
        step_number = math.floor(value / STEP)

        try:
            result_list[step_number] = result_list[step_number] + 1
        except:
            result_list[step_number] = 1

    return result_list

#
#
# def erlang_distribution(number):
#     li = {}
#     for i in range(number):
#         value = math.floor(random.gammavariate(1, 20))
#         try:
#             li[value] = li[value] + 1
#         except:
#             li[value] = 1
#     return li


def print_results(iteration_list, even_result_1, even_result_2, exp_result):

    print("  i\t\tITER\t\tEVEN_1\t\tEVEN_2\t\tEXPONENTIAL\t\tERLANG")
    for i in range(0, NUMBER_OF_STEPS*5+1):

        if i in even_result_1 or i in even_result_2 or i in exp_result:
            print('%(index)4.d\t%(iter)13.7s\t%(even_1)13.8s\t%(even_2)13.8s\t%(exp)13.8s\t'

                  % {"index": i,
                     "iter": iteration_list.get(i),
                     "even_1": even_result_1.get(i),
                     "even_2": even_result_2.get(i),
                     "exp": exp_result.get(i)
                     })


def main():
    random_numbers_count = make_numbers_count()
    iteration_list = make_iteration_list()

    even_result_1 = even_distribution(random_numbers_count)
    even_result_2 = even_distribution(random_numbers_count)
    exp_result = exp_distribution(random_numbers_count)
    # erlang_result = erlang_distribution(int(sys.argv[1]))

    print_results(iteration_list, even_result_1, even_result_2, exp_result)


if __name__ == "__main__":
    main()

