import os
import matplotlib.pyplot as plt
from constants import *


def create_histogram(name, columns, array_1, array_2):
    name_1 = "Generator №{}".format(SEED_1)
    name_2 = "Generator №{}".format(SEED_2)

    plt.figure()
    plt.hist([array_1, array_2], columns, label=[name_1, name_2])

    plt.legend()
    path = "./pictures/"
    if not os.path.exists(path):
        os.mkdir(path)
    plt.savefig('{}{}.png'.format(path, name))
    plt.close()
