import numpy
import plotly
import plotly.graph_objs as go
from table import Table
from numpy import random, mean, var, std
from constants import *

plotly.tools.set_credentials_file(username='yashin_alexander', api_key='Fx4bpffs3QI4dT7SXtqR')


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


def erlang_distribution(seed, size, shape):
    numpy.random.seed(seed)
    scale = (EXPECTED_VALUE-LEFT_BORDER)/shape

    result_list = numpy.random.gamma(shape, scale, size)

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


def main():
    even_result_1 = []
    even_result_2 = []
    exp_result_1 = []
    exp_result_2 = []
    erlang_result_1 = []
    erlang_result_2 = []
    erlang_result_3 = []
    erlang_result_4 = []

    for i in range(6):
        current_variables_number = NUMBER_OF_VARIABLES[i]

        even_result_1.append(even_distribution(SEED_1, current_variables_number))
        even_result_2.append(even_distribution(SEED_2, current_variables_number))

        exp_result_1.append(exp_distribution(SEED_1, current_variables_number))
        exp_result_2.append(exp_distribution(SEED_2, current_variables_number))

        erlang_result_1.append(erlang_distribution(SEED_1, current_variables_number, SHAPE))
        erlang_result_2.append(erlang_distribution(SEED_2, current_variables_number, SHAPE))

        erlang_result_3.append(erlang_distribution(SEED_1, current_variables_number, SHAPE+1))
        erlang_result_4.append(erlang_distribution(SEED_2, current_variables_number, SHAPE+1))

        make_diagram_columns(even_result_1[i], even_result_2[i],
                             exp_result_1[i], exp_result_2[i],
                             erlang_result_1[i], erlang_result_2[i])
        input()

    even_results = [even_result_1, even_result_2]
    exp_results = [exp_result_1, exp_result_2]
    erlang_results_shape_7 = [erlang_result_1, erlang_result_2]
    erlang_results_shape_8 = [erlang_result_3, erlang_result_4]

    make_even_table(even_results, 10, "Even distribution")
    make_exp_table(exp_results, 15, "Exponential distribution")
    make_erlang_table(erlang_results_shape_7, 30, "SHAPE 7 Erlang distribution")
    make_erlang_table(erlang_results_shape_8, 30, "SHAPE 8 Erlang distribution")


def make_erlang_table(results, columns_number, description):
    table = Table.make_file_header(description)

    for i in range(2):
        table += Table.make_table_header(i)
        table += Table.make_mean_row(results[i])
        table += Table.make_erlang_mean_misstep_row(results[i])
        table += Table.make_std_row(results[i])
        table += Table.make_erlang_std_misstep_row(results[i], i)
        table += Table.make_varcorf_row(results[i])
        table += Table.make_erlang_varcoef_misstep_row(results[i], i)

    fd = open(description, "w")
    fd.write(table)
    fd.close()


def make_even_table(results, columns_number, description):
    cell_size = (RIGHT_BORDER - LEFT_BORDER)/columns_number

    table = Table.make_file_header(description)

    for i in range(2):
        table += Table.make_table_header(i)
        table += Table.make_mean_row(results[i])
        table += Table.make_even_mean_misstep_row(results[i])
        table += Table.make_std_row(results[i])
        table += Table.make_even_std_misstep_row(results[i])
        table += Table.make_varcorf_row(results[i])
        table += Table.make_even_varcoef_misstep_row(results[i])

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

    table += Table.make_table_close()

    fd = open(description, "w")
    fd.write(table)
    fd.close()


def make_exp_table(results, columns_number, description):
    cell_size = (RIGHT_BORDER - LEFT_BORDER)/columns_number

    table = Table.make_file_header(description)

    for i in range(2):
        table += Table.make_table_header(i)
        table += Table.make_mean_row(results[i])
        table += Table.make_exp_mean_misstep_row(results[i])
        table += Table.make_std_row(results[i])
        table += Table.make_exp_std_misstep_row(results[i])
        table += Table.make_varcorf_row(results[i])
        table += Table.make_exp_varcoef_misstep_row(results[i])

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

    table += Table.make_table_close()

    fd = open(description, "w")
    fd.write(table)
    fd.close()


if __name__ == "__main__":
    main()
