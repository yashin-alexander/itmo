from numpy import mean, std
from html import *


# -------------CALCULATING MEAN, SKO, VARCOEF-------------


def calculate_mean(array):
    result = []
    for i in range(SELECTIONS_COUNT):
        result.append(round(mean(array[i]), 4))
    return result


def calculate_std(array):
    result = []
    for i in range(SELECTIONS_COUNT):
        result.append(round(std(array[i]), 4))
    return result


def calculate_varcoef(array):
    result = []
    for i in range(6):
        result.append(round(std(array[i]) / mean(array[i]), 4))
    return result


# --------------------CALCULATING ERRORS--------------------


def calculate_mean_error(array):
    result = []
    for i in range(SELECTIONS_COUNT):
        result.append(abs(round((mean(array[i]) - EXPECTED_VALUE) / EXPECTED_VALUE, 4)))
    return result


def calculate_std_error(array, sko):
    result = []
    for i in range(SELECTIONS_COUNT):
        result.append(abs(round((std(array[i]) - sko) / sko, 4)))
    return result


def calculate_varcoef_error(array, varcoef):
    result = []
    for i in range(SELECTIONS_COUNT):
        result.append(abs(round((std(array[i]) / mean(array[i]) - varcoef) / varcoef, 4)))
    return result


# --------------------CALCULATIONS OUTPUT--------------------


def create_file_with_calculated_values():
    html = Html("Calculated values")
    row = "Mean for all distributions: {}".format(EXPECTED_VALUE)
    html.add_text(row)
    row = "Standard deviation for even distribution: {}".format(EVEN_SKO)
    html.add_text(row)
    row = "Standard deviation for exponential distribution: {}".format(EXP_SKO)
    html.add_text(row)
    row = "Standard deviation for erlang distribution with shape {}: {}".format(K, ERL_SKO_K)
    html.add_text(row)
    row = "Standard deviation for erlang distribution with shape {}: {}".format(K_PLUS_1, ERL_SKO_K_PLUS_1)
    html.add_text(row)
    row = "Coefficient of variation for even distribution: {}".format(EVEN_VARCOEF)
    html.add_text(row)
    row = "Coefficient of variation for exponential distribution: {}".format(EXP_VARCOEF)
    html.add_text(row)
    row = "Coefficient of variation for erlang distribution with shape {}: {}".format(K, ERL_VARCOEF_K)
    html.add_text(row)
    row = "Coefficient of variation for erlang distribution with shape {}: {}".format(K + 1, ERL_VARCOEF_K_PLUS_1)
    html.add_text(row)
    html.close_and_create_file("calculated-values")
