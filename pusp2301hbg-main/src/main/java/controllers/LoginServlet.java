package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author [Group 1-backend-Ibrahim]
 * @version 1.0
 * @since [2023/02/22]
 * A servlet that handles user login functionality. It checks user credentials against a database
 * and sets session attributes based on whether the user is authenticated or not. It also handles
 * redirection to appropriate pages based on the user's role.
 */
@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends ServletBase {
    private static final long serialVersionUID = 1L;

    /**
     * @see ServletBase#ServletBase()
     */
    public LoginServlet() {
        super();
    }

    /**
     * Handles GET requests for the login page. Checks if the user is already authenticated,
     * and if so, redirects them to their appropriate homepage. Otherwise, it displays the
     * login page for the user to enter their credentials.
     *
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws ServletException if there is a servlet-related problem.
     * @throws IOException      if there is an input/output problem.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        String name = request.getParameter("username");
        String password = request.getParameter("password");

        // If either the username or password are not present, display the login page
        if (name == null || password == null) {
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
            return;
        }

        // If the user is the admin user, set their session attributes and redirect them to the admin page
        if (name.equals("admin") && password.equals("admin")) {
            session.setAttribute("state", LOGIN_TRUE);
            session.setAttribute("name", name);
            response.sendRedirect("/admin");
            return;
        }

        // If the user is not a valid user, display an error message on the login page
        if (!database.checkPassword(name, password)) {
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
            return;
        }

        // Set session attributes for the authenticated user and redirect them to the appropriate homepage
        session.setAttribute("state", LOGIN_TRUE);
        session.setAttribute("name", name);
        if (database.isUserPG(name)) {
            response.sendRedirect("/PGHome");
        } else {
            response.sendRedirect("/");
        }
    }

    /**
     * Handles POST requests for the login page. Redirects the request to the doGet() method.
     *
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws ServletException if there is a servlet-related problem.
     * @throws IOException      if there is an input/output problem.
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}