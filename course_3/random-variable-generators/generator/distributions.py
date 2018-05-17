import numpy
from constants import *


def even_distribution(seed, size):
    numpy.random.seed(seed)
    result_list = numpy.random.random_integers(0, 999, size)

    for i in range(size):
        result_list[i] = LEFT_BORDER + result_list[i] * STEP

    return result_list


def exp_distribution(seed, size):
    numpy.random.seed(seed)
    beta = (EXPECTED_VALUE - LEFT_BORDER)
    result_list = numpy.random.exponential(beta, size)

    for i in range(size):
        result_list[i] = round(LEFT_BORDER + result_list[i])

    return result_list


def erlang_distribution(seed, size, shape):
    numpy.random.seed(seed)
    scale = (EXPECTED_VALUE - LEFT_BORDER)/shape

    result_list = numpy.random.gamma(shape, scale, size)

    for i in range(size):
        result_list[i] = round(LEFT_BORDER + result_list[i])

    return result_list
