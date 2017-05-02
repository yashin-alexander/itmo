import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import templates.CheckResult;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by alexander on 01.05.17.
 */

@WebServlet(name = "AreaCheckServlet")
public class AreaCheckServlet extends HttpServlet {
    public AreaCheckServlet() {
    }
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

        HttpSession currentSession = req.getSession(true);
        if ( currentSession.getAttribute("prev_results") == null ) {
            currentSession.setAttribute("prev_results", new ArrayList<CheckResult>() );
        }

        PrintWriter out = resp.getWriter();
        String xValue = req.getParameter("x");
        String yValue = req.getParameter("y");
        String rValue = req.getParameter("r");

        Double x = Double.valueOf(Double.parseDouble(xValue));
        Double y = Double.valueOf(Double.parseDouble(yValue));
        Double r = Double.valueOf(Double.parseDouble(rValue));
        String isInside;

        if(this.checkArea(x, y, r))
             isInside = "green";
        else
            isInside = "red";

        Point point = new Point(xValue, y, r, isInside);
        ArrayList<Point> list;

        if(currentSession.getAttribute("list") == null) {
            list = new ArrayList();
            list.add(point);
            currentSession.setAttribute("list", list);
        } else {
            list = (ArrayList)currentSession.getAttribute("list");
            list.add(point);
        }

        HtmlTable table = new HtmlTable(out);
        table.printTableHeader();
        for(int i = 0; i<list.size(); i++){
            table.printRowWithResult(list.get(i));
        }
    }
}
