<%@ page import="model.UserBean" %>
<%@ page import="java.util.List" %>
<%
    List<UserBean> userList = (List<UserBean>) request.getAttribute("userList");
%>
<%
    List<String> projectList = (List<String>) request.getAttribute("projectList");
%>


<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="../css/adminStyle.css"/>
    <title>Admin Page</title>
    <%@ include file="menu.jsp" %>

    <div class="content">
        <%
            if (request.getAttribute("success") != null) {
        %>
        <div class="message-box success-message">
            <div class="message-content">
                <p><%=request.getAttribute("success")%></p>
                <button class="close-btn" onclick="this.parentElement.parentElement.style.display='none';">Close</button>
            </div>
        </div>
        <%
        } else if (request.getAttribute("errorM") != null) {
        %>
        <div class="message-box error-message">
            <div class="message-content">
                <p><%=request.getAttribute("errorM")%></p>
                <button class="close-btn" onclick="this.parentElement.parentElement.style.display='none';">Close</button>
            </div>
        </div>
        <%
            }
        %>

        <style>
            .close-btn {
                background-color: transparent;
                color: #666;
                font-size: 10px;
                font-weight: bold;
                cursor: pointer;
                transition: color 0.2s;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            .close-btn:hover {
                color: #333;
                border-color: #333;            }
        </style>




        <h1 id="main_title">ADMIN</h1>

        <div class="section">
            <div id="section_header"><h1 id="section_title">Project Settings</h1></div>

            <div class="container_cell">

                <form id="form_section_1" action="/admin">
                    <div id="delete_project">
                        <h3>Delete Existing Projects</h3>

                        <div id="horizontal_div">
                            <select name="deleteProject" id="Projects" style="padding-left: 10px;">
                                <% for (String projcet : projectList) { %>
                                <option value="<%=projcet%>"><%=projcet%>
                                </option>
                                <% } %>
                            </select>
                            <input type="submit" value="DELETE">
                        </div>
                    </div>
                </form>


                <form id="form_section_1" action="/admin">
                    <div id="create_project">
                        <h3>Create New Project</h3>

                        <div id="horizontal_div">
                            <input type="text" placeholder="PROJECT NAME" id="projectName" name="projectName" required>
                            <input type="submit" value="CREATE">

                        </div>
                    </div>
                </form>
            </div>
        </div>


        <!-- SECTION 2 -->
        <div class="section">
            <div id="section_header"><h1 id="section_title">User Settings</h1></div>

            <div class="container_cell">
                <div id="edit_users">

                    <h3>Edit existing user</h3>

                    <div id="horizontal_div">

                        <form id="form_section_2_1" action="/admin">
                            <div class="vertical_div_1">
                                <h3>Delete user</h3>
                                <select name="deletename" id="Users" style="padding-left: 10px;">
                                    <% for (UserBean user : userList) { %>
                                    <option value="<%=user.getName()%>"><%=user.getName()%>
                                    </option>
                                    <% } %>
                                </select>

                                <input type="submit" value="DELETE">
                            </div>
                        </form>

                        <form id="form_section_2_2" action="/admin">
                            <div class="vertical_div_1">
                                <h3>Change password</h3>
                                <select name="updateUser" id="Users-update" style="padding-left: 10px;">
                                    <% for (UserBean user : userList) { %>
                                    <option value="<%=user.getName()%>"><%=user.getName()%>
                                    </option>
                                    <% } %>
                                </select>
                                <input type="password" placeholder="New Password" id="newPassword" name="newPassword"
                                       required>
                                <input type="submit" value="UPDATE">

                            </div>
                        </form>

                    </div>


                </div>

                <div id="create_user">
                    <h3>Create New User</h3>

                    <form action="/admin">
                        <div id="vertical_div_2">
                            <input type="text" placeholder="Username" id="username" name="addname" required>
                            <input type="password" placeholder="Password" id="password" name="addpass" required>

                            <input type="submit" value="CREATE">

                        </div>
                    </form>

                </div>
            </div>

        </div>
    </div>
    </div>
    <%@ include file="footer.jsp" %>
    </body>
</html>