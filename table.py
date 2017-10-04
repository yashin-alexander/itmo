from constants import *
from numpy import random, mean, var, std


class Table:
    def __init__(self):
        pass

# file

    def make_file_header(description):
        header = "<html>" \
                 "<head>" \
                 "{CSS_FILE}" \
                 "</head>" \
                 "<body>" \
                 "<table>" \
                 "<tr><td>{}</td></tr>".format(description, CSS_FILE=CSS_FILE)

        return header

    @staticmethod
    def make_table_close():
        return "</table></body></html>"

# table

    @staticmethod
    def make_table_header(table_number=0):
        header = "<tr><td>generator {}</td></tr>".format(table_number + SEED_1)
        header += "<tr><td> </td><td>10</td><td>100</td><td>1000</td><td>5000</td><td>10000</td><td>20000</td></tr>"

        return header

# mean,std, varcoef

    @staticmethod
    def make_mean_row(array):
        row = "<tr><td>mean</td>"
        for j in range(6):
            row += "<td>{}</td>".format(round(mean(array[j]), 3))

        row += "</tr>"
        return row

    @staticmethod
    def make_std_row(array):
        row = "<tr><td>stdev</td>"
        for j in range(6):
            row += "<td>{}</td>".format(round(std(array[j]), 3))

        row += "</tr>"
        return row

    @staticmethod
    def make_varcorf_row(array):
        row = "<tr><td>varcoef</td>"
        for j in range(6):
            row += "<td>{}</td>".format(round(std(array[j]) / mean(array[j]), 3))

        row += "</tr>"
        return row

# even mistakes

    @staticmethod
    def make_even_mean_misstep_row(array):
        row = "<tr><td>mistake</td>"
        for j in range(6):
            row += "<td>{}</td>".format(abs(round((mean(array[j]) - EXPECTED_VALUE) / EXPECTED_VALUE, 4)))

        row += "</tr>"
        return row

    @staticmethod
    def make_even_std_misstep_row(array):
        print("even_sko = ", EVEN_SKO)
        row = "<tr><td>mistake</td>"
        for j in range(6):
            row += "<td>{}</td>".format(abs(round((std(array[j]) - EVEN_SKO) / EVEN_SKO, 4)))

        row += "</tr>"
        return row

    @staticmethod
    def make_even_varcoef_misstep_row(array):
        print("even_varcoef = ", EVEN_VARCOEF)
        row = "<tr><td>mistake</td>"
        for j in range(6):
            row += "<td>{}</td>".format(abs(round((std(array[j])/mean(array[j]) - EVEN_VARCOEF) / EVEN_VARCOEF, 4)))

        row += "</tr>"
        return row

# exp mistakes

    @staticmethod
    def make_exp_mean_misstep_row(array):
        row = "<tr><td>mistake</td>"
        for j in range(6):
            row += "<td>{}</td>".format(abs(round((mean(array[j]) - EXPECTED_VALUE) / EXPECTED_VALUE, 4)))

        row += "</tr>"
        return row

    @staticmethod
    def make_exp_std_misstep_row(array):
        print("exp_sko = ", EXP_SKO)

        row = "<tr><td>mistake</td>"
        for j in range(6):
            row += "<td>{}</td>".format(abs(round((std(array[j]) - EXP_SKO) / EXP_SKO, 4)))

        row += "</tr>"
        return row

    @staticmethod
    def make_exp_varcoef_misstep_row(array):
        row = "<tr><td>mistake</td>"
        for j in range(6):
            row += "<td>{}</td>".format(abs(round((std(array[j]) / mean(array[j]) - 1), 4)))

        row += "</tr>"
        return row

# erlang mistakes

    @staticmethod
    def make_erlang_mean_misstep_row(array):
        row = "<tr><td>mistake</td>"
        for j in range(6):
            row += "<td>{}</td>".format(abs(round((mean(array[j]) - EXPECTED_VALUE) / EXPECTED_VALUE, 4)))

        row += "</tr>"
        return row

    @staticmethod
    def make_erlang_std_misstep_row(array, shape):
        shape += SHAPE
        erlang_sko = (math.sqrt(shape)*EXPECTED_VALUE)/shape
        print("shape = ", shape, " erlang_sko = ", erlang_sko)

        row = "<tr><td>mistake</td>"
        for j in range(6):
            row += "<td>{}</td>".format(abs(round((std(array[j]) - erlang_sko) / erlang_sko, 4)))

        row += "</tr>"
        return row

    @staticmethod
    def make_erlang_varcoef_misstep_row(array, shape):
        shape += SHAPE
        erlang_varcoef = math.sqrt(shape) / shape
        print("shape = ", shape, " erlang_varcoef = ", erlang_varcoef)

        row = "<tr><td>mistake</td>"
        for j in range(6):
            row += "<td>{}</td>".format(abs(round((std(array[j]) / mean(array[j]) - erlang_varcoef)/erlang_varcoef, 4)))

        row += "</tr>"
        return row