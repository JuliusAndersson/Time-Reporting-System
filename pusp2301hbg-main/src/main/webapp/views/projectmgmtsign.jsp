<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project management - signing</title>
    <link rel="stylesheet" href="/css/timereportupdate.css"/>

    <%@ page import="java.util.List" %>
    <%@ include file="menu.jsp" %>
    <%@ page import="model.TimeReportBean" %>


        <% String projectName = (String) request.getAttribute("projectName"); %>
        <% List<TimeReportBean> reportList = (List<TimeReportBean>) request.getAttribute("unsignedList"); %>

    <div id="content">
        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null && !errorMessage.isEmpty()) {
        %>
        <div class="alert alert-danger" role="alert">
            <%= errorMessage %>
        </div>
        <%
            }
        %>

        <h1>SIGN REPORTS</h1>
        <form action="/PGSign" name="reportForm" style="width:80%;">
            <div class="boxBorder" style="width:100%;">
                <div class="tableFixHead">
                    <%-- Table gets information from database and gets html string from Java backend --%>
                    <table>
                        <thead>
                        <tr>
                            <th>Project</th>
                            <th>ID</th>
                            <th>Date</th>
                            <th>Start</th>
                            <th>End</th>
                            <th>Break</th>
                            <th>Activity</th>
                            <th>To Sign</th>
                        </tr>
                        </thead>

                        <div><input type="hidden" name="projectName" value="<%= projectName %>"> </input></div>

                        <tbody>
                        <% for (TimeReportBean reports : reportList) { %>
                        <tr>
                            <td><%= reports.getProjectName() %>
                            </td>
                            <td><%= reports.getReportId()%>
                            </td>
                            <td><%= reports.getReportDate()%>
                            </td>
                            <td><%= reports.getStartTime().substring(10)%>
                            </td>
                            <td><%= reports.getEndTime().substring(10)%>
                            </td>
                            <td><%= reports.getBreakTime()%>
                            </td>
                            <td><%= reports.getActivityId()%>
                            </td>
                            <td><input value="<%= reports.getReportId() %>" name="checkedBoxes" type="checkbox"
                                       class="checkbox"></input></td>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
            <div style="margin-top: 3vh; width: 99%;"><input
                    style="float: right; width: 120px; height: 40px; border-radius: 40px; font-size: 20px;"
                    type="submit" value="Sign" class="blue_button"/></input> </div>
        </form>

    </div>
    </div>
    </body>
    <%@ include file="footer.jsp" %>

