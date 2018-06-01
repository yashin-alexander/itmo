import queuing_systems_modeling
import numpy
import math
from calculations import *
import argparse
import sys
import constants

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


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('--mod1', choices=['exp', 'det', 'erl', 'uni'], default='exp')
    parser.add_argument('--mod2', choices=['exp', 'det', 'erl', 'uni'], default='exp')
    parser.add_argument('--mod3', choices=['exp', 'det', 'erl', 'uni'], default='exp')
    args = parser.parse_args()
    constants.mods.append(args.mod1)
    constants.mods.append(args.mod2)
    constants.mods.append(args.mod3)
    # print(args.mod1, args.mod2, args.mod3)

    for i in range(experiments_number):
        print("\nexperiment #", i + 1)
        result.append(queuing_systems_modeling.calculate())

    middles = calculate_middles(result)
    standart_deviations = calculate_standart_deviations(result, middles)
    SEs = calculate_SEs(standart_deviations)
    confidence_interval = calculate_confidence_intervals(SEs)

    for i in range(len(output)):
        print(output[i], middles[i], '+-', confidence_interval[i])


if __name__ == "__main__":
    main()
