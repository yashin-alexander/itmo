import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        return x.doubleValue() >= 0.0D && x.doubleValue() <= r.doubleValue() && y.doubleValue() >= -r.doubleValue() / 2.0D && y.doubleValue() <= 0.0D || x.doubleValue() < 0.0D && y.doubleValue() < 0.0D && x.doubleValue() * x.doubleValue() + y.doubleValue() * y.doubleValue() <= r.doubleValue() * r.doubleValue() || x.doubleValue() < 0.0D && y.doubleValue() > 0.0D && y.doubleValue() <= x.doubleValue() + r.doubleValue() / 2.0D;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        PrintWriter fout = resp.getWriter();
//        fout.println("<h1>r</h1>");
//        fout.flush();
//    }

        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession(true);
        String xValue = req.getParameter("x");
        String yValue = req.getParameter("y");
        String rValue = req.getParameter("r");
        String xxValue = xValue.replaceAll("([0]){2,}", "$1");
        xxValue = xxValue.replaceAll("([1]){2,}", "$1");
        xxValue = xxValue.replaceAll("([2]){2,}", "$1");
        xxValue = xxValue.replaceAll("([3]){2,}", "$1");
        xxValue = xxValue.replaceAll("([4]){2,}", "$1");
        xxValue = xxValue.replaceAll("([5]){2,}", "$1");
        xxValue = xxValue.replaceAll("([6]){2,}", "$1");
        xxValue = xxValue.replaceAll("([7]){2,}", "$1");
        xxValue = xxValue.replaceAll("([8]){2,}", "$1");
        xxValue = xxValue.replaceAll("([9]){2,}", "$1");
        Double x = Double.valueOf(Double.parseDouble(xxValue));
        Double y = Double.valueOf(Double.parseDouble(yValue));
        Double r = Double.valueOf(Double.parseDouble(rValue));
        boolean isInside = this.checkArea(x, y, r);
        Point point = new Point(xValue, y, r, isInside);
        ArrayList list;
        if(session.getAttribute("list") == null) {
            list = new ArrayList();
            list.add(point);
            session.setAttribute("list", list);
        } else {
            list = (ArrayList)session.getAttribute("list");
            list.add(point);
        }

        session.setAttribute("isInside", Boolean.valueOf(isInside));
        String responseType = session.getAttribute("clicked").toString();
        if(responseType.equals("1")) {
            if(isInside) {
                out.print("green");
            } else {
                out.print("red");
            }

        } else {
            String nextJSP = "/searchResults.jsp";
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(req, resp);
        }
    }
}
