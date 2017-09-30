import random
import math
import sys

A = 40
B = 41
V = 64000
G = 12800   # left border
D = 115200  # right border
E = 12800
YO = 7      # 3 + 4


def even_distribution(number):
    calculation_sheet = {}
    for i in range(number):
        value = random.randint(G, D)        # specify borders for generation
        try:
            calculation_sheet[value] = calculation_sheet[value] + 1
        except:
            calculation_sheet[value] = 1
    return calculation_sheet


# def exp_distribution(number):
#     li = {}
#     for i in range(number):
#         value = math.floor(random.expovariate(0.5))
#         try:
#             li[value] = li[value] + 1
#         except:
#             li[value] = 1
#     return li
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


# def print_results(number, even_result, exp_result, erlang_result):
def print_results(number, even_result):

    print("\t\t\tEVEN\t\tEXPONENTIAL\t\tERLANG")
    for i in range(G, D):
        try:
            even_number = even_result[i]
        except:
            even_number = 0

        if even_number != 0:
            print('i= %(iter)d\t\teven = %(even)d' % {"iter": i, "even": even_number})


def main():
    even_result = even_distribution(int(sys.argv[1]))
    # exp_result = exp_distribution(int(sys.argv[1]))
    # erlang_result = erlang_distribution(int(sys.argv[1]))

    # print_results(int(sys.argv[1]), even_result, exp_result, erlang_result)
    print_results(int(sys.argv[1]), even_result)

if __name__ == "__main__":
    main()

