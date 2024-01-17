<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>


    <%@ include file="views/menu.jsp" %>

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

        <div class="welcome-page" style="">
            <h1 class="welcome-page-header">Welcome to the time reporting System</h1>
            Time Reporting System, which is a time reporting system utilizing a web-server solution. The system
            maintains information on multiple projects and their respective project members, each with distinct roles
            and assignments. The intention of the system is to collect time reports from project team members and to
            generate and display statistics on the time allocation for different aspects of the project. The system is
            developed by Project Group 1 (PG1) as part of the course Software Development for Large Projects (ETSF20).

        </div>

    </div>
    </div>
    </body>
    <%@ include file="/views/footer.jsp" %>
</html>
