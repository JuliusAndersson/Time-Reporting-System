package controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author [Group 1-backend-Ibrahim]
 * @version 1.0
 * @since [2023/02/22]
 * This class handles the requests coming from the admin page.
 * <p>
 * It provides functionalities for adding and deleting users, creating and deleting projects,
 * <p>
 * and updating user passwords.
 * <p>
 * It also displays a list of all users and projects in the database.
 */
@WebServlet(name = "AdministrationServlet", value = "/admin")
public class AdministrationServlet extends ServletBase {
    private static final long serialVersionUID = 1L;
    private static final int PASSWORD_LENGTH = 6;

    /**
     * @see ServletBase#ServletBase()
     */
    public AdministrationServlet() {
        super();
    }

    /**
     * Overrides the doGet method of the HttpServlet class to handle the GET request made to the Admin page.
     * The method first retrieves the current HttpSession object or creates a new one if it does not exist.
     * Then, it checks if the user has permission to access the page. If the user is not the admin, the method sets an error message and forwards the request to the homepage.
     * The method then calls several helper methods to handle requests to add, delete, or update users and projects.
     * After that, it sets the attributes of the request to be used by the JSP page and forwards the request to the AdminPage.jsp view.
     *
     * @param request  the HttpServletRequest object that contains the parameters passed in from the user
     * @param response the HttpServletResponse object that contains the response to be sent to the user
     * @throws ServletException if an error occurs while handling the servlet request
     * @throws IOException      if an error occurs while handling the servlet request
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String myName = (String) session.getAttribute("name");
        if (!myName.equals("admin")) {
            String errorMessage = "You do not have permission to access this page.";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/").forward(request, response);
            return;
        }

        // handle requests
        handleAddUser(request);
        handleDeleteUser(request);
        handleUpdatePassword(request);
        handleCreateProject(request);
        handleDeleteProject(request);

        // set attributes for JSP
        request.setAttribute("userList", database.getUsers());
        request.setAttribute("projectList", database.getProjects());

        // forward request to JSP
        request.getRequestDispatcher("views/AdminPage.jsp").forward(request, response);
    }

    /**
     * This method handles the request for adding a user.
     * <p>
     * It takes a username and password as input, validates them, and adds the user to the database.
     * <p>
     * If the username already exists in the database, it sets an error message to be displayed on the JSP.
     *
     * @param request the HttpServletRequest object containing the user input data
     */
    private void handleAddUser(HttpServletRequest request) {
        // Get the user input for username and password
        String newName = request.getParameter("addname");
        String newPass = request.getParameter("addpass");

        // Validate the username and password, and add the user to the database
        if (newName != null && checkNewName(newName) && newPass != null && checkNewPass(newPass)) {
            if (database.createUser(newName, newPass)) {
                request.setAttribute("success", "User added successfully!");
            } else {
                request.setAttribute("errorM", "Username already exists");
            }
        } else if (newName != null) {
            request.setAttribute("errorM", "Invalid user name or password");
        }
    }


    /**
     * This method handles the request for deleting a user.
     * <p>
     * It takes a username as input, validates it, and deletes the user from the database.
     * <p>
     * If the user does not exist in the database, it sets an error message to be displayed on the JSP.
     *
     * @param request the HttpServletRequest object containing the user input data
     */
    private void handleDeleteUser(HttpServletRequest request) {
// Get the user input for the username to delete
        String deleteName = request.getParameter("deletename");

// Validate the username and delete the user from the database
        if (deleteName != null) {
            if (database.removeUser(deleteName)) {
                request.setAttribute("success", "User deleted");
            } else {
                request.setAttribute("errorM", "Something went wrong");
            }
        }
    }

    /**
     * Creates a new project with the given project name and adds it to the database.
     *
     * @param request an HttpServletRequest object that contains the parameters passed in from the user
     */
    private void handleCreateProject(HttpServletRequest request) {
        String projectName = request.getParameter("projectName");

        if (projectName != null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
            String st = dtf.format(now);
            if (database.createProject(projectName, st, null)) {
                request.setAttribute("success", "Project Created");
            } else {
                request.setAttribute("errorM", "Project already exists");
            }
        }
    }

    /**
     * Deletes the project with the given project name from the database.
     *
     * @param request an HttpServletRequest object that contains the parameters passed in from the user
     */
    private void handleDeleteProject(HttpServletRequest request) {
        String projectName = request.getParameter("deleteProject");
        if (projectName != null) {
            if (database.removeProject(projectName)) {
                request.setAttribute("success", "Project deleted");
            } else {
                request.setAttribute("errorM", "project does not exist");
            }
        }
    }


    /**
     * Updates the password for a given user in the database.
     *
     * @param request an HttpServletRequest object that contains the parameters passed in from the user
     */
    private void handleUpdatePassword(HttpServletRequest request) {
        String updateUser = request.getParameter("updateUser");
        String newPassword = request.getParameter("newPassword");

        if (updateUser != null && checkNewName(updateUser) && newPassword != null && checkNewPass(newPassword)) {
            if (database.changePassword(updateUser, newPassword)) {
                request.setAttribute("success", "Password updated successfully!");
            } else {
                request.setAttribute("errorM", "Failed to update password");
            }
        } else if (updateUser != null) {
            request.setAttribute("errorM", "Invalid password");
        }
    }


    /**
     * Checks if a given name is valid (5-10 alphanumeric characters).
     *
     * @param name the name to check
     * @return true if the name is valid, false otherwise
     */
    private boolean checkNewName(String name) {
        // Check if username length is between 8 and 16 characters
        if (name.length() < 8 || name.length() > 16) {
            return false;
        }

        // Check if username only contains alphanumeric characters and underscores
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]+$");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }


    private boolean checkNewPass(String pass) {
        // Check if password length is between 8 and 16 characters
        if (pass.length() < 8 || pass.length() > 16) {
            return false;
        }

        // Check if password has at least 2 letters and 2 numbers
        int numLetters = 0;
        int numNumbers = 0;
        for (int i = 0; i < pass.length(); i++) {
            char c = pass.charAt(i);
            if (Character.isLetter(c)) {
                numLetters++;
            } else if (Character.isDigit(c)) {
                numNumbers++;
            }
        }
        if (numLetters < 2 || numNumbers < 2) {
            return false;
        }

        // Check if password only contains ASCII values 48-57, 65-90
        String regex = "^[0-9A-Za-z]*$";
        return pass.matches(regex);
    }


}
