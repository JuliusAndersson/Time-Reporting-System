<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Time report - update</title>
    <link rel="stylesheet" href="/css/timereportupdate.css"/>
    <link rel="stylesheet" href="/css/timereportnew.css"/>

    <%@ page import="model.TimeReportBean" %>
    <%@ page import="java.util.List" %>

        <% List<TimeReportBean> reportList = (List<TimeReportBean>) request.getAttribute("reportList"); %>
        <% TimeReportBean timeReport = (TimeReportBean) request.getAttribute("timeReport"); %>

    <%@ include file="menu.jsp" %>
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

        <h1>UNSIGNED REPORTS</h1>

        <div class="boxBorder">
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
                        <th>Update</th>
                        <th>Delete test</th>
                    </tr>
                    </thead>
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

                        <td><a href="/update-report?update-report-id=<%= reports.getReportId() %>" class="blue_button">
                            <button class="blue_button">Update</button>
                        </a></td>
                        <td><a href="/update-report?delete-report-id=<%= reports.getReportId() %>" class="blue_button">
                            <button class="blue_button">Delete</button>
                        </a></td>

                    </tr>
                    <% } %>
                    </tbody>

                </table>
            </div>
        </div>

        <% if (timeReport != null) { %>
        <div id="border_template">
            <div id="border_template_inner">

                <!-- form that will send the values with
                date, start_time, end_time, break_time, activity
                when submit is pressed -->
                <form id="inner_div" action="/update-report">


                    <!-- Text areas for submit-->
                    <div id="text_div"><p>Project</p></br>
                        <input type="text" readonly id="projectName" name="projectName" class="text-box"
                               style="border: none;" size="10" value="<%= timeReport.getProjectName() %>"></div>

                    <div id="text_div"><p>ID</p></br>
                        <input type="text" readonly id="reportId" name="reportId" class="text-box" style="border: none;"
                               size="10" value="<%= timeReport.getReportId() %>"></div>

                    <div id="text_div"><p>Date</p></br>
                        <input type="text" value="<%= timeReport.getReportDate() %>" id="reportDate" name="reportDate"
                               class="text-box" size="10"></div>

                    <div id="text_div"><p>Start Time</p></br>
                        <input type="text" value="<%= timeReport.getStartTime().substring(10) %>" id="startTime"
                               name="startTime" class="text-box" size="10"></div>

                    <div id="text_div"><p>End Time</p></br>
                        <input type="text" value="<%= timeReport.getEndTime().substring(10) %>" id="endTime"
                               name="endTime" class="text-box" size="10"></div>

                    <div id="text_div"><p>Break</p></br>
                        <input type="text" value="<%= timeReport.getBreakTime() %>" id="breakTime" name="breakTime"
                               class="text-box" size="10"></div>

                    <!-- Dropdown for submit -->
                    <div id="text_div"><p>Activity</p></br>
                        <select id="activityId" name="activityId" class="text-box">
                            <option value="<%= timeReport.getActivityId() %>"><%= timeReport.getActivityId() %>
                            </option>
                            <option value="11">11. SDP</option>
                            <option value="12">12. SRS</option>
                            <option value="13">13. SVVS</option>
                            <option value="14">14. STLDD</option>
                            <option value="15">15. SVVI</option>
                            <option value="16">16. SDDD</option>
                            <option value="17">17. SVVR</option>
                            <option value="18">18. SSD</option>
                            <option value="19">19. Slutrapport</option>
                            <option value="21">21. Funktionstest</option>
                            <option value="22">22. Systemtest</option>
                            <option value="23">23. Regressionstest</option>
                            <option value="30">30. Möte</option>
                            <option value="41">41. Föreläsning</option>
                            <option value="42">42. Övning</option>
                            <option value="43">43. Terminalövning</option>
                            <option value="44">44. Självstudier</option>
                            <option value="100">100. Övrigt</option>
                        </select>
                    </div>

                    <!-- Button for submit -->
                    <div id="text_div" style="margin-top: 18px;">
                        <button type="submit" class="blue_button">Update</button>
                    </div>

                </form>
            </div>
        </div>
        <% } %>
    </div>
    </div>

    </body>
    <%@ include file="footer.jsp" %>

</html>