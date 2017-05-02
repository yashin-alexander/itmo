import java.io.PrintWriter;

/**
 * Created by alexander on 02.05.17.
 */
public class HtmlTable {
    private  PrintWriter toClientStream;

    public HtmlTable( PrintWriter toClientStream ) {
        this.toClientStream = toClientStream;
    }


    public void printRowWithResult( Point result ) {

        toClientStream.println("<tr>");
        printColumnWith( String.valueOf(result.getX()) );
        printColumnWith( String.valueOf(result.getY()) );
        printColumnWith( String.valueOf(result.getR()) );
        printColumnWith( result.getIsInside());
        toClientStream.println("</tr>");
    }

    public void printTableHeader( ) {
        toClientStream.println("<table id=\"results-table\">");
        toClientStream.println("<tr>");
        printHeadColumnWith( "X");
        printHeadColumnWith( "Y");
        printHeadColumnWith( "R");
        printHeadColumnWith( "Result");
        toClientStream.println("</tr>");
    }

    public void closeTable() {
        toClientStream.println("</table>");
    }

    public void printColumnWith( String content ) {
        toClientStream.println("<td>");
        toClientStream.println(content);
        toClientStream.println("</td>");
    }

    public void printHeadColumnWith( String content ) {
        toClientStream.println("<th>");
        toClientStream.println(content);
        toClientStream.println("</th>");
    }
}
