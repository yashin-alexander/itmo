import queuing_systems_modeling
import numpy
import math
from calculations import *

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

for i in range(experiments_number):
    print("\nexperiment #", i + 1)
    result.append(queuing_systems_modeling.calculate())


middles = calculate_middles(result)
standart_deviations = calculate_standart_deviations(result, middles)
SEs = calculate_SEs(standart_deviations)
confidence_interval = calculate_confidence_intervals(SEs)

for i in range(len(output)):
    print(output[i], middles[i], '+-', confidence_interval[i])
