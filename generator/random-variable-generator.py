from table import *
from calculations import *
from distributions import *
from histogram import *
import progressbar


def main():
    even_result_1 = []
    even_result_2 = []
    exp_result_1 = []
    exp_result_2 = []
    erlang_result_1 = []
    erlang_result_2 = []
    erlang_result_3 = []
    erlang_result_4 = []

    bar = progressbar.ProgressBar()

    print("Creating hystograms...")
    for i in bar(range(EXPERIMENTS_NUMBER)):
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

    make_table(even_results, "Even distribution", EVEN_SKO, EVEN_VARCOEF, 10)
    make_table(exp_results, "Exponential distribution", EXP_SKO, EXP_VARCOEF, 15)
    make_table(erlang_results_shape, "Erlang distribution shape {}".format(K), ERL_SKO_K, ERL_VARCOEF_K)
    make_table(erlang_results_shape_plus_1, "Erlang distribution shape {}".format(K_PLUS_1), ERL_SKO_K_PLUS_1, ERL_VARCOEF_K_PLUS_1)
    create_file_with_calculated_values()


def make_table(results, description, sko, varcoef, intervals_number=-1):
    cell_size = (RIGHT_BORDER - LEFT_BORDER)/intervals_number
    html = Html(description)
    for i in range(len(results)):
        table = Table()
        table.open_table(i + SEED_1)

        table.add_row("mean", calculate_mean(results[i]))
        table.add_row("mean  error", calculate_mean_error(results[i]))
        table.add_row("sko", calculate_std(results[i]))
        table.add_row("sko error", calculate_std_error(results[i], sko))
        table.add_row("varcoef", calculate_varcoef(results[i]))
        table.add_row("varcoef error", calculate_varcoef_error(results[i], varcoef))

        for c in range(intervals_number):
            row_content = []
            cell_left = LEFT_BORDER+cell_size*c
            cell_right = LEFT_BORDER+cell_size*(c+1)
            row_name = "{0}-{1}".format(round(cell_left), round(cell_right))

            for j in range(SELECTIONS_COUNT):
                in_cell_numbers_counter = 0
                for k in range(len(results[i][j])):
                    if cell_left <= results[i][j][k] < cell_right:
                        in_cell_numbers_counter += 1
                row_content.append(in_cell_numbers_counter)
            table.add_row(row_name, row_content)

        table.close_table()
        html.add_table(table)
    html.close_and_create_file(description)


if __name__ == "__main__":
    main()
