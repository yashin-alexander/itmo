import runner
import table

import math
import numpy

from constants import TIMEZONES_POPULATION, TIMEZONES_NUMBER

# out = table.Table()
# out.open_table(("helle", "wedwe", "edwed"))
# print(out.content)

TIMEZONES_POPULATION.update((x, int(round((y*25.44/50000)+5, 0))) for x, y in TIMEZONES_POPULATION.items())

print(TIMEZONES_POPULATION)

returned = []


experiments_number = 8


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
            accumulator.append((list_for_calculate[j][i] - middles[i]) ** 2)  # √(x-x)^2

        standart_deviations.append(math.sqrt(sum(accumulator) / experiments_number))  # √[(∑(x-x)^2)/n]
    return standart_deviations


def calculate_confidence_interval(SEs):  # x ± z∗SE
    z = 1.96
    confidence_interval = []
    for i in range(len(SEs)):
        confidence_interval.append(SEs[i] * z)
    return confidence_interval


def calculate_SE(standart_deviation, number):
    return standart_deviation / math.sqrt(number)


def calculate_SEs(standart_deviations):  # se = standard_deviation/√experiments_number
    SEs = []
    for i in range(len(standart_deviations)):
        SEs.append(calculate_SE(standart_deviations[i], experiments_number))
    return SEs


def calculate_confidence_interval(SE):
    z = 1.96
    return SE*z


def calculate_confidence_intervals(SEs):  # x ± z∗SE
    confidence_interval = []
    for i in range(len(SEs)):
        confidence_interval.append(calculate_confidence_interval(SEs[i]))
    return confidence_interval












print("\n-----------------------------\n")
worker = runner.Runner()
returned.append(worker.execute(0, 0))
worker = runner.Runner()
returned.append(worker.execute(0, 0))
worker = runner.Runner()
returned.append(worker.execute(0, 0))
worker = runner.Runner()
returned.append(worker.execute(0, 0))
worker = runner.Runner()
returned.append(worker.execute(0, 0))
worker = runner.Runner()
returned.append(worker.execute(0, 0))
worker = runner.Runner()
returned.append(worker.execute(0, 0))
worker = runner.Runner()
returned.append(worker.execute(0, 0))

middles = calculate_middles(returned)
standart_deviations = calculate_standart_deviations(returned, middles)
SEs = calculate_SEs(standart_deviations)
confidence_interval = calculate_confidence_intervals(SEs)
#
# for i in range(experiments_number):

# print(middles)
print("Not processed doors {}+-{}. \nMonsters stuck {}+-{}. \nProcessed doors {}+-{} \nMonster productivity {}+-{}"
      .format(middles[0], confidence_interval[0],
              middles[1], confidence_interval[1],
              middles[2], confidence_interval[2],
              middles[3], confidence_interval[3]))


# print("\n-----------------------------\n")
# worker = runner.Runner()
# returned.append(worker.execute(0, 1))
# worker = runner.Runner()
# returned.append(worker.execute(0, 1))
# worker = runner.Runner()
# returned.append(worker.execute(0, 1))
# worker = runner.Runner()
# returned.append(worker.execute(0, 1))
#
# print("\n-----------------------------\n")
# worker = runner.Runner()
# returned.append(worker.execute(0, 2))
# worker = runner.Runner()
# returned.append(worker.execute(0, 2))
# worker = runner.Runner()
# returned.append(worker.execute(0, 2))
# worker = runner.Runner()
# returned.append(worker.execute(0, 2))
#
# print("\n-----------------------------\n")
# worker = runner.Runner()
# returned.append(worker.execute(0, 3))
# worker = runner.Runner()
# returned.append(worker.execute(0, 3))
# worker = runner.Runner()
# returned.append(worker.execute(0, 3))
# worker = runner.Runner()
# returned.append(worker.execute(0, 3))






