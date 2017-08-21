/**
 * Created by alexander on 04.07.17.
 */

    function tablePrintHeader() {
        var EndedRow = "";

        EndedRow += "<table id=\"results-table\"><tr>";
        EndedRow += "<td> X </td><td> Y </td><td> R </td><td> isInside </td><td> color </td>";
        EndedRow += "</tr>";
        return EndedRow;
    }


    function tableAddRow(x, y, r, isInside, color) {
        var EndedRow = "";

        EndedRow += "<tr>";
        EndedRow += "<td>" + x + "</td><td>" + y + "</td><td>"
            + r + "</td><td>" + isInside + "</td><td>" + color + "</td>";
        EndedRow += "</tr>";
        return EndedRow;
    }


    function closeTable() {
        return ("</table>");
    }