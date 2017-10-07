from constants import *
import os


class Html:
    def __init__(self, description):
        self.text = "<html>" \
                    "   <head>" \
                    "       {CSS_FILE}" \
                    "   </head>" \
                    "   <body>" \
                    "       {}".format(description, CSS_FILE=CSS_FILE)

    def add_table(self, table):
        self.text += table.text

    def add_text(self, text):
        self.text += "<p>"
        self.text += text
        self.text += "</p>"

    def create_file(self, name):
        path = "./htmls/"
        if not os.path.exists(path):
            os.mkdir(path)
        self.text += "  </body>" \
                     "</html>"
        path += name
        fd = open(path, "w")
        fd.write(self.text)
        fd.close()
