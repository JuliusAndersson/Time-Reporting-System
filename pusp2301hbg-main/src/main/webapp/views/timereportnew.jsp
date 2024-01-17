<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Timereport - new</title>
    <link rel="stylesheet" href="/css/timereportnew.css"/>
    <%@ page import="model.TimeReportBean" %>
    <%@ page import="java.util.List" %>


    <!-- List with project names -->
        <% List<String> projectList = (List<String>)request.getAttribute("projectList"); %>


    <!-- Menu -->
    <%@ include file="menu.jsp" %>

    <!-- Area for the content of the current view -->
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
        <h1>TIMEREPORT</h1>

        <!-- Boarder around the div-->

        <div id="border_template">
            <div id="border_template_inner">

                <!-- form that will send the values with
                date, start_time, end_time, break_time, activity
                when submit is pressed -->
                <form id="inner_div">

                    <!-- Dropdown for submit -->
                    <div id="text_div"><p>Project</p></br>
                        <select id="project" name="project" class="text-box" style="width: 110px;">

                            <!-- For loop that makes the Dropdown choices -->
                            <% for (String project : projectList) { %>
                            <option value="<%=project %>"><%= project %>
                            </option>
                            <% } %>

                        </select></div>

                    <!-- Text areas for submit-->
                    <div id="text_div"><p>Date</p></br>
                        <input type="text" placeholder="YYYY-MM-DD" id="date" name="date" class="text-box"
                               size="10"></input></div>

                    <div id="text_div"><p>Start Time</p></br>
                        <input type="text" placeholder="hh:mm:ss" id="start_time" name="start_time" class="text-box"
                               size="10"></input></div>

                    <div id="text_div"><p>End Time</p></br>
                        <input type="text" placeholder="hh:mm:ss" id="end_time" name="end_time" class="text-box"
                               size="10"></input></div>

                    <div id="text_div"><p>Break</p></br>
                        <input type="text" placeholder="minutes" id="break_time" name="break_time" class="text-box"
                               size="10"></input></div>

                    <!-- Dropdown for submit -->
                    <div id="text_div"><p>Activity</p></br>
                        <select id="activity" name="activity" class="text-box" style="width: 150px;">
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
                        </select></div>

                    <!-- Button for submit -->
                    <div id="text_div" style="margin-top: 18px; ">
                        <input type="submit" value="Submit" class="blue_button"/></div>

                </form>
            </div>


        </div>
    </div>

    </body>
    <%@ include file="footer.jsp" %>

</html>
 