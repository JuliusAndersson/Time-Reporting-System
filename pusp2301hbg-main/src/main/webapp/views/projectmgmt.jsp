<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project management</title>
    <link rel="stylesheet" href="/css/timereportupdate.css"/>

    <%@ page import="java.util.List" %>
        <% List<String> projectList = (List<String>) request.getAttribute("projectList"); %>
    <%@ include file="menu.jsp" %>

    <div id="content">

        <h1>PROJECT MANAGEMENT</h1>

        <div class="boxBorder" style="width: 60%">
            <div class="tableFixHead">
                <%-- Table gets information from database and gets html string from Java backend --%>
                <table>
                    <thead>
                    <tr>
                        <th style="font-size: 20px;">Project</th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>


                    <% for (String project : projectList) { %>
                    <tr>
                        <td style="width: 90%;"><%= project %>
                        </td>
                        <td style="width: 5%;"><a href="/PGSign?projectName=<%=project%>"><input type="submit"
                                                                                                 value="Sign Reports"
                                                                                                 class="blue_button"
                                                                                                 style="width: 110px"></a>
                        </td>
                        <td style="width: 5%;"><a href="/PGMember?projectName=<%=project%>"><input type="submit"
                                                                                                   value="Members"
                                                                                                   class="blue_button"></a>
                        </td>
                    </tr>
                    <% } %>


                    </tbody>

                </table>
            </div>
        </div>

    </div>
    </div>
    <%@ include file="footer.jsp" %>

    </body>
</html>

 