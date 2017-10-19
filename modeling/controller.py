import queuing_systems_modeling
import numpy
import math


experiments_number = 5
output = ["zagr1 = ", "zagr2 = ", "zagr3 = ", \
          "nagr1 = ", "nagr2 = ", "nagr3 = ", \
          "ozhid1 = ", "ozhid2 = ", "ozhid3 = ", \
          "dlina1 = ", "dlina2 = ", "dlina3 = ", \
          "prebyvanie1 = ", "prebyvanie2 = ", "prebyvanie3 = ", \
          "lifecycle = ", "varcoef = ", \
          "lost2 = ", "lost3 = "
          ]
result = []


def calculate_middles(list_for_calculate):  # middle = (x1+x2+x3+x4+x5)/5
    number_of_values = len(list_for_calculate[0])
    middle = []
    for i in range(number_of_values):
        accumulator = []
        for j in range(experiments_number):
            accumulator.append(list_for_calculate[j][i])
        middle.append(numpy.mean(accumulator))
    return middle


def calculate_standart_deviations(list_for_calculate, middles):  # standard_deviation = √[(∑(x-x)^2)/n]
    number_of_values = len(list_for_calculate[0])
    standart_deviations = []
    for i in range(number_of_values):
        accumulator = []
        for j in range(experiments_number):
            accumulator.append((list_for_calculate[j][i] - middles[i])**2)  # √(x-x)^2

        standart_deviations.append(math.sqrt(sum(accumulator)/experiments_number))  # √[(∑(x-x)^2)/n]
    return standart_deviations


def calculate_SEs(standart_deviations):  # se = standard_deviation/√experiments_number
    SEs = []
    for i in range(len(standart_deviations)):
        SEs.append(standart_deviations[i]/math.sqrt(experiments_number))
    return SEs


def calculate_confidence_interval(SEs):  # x ± z∗SE
    z = 1.96
    confidence_interval = []
    for i in range(len(SEs)):
        confidence_interval.append(SEs[i]*z)
    return confidence_interval


for i in range(experiments_number):
    print("\nexperiment #", i + 1)
    result.append(queuing_systems_modeling.calculate())


middles = calculate_middles(result)
standart_deviations = calculate_standart_deviations(result, middles)
SEs = calculate_SEs(standart_deviations)
confidence_interval = calculate_confidence_interval(SEs)

# print(middles, "\n", standart_deviations, "\n", SEs, "\n")

for i in range(len(output)):
    print(output[i], middles[i], '+-', confidence_interval[i])
