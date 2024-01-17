package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PGHomeServlet", value = "/PGHome")
public class PGHomeServlet extends ServletBase {
    private static final long serialVersionUID = 1L;

    /**
     * @see ServletBase#ServletBase()
     */
    public PGHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, IOException {

        String myName = "";
        HttpSession session = request.getSession(true);
        Object nameObj = session.getAttribute("name");

        if (nameObj != null)
            myName = (String) nameObj; // if the name exists typecast the name to a string

        // check that the user is logged in and is a PG.
        if (myName.equals("admin")) {
            request.setAttribute("projectList", database.getProjects());
            request.getRequestDispatcher("views/projectmgmt.jsp").forward(request, response);

        } else if (database.isUserPG(myName)) {
            request.setAttribute("projectList", database.getPGProjects(myName));
            request.getRequestDispatcher("views/projectmgmt.jsp").forward(request, response);
        } else {
            String errorMessage = "You do not have permission to access this page.";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/").forward(request, response);
        }


    }


    /**
     *
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
    }
}