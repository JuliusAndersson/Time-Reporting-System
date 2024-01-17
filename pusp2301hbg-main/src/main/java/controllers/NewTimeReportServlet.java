package controllers;

import model.TimeReportBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "NewTimeReportServlet", value = "/timereportnew")
public class NewTimeReportServlet extends ServletBase {
    public NewTimeReportServlet() {
        super();
    }

    /**
     * Handles GET requests for the newTimeReport page. Checks if the user is
     * already authenticated, and if so, redirects them to the page. Otherwise, it
     * displays the login page for the user to enter their credentials. Then it
     * retrieves a list of all the project where the user is a member, if the list
     * is empty it displays an error message.
     *
     * @param request  the HttpServletRequest object that contains the parameters
     *                 passed in from the user
     * @param response the HttpServletResponse object that contains the response to
     *                 be sent to the user
     * @throws ServletException if an error occurs while handling the servlet
     *                          request
     * @throws IOException      if an error occurs while handling the servlet
     *                          request
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // check that the user is logged in
        String myName = "";
        HttpSession session = request.getSession(true);
        Object nameObj = session.getAttribute("name");
        if (nameObj != null) {
            myName = (String) nameObj;
        }
        // retrives the projects from the database, either displays an error message or
        // creates a new report.
        List<String> projectList = new ArrayList<>(database.getUserProjects(myName));
        if (projectList.isEmpty()) {
            System.out.println("true");

            // sets the error message and sets attributes for the JSP file
            String errorMessage = "You aren't a member of any project";
            request.setAttribute("errorMessage", errorMessage);
        }
        // sets the attribute for JSP and runs help-method to create the report
        request.setAttribute("projectList", projectList);
        displayNewTimeReportForm(request, response, myName);
    }

    /**
     * All requests are forwarded to the doGet method.
     *
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Gets all the parameters from the form, checks that all the fields are filled
     * and that nothing is null. Then runs the createTimeReport helper method.
     *
     * @param request
     * @param response
     * @param myName
     * @throws ServletException
     * @throws IOException
     */
    private void displayNewTimeReportForm(HttpServletRequest request, HttpServletResponse response, String myName)
            throws ServletException, IOException {
        // gets all the parameters from the form in the JSP file
        String projectName = request.getParameter("project");
        String userName = myName;
        String reportDate = request.getParameter("date");
        String startTime = reportDate + " " + request.getParameter("start_time");
        String endTime = reportDate + " " + request.getParameter("end_time");
        String activityID = request.getParameter("activity");
        String breakTime = request.getParameter("break_time");


        if (projectName != null && userName != null && startTime != null && endTime != null && reportDate != null
                && activityID != null && breakTime != null) {
            try {
                if (checkInputTime(startTime, endTime, breakTime)) {
                    if (createTimeReport(projectName, userName, startTime, endTime, reportDate, Integer.parseInt(activityID),
                            Integer.parseInt(breakTime), 0)) {

                    } else {
                        String errorMessage = "Invalid inputs";
                        request.setAttribute("errorMessage", errorMessage);
                    }
                }else {
                    String errorMessage = "Invalid inputs";
                    request.setAttribute("errorMessage", errorMessage);
                }
                request.getRequestDispatcher("views/timereportnew.jsp").forward(request, response);
            } catch (Exception e) { // invalid input
                e.printStackTrace();
                request.getRequestDispatcher("views/timereportnew.jsp").forward(request, response);
            }
        } else {
            // Some Input was null, probably because no form has been filled out yet.
            // Display
            // form.
            request.getRequestDispatcher("views/timereportnew.jsp").forward(request, response);
        }
    }

    /**
     * helper method that creates a TimeReportBean and sends it to the database
     * class that creates the report in the database.
     *
     * @param projectName
     * @param username
     * @param startTime
     * @param endTime
     * @param reportDate
     * @param activityId
     * @param breakTime
     * @param reportId
     */
    private boolean createTimeReport(String projectName, String username, String startTime, String endTime,
                                     String reportDate, int activityId, int breakTime, int reportId) {
        TimeReportBean temp = new TimeReportBean();
        temp.setProjectName(projectName);
        temp.setUsername(username);
        temp.setStartTime(startTime);
        temp.setEndTime(endTime);
        temp.setReportDate(reportDate);
        temp.setActivityId(activityId);
        temp.setBreakTime(breakTime);
        temp.setReportId(reportId);
        return database.createReport(temp);
    }

    /**
     * Checks so that the inputs in startTime, endTime and breakTime and correct
     *
     * @param startTime
     * @param endTime
     * @param breakTime
     * @return true if inputs are correct
     */

    private boolean checkInputTime(String startTime, String endTime, String breakTime) {

        var month = Integer.valueOf(startTime.substring(5, 7));
        var day = Integer.valueOf(startTime.substring(8, 10));
        var year = Integer.valueOf(startTime.substring(0, 4));
        if (month < 1 || month > 12|| day<1 || year<1) {
            return false;
        }
        var start = startTime.substring(11).split(":");
        var end = endTime.substring(11).split(":");

        int startSeconds = Integer.parseInt(start[0]) * 3600 + Integer.parseInt(start[1]) * 60 + Integer.parseInt(start[2]);
        int endSeconds = Integer.parseInt(end[0]) * 3600 + Integer.parseInt(end[1]) * 60 + Integer.parseInt(end[2]);
        int breakSeconds = Integer.parseInt(breakTime) * 60;

        if (month < 1 || month > 12|| day<1) {
            return false;
        }
        if (startSeconds < 0 || endSeconds < 0 || breakSeconds < 0) {
            return false;
        } else if ((endSeconds - startSeconds) - breakSeconds < 0) {
            return false;
        } else {
            return true;
        }
    }
}
