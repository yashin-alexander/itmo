import sys
import numpy
import plotly
import plotly.graph_objs as go
from numpy import random, mean, var, std


plotly.tools.set_credentials_file(username='yashin_alexander', api_key='Fx4bpffs3QI4dT7SXtqR')

SEED_1 = 40  # A
SEED_2 = 41  # B
EXPECTED_VALUE = 64000  # V
LEFT_BORDER = 12800  # G
RIGHT_BORDER = 115200  # D
E = 12800
SHAPE = 7  # YO

NUMBER_OF_STEPS = 1000
STEP = round(((RIGHT_BORDER - LEFT_BORDER) / 1000), 1)

NUMBER_OF_VARIABLES = [10, 100, 1000, 5000, 10000, 20000]


def even_distribution(seed, size):
    numpy.random.seed(seed)
    result_list = numpy.random.random_integers(0, 999, size)

    for i in range(size):
        result_list[i] = LEFT_BORDER + result_list[i]*STEP

    return result_list


def exp_distribution(seed, size):
    numpy.random.seed(seed)
    beta = (EXPECTED_VALUE-LEFT_BORDER)
    result_list = numpy.random.exponential(beta, size)

    for i in range(size):
        result_list[i] = round(LEFT_BORDER + result_list[i])

    return result_list


def erlang_distribution(seed, size):
    numpy.random.seed(seed)
    scale = (EXPECTED_VALUE-LEFT_BORDER)/SHAPE

    result_list = numpy.random.gamma(SHAPE, scale, size)

    for i in range(size):
        result_list[i] = round(LEFT_BORDER + result_list[i])

    return result_list


def make_diagram_columns(even_result_1, even_result_2, exp_result_1, exp_result_2, erlang_result_1, erlang_result_2):

    first_even_result = go.Histogram(x=even_result_1)
    second_even_result = go.Histogram(x=even_result_2)

    first_exp_result = go.Histogram(x=exp_result_1)
    second_exp_result = go.Histogram(x=exp_result_2)

    first_erlang_result = go.Histogram(x=erlang_result_1)
    second_erlang_result = go.Histogram(x=erlang_result_2)

    data = [first_even_result, second_even_result,
            first_exp_result, second_exp_result,
            first_erlang_result, second_erlang_result]

    fig = go.Figure(data=data)
    plotly.plotly.plot(fig, filename='overlaid histogram')


def computing():
    even_result_1 = []
    even_result_2 = []
    exp_result_1 = []
    exp_result_2 = []
    erlang_result_1 = []
    erlang_result_2 = []

    for i in range(6):
        current_variables_number = NUMBER_OF_VARIABLES[i]

        even_result_1.append(even_distribution(SEED_1, current_variables_number))
        even_result_2.append(even_distribution(SEED_2, current_variables_number))

        exp_result_1.append(exp_distribution(SEED_1, current_variables_number))
        exp_result_2.append(exp_distribution(SEED_2, current_variables_number))

        erlang_result_1.append(erlang_distribution(SEED_1, current_variables_number))
        erlang_result_2.append(erlang_distribution(SEED_2, current_variables_number))

        # make_diagram_columns(even_result_1[i], even_result_2[i],
        #                      exp_result_1[i], exp_result_2[i],
        #                      erlang_result_1[i], erlang_result_2[i])
        # input()

    even_results = [even_result_1, even_result_2]
    exp_results = [exp_result_1, exp_result_2]
    erlang_results = [erlang_result_1, erlang_result_2]

    make_table(even_results, 10, "IMA RAVNOMERNOE")
    make_table(exp_results, 30, "IMA EXPONENTIALNOE")
    make_erlang_table(erlang_results)


def make_erlang_table(results):
    columns_number = 30
    table = "<html>" \
            "<head></head>" \
            "<body>" \
            "<table>" \
            "<tr><td>IMA ERLANG</td></tr>"
    cell_size = (RIGHT_BORDER - LEFT_BORDER) / columns_number
    # .....


def make_table(results, columns_number, message):
    table = "<html>" \
             "<head></head>" \
             "<body>" \
             "<table>" \
             "<tr><td>{}</td></tr>".format(message)

    cell_size = (RIGHT_BORDER - LEFT_BORDER)/columns_number

    for i in range(2):
        table += "<tr><td>generator {}</td></tr>".format(i+1)
        table += "<tr><td> </td><td>10</td><td>100</td><td>1000</td><td>5000</td><td>10000</td><td>20000</td></tr>"
        table += "<tr><td>mean</td>"
        for j in range(6):
            table += "<td>{}</td>".format(round(mean(results[i][j]), 3))
        table += "</tr>"
        table += "<tr><td>stdev</td>"
        for j in range(6):
            table += "<td>{}</td>".format(round(std(results[i][j]), 3))
        table += "</tr>"
        table += "<tr><td>varcoef</td>"
        for j in range(6):
            table += "<td>{}</td>".format(round(std(results[i][j])/mean(results[i][j]), 3))
        table += "</tr>"
        for l in range(columns_number):
            cell_left = LEFT_BORDER+cell_size*l
            cell_right = LEFT_BORDER+cell_size*(l+1)
            table += "<tr><td>{0}-{1}</td>".format(round(cell_left), round(cell_right))
            for j in range(6):
                counter = 0
                for k in range(len(results[i][j])):
                    if cell_left <= results[i][j][k] < cell_right:
                        counter += 1
                table += "<td>{}</td>".format(counter)
            table += "</tr>\n"
    table += "</table></body></html>"
    fd = open("file", "a")
    fd.write(table)
    fd.close()


def main():
    computing()


if __name__ == "__main__":
    main()

