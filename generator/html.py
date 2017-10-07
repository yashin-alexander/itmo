from constants import *


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
        self.text += "  </body>" \
                     "</html>"
        fd = open(name, "w")
        fd.write(self.text)
        fd.close()
