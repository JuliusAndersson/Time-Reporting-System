package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.TimeReportBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UpdateReportServlet", value = "/update-report")
public class UpdateReportServlet extends ServletBase {
	private static final long serialVersionUID = 1L;

	public UpdateReportServlet() {
		super();
	}

	/**
	 * Handles GET requests for the timeReportUpdate page. Checks if the user is
	 * already authenticated, and if so, redirects them to the timeReportupdate
	 * page. Otherwise, it displays the login page for the user to enter their
	 * credentials. The method then calls several helper methods to handle requests
	 * to update or delete submitted timereports.
	 * 
	 * @param request  the HttpServletRequest object that contains the parameters
	 *                 passed in from the user
	 * @param response the HttpServletResponse object that contains the response to
	 *                 be sent to the user
	 * @throws ServletException if an error occurs while handling the servlet
	 *                          request
	 * @throws IOException      if an error occurs while handling the servlet
	 *                          request
	 * 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String myName = "";
		HttpSession session = request.getSession(true);
		Object nameObj = session.getAttribute("name");
		if (nameObj != null) {
			myName = (String) nameObj;
			// handle requests
			handleUpdateReport(request);
			handelDeleteReportRequest(request);
			handelReportUpdateSubmit(request, myName);
			handelReportUpdateView(request);

			// creates a list of all the users unsigned reports
			List<TimeReportBean> reportList = new ArrayList<>(database.getAllUnsignedReportsByUser(myName));
			if (reportList.isEmpty()) {
				System.out.println("true");
				String errorMessage = "You don't have any unsigned reports";
				request.setAttribute("errorMessage", errorMessage);
			}
			// sets attributes for the JSP file and forwards to the timereportupdate JSP
			// file
			request.setAttribute("reportList", reportList);
			request.getRequestDispatcher("views/timereportupdate.jsp").forward(request, response);
		} else {
			// sends user to login if the user was not logged in
			request.getRequestDispatcher("views/login.jsp").forward(request, response);
		}
	}

	private void handleUpdateReport(HttpServletRequest request) {

	}

	/**
	 * All requests are forwarded to the doGet method.
	 *
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Gets the id for the report that the user wants to update, the creates a new
	 * TimeReportBean containing the current values.
	 * 
	 * @param request
	 */
	private void handelReportUpdateView(HttpServletRequest request) {
		String updateId = request.getParameter("update-report-id");
		if (updateId != null && !updateId.isEmpty()) {
			TimeReportBean timeReportBean = database.getReportById(Integer.parseInt(updateId));
			request.setAttribute("timeReport", timeReportBean);

		}
	}

	/**
	 * Gets the id of the report the user wants to delete and removes it form the
	 * database.
	 * 
	 * @param request
	 */

	private void handelDeleteReportRequest(HttpServletRequest request) {
		String deleteId = request.getParameter("delete-report-id");
		if (deleteId != null) {
			if (database.removeReport(Integer.parseInt(deleteId))) {
				System.out.println("deleted");
			}
		}
	}

	/**
	 * Gets all the parameters from the form, checks that all the fields are filled
	 * and that nothing is null. Then creates a new TimeReportBean with the updated
	 * values but with the same reportId and then updates the report in the database
	 * 
	 * @param request
	 * @param myName
	 */
	private void handelReportUpdateSubmit(HttpServletRequest request, String myName) {

		String activityId = request.getParameter("activityId");
		String breakTime = request.getParameter("breakTime");
		String projectName = request.getParameter("projectName");
		String reportDate = request.getParameter("reportDate");
		String endTime = reportDate + " " + request.getParameter("endTime");
		String startTime = reportDate + " " + request.getParameter("startTime");
		String reportId = request.getParameter("reportId");
		
		
		if (activityId != null && breakTime != null && projectName != null && reportDate != null && reportId != null) {
			System.out.println(startTime);
			System.out.println(endTime);
			if (checkInputTime(startTime, endTime, breakTime)) {
				TimeReportBean timeReportBean = new TimeReportBean();
				timeReportBean.setReportId(Integer.parseInt(reportId));
				timeReportBean.setBreakTime(Integer.parseInt(breakTime));
				timeReportBean.setReportDate(reportDate);
				timeReportBean.setEndTime(endTime);
				timeReportBean.setStartTime(startTime);
				timeReportBean.setActivityId(Integer.parseInt(activityId));
				timeReportBean.setProjectName(projectName);
				timeReportBean.setUsername(myName);
				if (database.updateReport(Integer.parseInt(reportId), timeReportBean)) {
					request.setAttribute("errorM", "Error");
				} else {
					String errorMessage = "Invalid inputs";
					request.setAttribute("errorMessage", errorMessage);
				}

			}else {
				String errorMessage = "Invalid inputs";
				request.setAttribute("errorMessage", errorMessage);
			}
			}
			
			
			
		
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
		var start = startTime.substring(12).split(":");
		var end = endTime.substring(12).split(":");

		int startSeconds = Integer.parseInt(start[0]) * 3600 + Integer.parseInt(start[1]) * 60 + Integer.parseInt(start[2]);
		int endSeconds = Integer.parseInt(end[0]) * 3600 + Integer.parseInt(end[1]) * 60 + Integer.parseInt(end[2]);
		int breakSeconds = Integer.parseInt(breakTime) * 60;

		if (startSeconds < 0 || endSeconds < 0 || breakSeconds < 0) {
			return false;
		} else if ((endSeconds - startSeconds) - breakSeconds < 0) {
			return false;
		} else {
			return true;
		}		
	}
}
