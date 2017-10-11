from constants import *


class Table:
    def __init__(self):
        self.content = ""

    def open_table(self, generator):
        self.content += "<table>"
        self.add_row("Generator {}".format(generator), NUMBER_OF_VARIABLES)

    def add_row(self, name, array):
        self.content += "<tr>"
        self.add_cell(name)
        for i in range(len(array)):
            self.add_cell(array[i])
        self.content += "</tr>\n"

    def add_cell(self, content):
        self.content += "<td>"
        self.content += str(content)
        self.content += "</td>"

    def close_table(self):
        self.content += "</table>\n"
