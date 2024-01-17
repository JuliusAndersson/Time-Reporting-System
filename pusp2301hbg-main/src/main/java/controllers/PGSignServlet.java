package controllers;

import model.TimeReportBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PGSignServlet", value = "/PGSign")
public class PGSignServlet extends ServletBase {
    private static final long serialVersionUID = 1L;

    /**
     * @see ServletBase#ServletBase()
     */
    public PGSignServlet() {
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

        // check that the user is logged in and is a PG or admin.
        if (!myName.equals("admin")) {

            if (!database.isUserPG(myName)) {
                String errorMessage = "You do not have permission to access this page.";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("/").forward(request, response);
                return;
            }
        }

        String projectName = (String) request.getParameter("projectName");


        // handle requests
        signReports(request);

        // set attributes for JSP
        List<TimeReportBean> timeReportBeans = getUnsignedReports(projectName);
        if (timeReportBeans.isEmpty()) {
            String errorMessage = "There are no reports to sign!";
            request.setAttribute("errorMessage", errorMessage);
        }
        request.setAttribute("unsignedList", timeReportBeans);
        request.setAttribute("projectName", projectName);

        // forward request to JSP
        request.getRequestDispatcher("views/projectmgmtsign.jsp").forward(request, response);


    }

    List<TimeReportBean> getUnsignedReports(String project) {
        return database.getAllUnsignedReports(project);
    }

    private void signReports(HttpServletRequest request) {
        String[] unsigned = (String[]) request.getParameterValues("checkedBoxes");
        if (unsigned != null) {
            for (String reportId : unsigned) {
                database.signReport(Integer.parseInt(reportId));
            }
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