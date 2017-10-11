from constants import *
import os


class Html:
    def __init__(self, description):
        self.content = "<html>\n"\
                    "       <head>\n"\
                    "        {CSS_FILE}\n"\
                    "       </head>\n" \
                    "       <body>\n"\
                    "           {}\n".format(description, CSS_FILE=CSS_FILE)

    def add_table(self, table):
        self.content += table.content

    def add_text(self, text):
        self.content += "<p>"
        self.content += text
        self.content += "</p>\n"

    def close_and_create_file(self, name):
        self.content += "  </body>\n" \
                     "</html>\n"
        path = "./htmls/"
        if not os.path.exists(path):
            os.mkdir(path)
        path += name
        fd = open(path, "w")
        fd.write(self.content)
        fd.close()
