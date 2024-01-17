package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;


@WebServlet(name = "StatsServlet", value = "/stats")
public class StatsServlet extends ServletBase {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // retrieve the current session without creating a new one

        // check that the user is logged in
        String myName = "";
        session = request.getSession(true);
        Object nameObj = session.getAttribute("name");
        if (nameObj != null && loggedIn(request)) {
            myName = (String) nameObj; // if the name exists typecast the name to a string

            Map<String, String> projectMap = new TreeMap<>();
            for (String name : database.getUserProjects(myName)) {
                projectMap.put(name, "<a style='color: black' href=\"statsuser?project=" + name + "\">show</a>");
            }

            request.setAttribute("projectList", projectMap);
            request.getRequestDispatcher("views/stats.jsp").forward(request, response);
        }
        else {
            response.sendRedirect("/login");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
