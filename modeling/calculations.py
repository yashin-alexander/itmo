from numpy import mean, minimum, std
import constants
import math
from scipy import stats


def calculate_loading(array_b, array_intervals, max=1, k=1, p=0):
    b = mean(array_b)
    lyambda = 1 / mean(array_intervals)
    return minimum((1 - p) * b * lyambda / k, max)


def calculate_probability_of_losing(lost, all):
    return lost / all


def calculate_average_lenght(array_intervals, array_times, p):
    lyambda_shtrih = (1 - p) / mean(array_intervals)
    return lyambda_shtrih * mean(array_times)

#
# def calculate_average_waiting_time_1(array_b, loading, k=1):
#     b = mean(array_b)
#     p0 = math.pow(k*loading, k) / (math.factorial(k) * (1 - loading))
#     for i in range(0, k - 1):
#         p0 += math.pow(k * loading, i) / math.factorial(i)
#     p0 = 1 / p0
#     p = p0 * math.pow(k * loading, k) / (math.factorial(k) * (1 - loading))
#     return p * b / (1 - loading) / k


def calculate_average_staying_time(array_times, array_b):
    return mean(array_times) + mean(array_b)
