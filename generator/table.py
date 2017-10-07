class Table:
    def __init__(self, generator):
        self.text = "<table>" \
                     "  <tr>" \
                     "      <td>Generator #{}</td>" \
                     "      <td>10</td>" \
                     "      <td>100</td>" \
                     "      <td>1000</td>" \
                     "      <td>5000</td>" \
                     "      <td>10000</td>" \
                     "      <td>20000</td>" \
                     "  </tr>" \
                     "</table>".format(generator)

    def add_row(self, name, array):
        self.text = self.text[:-8]
        self.text += "<tr>"
        self.text += "<td>{}</td>".format(name)
        for i in range(len(array)):
            self.text += "<td>{}</td>".format(array[i])
        self.text += "</tr>"
        self.text += "</table>"
