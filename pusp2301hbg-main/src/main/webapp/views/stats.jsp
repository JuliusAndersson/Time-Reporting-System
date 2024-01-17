<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Stats</title>


    <%@ include file="/views/menu.jsp" %>

    <%@ page import="java.util.Map" %>

    <% Map<String, String> projectList = (Map<String, String>) request.getAttribute("projectList"); %>

    <style>
        #content {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        table, th, td {
            margin-top: 5vh;
            border: 1px solid black;
            text-align: center;
            padding: 2vh;
        }

        h1 {
            margin-top: 5vh;
        }

    </style>

    <div id="content">

        <h1>STATISTICS</h1>


        <table style="width: 80%">

			<% for (Map.Entry<String, String> name : projectList.entrySet()) { %>

	 		<tr>
				<td><%= name.getKey() %></td>
				<td><%= name.getValue() %></td>
			</tr>
			
			<% } %>
        
        </table>

    </div>
    </div>

    </body>
    <%@ include file="footer.jsp" %>
</html>
 