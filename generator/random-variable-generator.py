import plotly
import plotly.graph_objs as go
from table import Table
from calculations import *
from distributions import *
from histogram import *

plotly.tools.set_credentials_file(username='yashin_alexander', api_key='Fx4bpffs3QI4dT7SXtqR')


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

        erlang_result_1.append(erlang_distribution(SEED_1, current_variables_number, K))
        erlang_result_2.append(erlang_distribution(SEED_2, current_variables_number, K))

        erlang_result_3.append(erlang_distribution(SEED_1, current_variables_number, K + 1))
        erlang_result_4.append(erlang_distribution(SEED_2, current_variables_number, K + 1))

        histogram_name = "even{}".format(current_variables_number)
        create_histogram(histogram_name, 20, even_result_1[i], even_result_2[i])
        histogram_name = "exp{}".format(current_variables_number)
        create_histogram(histogram_name, 40, exp_result_1[i], exp_result_2[i])
        histogram_name = "shape{}erl{}".format(K, current_variables_number)
        create_histogram(histogram_name, 40, erlang_result_1[i], erlang_result_2[i])

    even_results = [even_result_1, even_result_2]
    exp_results = [exp_result_1, exp_result_2]
    erlang_results_shape = [erlang_result_1, erlang_result_2]
    erlang_results_shape_plus_1 = [erlang_result_3, erlang_result_4]

    make_table(even_results, 10, "Even distribution", EVEN_SKO, EVEN_VARCOEF)
    make_table(exp_results, 15, "Exponential distribution", EXP_SKO, EXP_VARCOEF)
    make_table(erlang_results_shape, -1, "Erlang distribution shape {}".format(K), ERL_SKO_K, ERL_VARCOEF_K)
    make_table(erlang_results_shape_plus_1, -1, "Erlang distribution shape {}".format(K_PLUS_1), ERL_SKO_K_PLUS_1, ERL_VARCOEF_K_PLUS_1)

    create_file_with_calculated_values()


def make_table(results, intervals_number, description, sko, varcoef):
    cell_size = (RIGHT_BORDER - LEFT_BORDER)/intervals_number
    html = Html(description)
    for i in range(2):
        table = Table(i + SEED_1)
        table.add_row("mean", calculate_mean(results[i]))
        table.add_row("mean  error", calculate_mean_error(results[i]))
        table.add_row("sko", calculate_std(results[i]))
        table.add_row("sko error", calculate_std_error(results[i], sko))
        table.add_row("varcoef", calculate_varcoef(results[i]))
        table.add_row("varcoef error", calculate_varcoef_error(results[i], varcoef))

        for l in range(intervals_number):
            row = []
            cell_left = LEFT_BORDER+cell_size*l
            cell_right = LEFT_BORDER+cell_size*(l+1)
            row_name = "{0}-{1}".format(round(cell_left), round(cell_right))
            for j in range(6):
                counter = 0
                for k in range(len(results[i][j])):
                    if cell_left <= results[i][j][k] < cell_right:
                        counter += 1
                row.append(counter)
            table.add_row(row_name, row)

        html.add_table(table)

    html.create_file(description)


if __name__ == "__main__":
    main()
