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
@WebServlet(name = "RemoveTableServlet")
public class RemoveTableServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<Point> list;
        HttpSession session = req.getSession(true);
        if(session.getAttribute("list") == null) {
            list = new ArrayList();
            session.setAttribute("list", list);
        } else {
            list = (ArrayList)session.getAttribute("list");
        }
        PrintWriter fout = resp.getWriter();
        list.clear();
        session.setAttribute("list", list);
    }
}
