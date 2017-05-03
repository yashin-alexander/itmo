import javax.servlet.RequestDispatcher;
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
 * Created by alexander on 30.04.17.
 */
@WebServlet(name = "ControllerServlet")
public class ControllerServlet extends HttpServlet {
    public ControllerServlet() {
    }

    public boolean isNumeric(String val) {
        return val.matches("[-+]?\\d*\\.?\\d+");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String clear = req.getParameter("clear");
        String changer = req.getParameter("changer");
//
//        qout.println("HqqqqqUI\n");

        if (clear.toString() == "") {
            PrintWriter fout = resp.getWriter();
            HttpSession session = req.getSession(true);
            ArrayList<Point> list = (ArrayList)session.getAttribute("list");
//            fout.print("<td>" + Integer.valueOf(list.size()) + "</td>");
            list.clear();
            session.setAttribute("list", list);
        }
        else if(changer.toString() == ""){
            String rValue = req.getParameter("r");
            String checkRequest = "/change_radius_servlet?r=" + rValue;
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(checkRequest);
            dispatcher.include(req, resp);
        }
        else {
            PrintWriter out = resp.getWriter();
            HttpSession session = req.getSession(true);
            String xValue = req.getParameter("x");
            String yValue = req.getParameter("y");
            String rValue = req.getParameter("r");

            String clicked = req.getParameter("clicked");
            session.setAttribute("clicked", clicked);
            if (this.isNumeric(xValue) && this.isNumeric(yValue) && this.isNumeric(rValue)) {
                if (Double.parseDouble(xValue) >= -4 && Double.parseDouble(xValue) <= 4 && Double.parseDouble(yValue) >= -5 && Double.parseDouble(yValue) <= 3 && Double.parseDouble(rValue) > 1 && Double.parseDouble(rValue) < 4) {
                    String checkRequest = "/area_check_servlet?x=" + xValue + "&y=" + yValue + "&r=" + rValue;
                    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(checkRequest);
                    dispatcher.include(req, resp);
                } else {
                    out.println("incorrect values");
                }
            } else {
                out.println("incorrect AAvalues\n");
                out.println(xValue);
                out.println(yValue);
                out.println(rValue);
            }
        }
    }
}
