package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberBean;
import model.TimeReportBean;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@WebServlet(name = "StatsuserServlet", value = "/statsuser")
public class StatuserServlet extends ServletBase {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // retrieve the current session without creating a new one

        // check that the user is logged in
        String myName = "";
        session = request.getSession(true);
        Object nameObj = session.getAttribute("name");
        String projectName = request.getParameter("project");
        // check that the user is logged in
        if (projectName != null && nameObj != null) {
            myName = (String) nameObj; // if the name exists typecast the name to a string
            String user = request.getParameter("user");
            boolean isPg = database.getRole(myName, projectName).equals("PG");

            if(user != null){
                myName = user;
            }

            List<TimeReportBean> test = new ArrayList<>();
            for (TimeReportBean bean: database.getAllReportsByProject(projectName)) {
                if(bean.getSigned()){
                    test.add(bean);
                }
            }

            List<TimeReportBean> timeReports = new ArrayList<>();
            if(myName == null || myName.equals("All")){ //
                for (TimeReportBean bean : database.getAllReportsByProject(projectName) /* <- namn för tiden som hämtas */) {
                    if(bean.getSigned()){
                        timeReports.add(bean);
                    }
                }
            }
            else{
                for (TimeReportBean bean : database.getAllReportsByUser(myName) /* <- namn för tiden som hämtas */) {
                    if(bean.getProjectName().equals(projectName) && bean.getSigned()){
                        timeReports.add(bean);
                    }
                }
            }


            //Group by activity
            TreeMap<Integer, Double> timeMap = SumActivityTime(timeReports, projectName);

            //Group by role
            Map<String, Double> timeMapByRole = getStatisticsByRole(projectName);

            //Sum total time
            double total = 0;
            for (Map.Entry<Integer, Double> r : timeMap.entrySet()) {
                total += r.getValue();
            }

            //Attributes for displaying data by activity ID
            request.setAttribute("select", user);
            request.setAttribute("curProject", projectName);
            request.setAttribute("totalTime", formatingNumber(total));
            request.setAttribute("11", timeMap.getOrDefault(11, 0.0));
            request.setAttribute("12", timeMap.getOrDefault(12, 0.0));
            request.setAttribute("13", timeMap.getOrDefault(13, 0.0));
            request.setAttribute("14", timeMap.getOrDefault(14, 0.0));
            request.setAttribute("15", timeMap.getOrDefault(15, 0.0));
            request.setAttribute("16", timeMap.getOrDefault(16, 0.0));
            request.setAttribute("17", timeMap.getOrDefault(17, 0.0));
            request.setAttribute("18", timeMap.getOrDefault(18, 0.0));
            request.setAttribute("19", timeMap.getOrDefault(19, 0.0));
            request.setAttribute("21", timeMap.getOrDefault(21, 0.0));
            request.setAttribute("22", timeMap.getOrDefault(22, 0.0));
            request.setAttribute("23", timeMap.getOrDefault(23, 0.0));
            request.setAttribute("30", timeMap.getOrDefault(30, 0.0));
            request.setAttribute("41", timeMap.getOrDefault(41, 0.0));
            request.setAttribute("42", timeMap.getOrDefault(42, 0.0));
            request.setAttribute("43", timeMap.getOrDefault(43, 0.0));
            request.setAttribute("44", timeMap.getOrDefault(44, 0.0));
            request.setAttribute("100", timeMap.getOrDefault(100, 0.0));

            //Attributes for displaying data grouped by Role
            request.setAttribute("PG", timeMapByRole.getOrDefault("PG", 0.0));            request.setAttribute("SG", timeMapByRole.getOrDefault("", 0.0));
            request.setAttribute("DG", timeMapByRole.getOrDefault("DG", 0.0));
            request.setAttribute("TG", timeMapByRole.getOrDefault("TG", 0.0));
            request.setAttribute("SG", timeMapByRole.getOrDefault("SG", 0.0));


            if (isPg) {    //redirect to statsuser
                List<String> memberList = new ArrayList<>();
                for (MemberBean bean : database.getProjectMembers(projectName)) {
                    memberList.add(bean.getName());
                }
                memberList.add("All");
                memberList.sort((u1, u2) -> u1.compareTo(u2));
                request.setAttribute("members", memberList);
                request.getRequestDispatcher("views/statsuser.jsp").forward(request, response);
            } else {   //redirect to statsuserNotPG

                request.setAttribute("nameUser", myName);
                request.getRequestDispatcher("views/statsuserNotPg.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("/login");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private TreeMap<Integer, Double> SumActivityTime(List<TimeReportBean> reportBeans, String projectName) {
        TreeMap<Integer, Double> activityMap = new TreeMap<>();
        for (TimeReportBean bean : reportBeans) {
            if (bean.getProjectName().equals(projectName)) {
                if (!activityMap.containsKey(bean.getActivityId())) {
                    activityMap.put(bean.getActivityId(), GetTimeDifference(bean));
                } else {
                    activityMap.put(bean.getActivityId(), activityMap.get(bean.getActivityId()) + GetTimeDifference(bean));
                }
            }
        }
        return activityMap;
    }

    private double GetTimeDifference(TimeReportBean bean) {
        String startTime = bean.getStartTime();
        String endTime = bean.getEndTime();
        int startYear = Integer.parseInt(startTime.substring(0, 4));
        int startMonth = Integer.parseInt(startTime.substring(5, 7));
        int startDay = Integer.parseInt(startTime.substring(8, 10));
        int startHour = Integer.parseInt(startTime.substring(11, 13));
        int startMinute = Integer.parseInt(startTime.substring(14, 16));
        int startSecond = Integer.parseInt(startTime.substring(17, 19));

        int endYear = Integer.parseInt(endTime.substring(0, 4));
        int endMonth = Integer.parseInt(endTime.substring(5, 7));
        int endDay = Integer.parseInt(endTime.substring(8, 10));
        int endHour = Integer.parseInt(endTime.substring(11, 13));
        int endMinute = Integer.parseInt(endTime.substring(14, 16));
        int endSecond = Integer.parseInt(endTime.substring(17, 19));
        double breakTime = (double) bean.getBreakTime() / 60;

        LocalDateTime dateTime1 = LocalDateTime.of(startYear, startMonth, startDay, startHour, startMinute, startSecond);
        LocalDateTime dateTime2 = LocalDateTime.of(endYear, endMonth, endDay, endHour, endMinute, endSecond);
        Duration duration = Duration.between(dateTime1, dateTime2);
        double result = (((double) duration.toMinutes() % 60) / 60 + (double) duration.toHours()) - breakTime;
        if (result < 0) {
            throw new ArithmeticException("ERROR: Negative sum, check that endTime is after startTime.");
        }

        return formatingNumber(result);
    }

    private Double formatingNumber(Double nbr) {
        nbr *= 10;
        nbr = Math.floor(nbr + 0.5);
        nbr /= 10;
        return nbr;
    }



    private Map<String, Double> getStatisticsByRole(String projectName) {
        Map<String, Double> map = new HashMap<>();
        for (TimeReportBean bean: database.getAllReportsByProject(projectName)) {
            if(bean.getSigned()){
                String pName = bean.getProjectName();
                String uName = bean.getUsername();
                String role = database.getRole(uName, pName);
                if (pName.equals(projectName)) {

                    if (map.containsKey(role)) {
                        map.put(role, map.get(role) + GetTimeDifference(bean));
                    } else {
                        map.put(role, GetTimeDifference(bean));
                    }
                }
            }
        }
        return map;
    }
}
