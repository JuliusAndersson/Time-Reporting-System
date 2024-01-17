package controllers;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet responsible for handling user logout.
 */
@WebServlet(name = "LogoutServlet", value = "/logout")
public class LogoutServlet extends ServletBase {

    /**
     * Handles the GET request to log out the user by invalidating the current session and redirecting to the login page.
     *
     * @param request  the HttpServletRequest object that contains the parameters passed in from the user
     * @param response the HttpServletResponse object that contains the response to be sent to the user
     * @throws ServletException if an error occurs while handling the servlet request
     * @throws IOException      if an error occurs while handling the servlet request
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // retrieve the current session without creating a new one

        if (session != null) {
            session.invalidate(); // invalidate the session
        }

        response.sendRedirect("/login"); // redirect to the login page
    }

    /**
     * Handles the POST request by calling the doGet method to log out the user.
     *
     * @param request  the HttpServletRequest object that contains the parameters passed in from the user
     * @param response the HttpServletResponse object that contains the response to be sent to the user
     * @throws ServletException if an error occurs while handling the servlet request
     * @throws IOException      if an error occurs while handling the servlet request
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}