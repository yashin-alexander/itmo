import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by alexander on 03.05.17.
 */
@WebServlet(name = "ChangeRadiusControllerServlet")
public class ChangeRadiusControllerServlet extends HttpServlet {

    private boolean checkArea(Double x, Double y, Double r) {
        if(x>0&&y>0)
            return (x<r/2 && y<r && y<-2*x+r);
        if(x>=0&&y<=0)
            return (x<r/2 && y>-r);
        if(x<0&&y<0)
            return(x>-r/2&&y>-r/2&&x*x+y*y<r*r/4);
        else
            return false;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter qout = resp.getWriter();
        HttpSession session = req.getSession(true);
        ArrayList<Point> list;
        String rValue = req.getParameter("r");

        if(session.getAttribute("list") == null) {
            list = new ArrayList();
            session.setAttribute("list", list);
        } else {
            list = (ArrayList)session.getAttribute("list");
        }

        HtmlTable table = new HtmlTable(qout);
        table.printTableHeader();

        for(int i = 0; i<Integer.valueOf(list.size()); i++){
            Point point = list.get(i);

            Double x = Double.valueOf(Double.parseDouble(point.getX()));
            Double y = Double.valueOf(Double.parseDouble(point.getY()));
            Double r = Double.valueOf(Double.parseDouble(rValue));
            if (checkArea(x,y,r))
                point.color = "green";
            else
                point.color = "red";
            table.printRowWithResult(point);
        }
        table.closeTable();
    }
}
