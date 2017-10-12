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
