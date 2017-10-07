import os
import matplotlib.pyplot as plt


def create_histogram(name, columns, *args):
    fig = plt.figure()
    histogram = plt.hist(args, columns)

    path = "./pictures/"
    if not os.path.exists(path):
        os.mkdir(path)
    plt.savefig('{}{}.png'.format(path, name))
