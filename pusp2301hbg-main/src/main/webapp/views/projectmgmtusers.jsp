<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project management - users</title>
    <link rel="stylesheet" href="/css/timereportupdate.css"/>
    <link rel="stylesheet" href="/css/timereportnew.css"/>

    <%@ include file="menu.jsp" %>
    <%@ page import="java.util.List" %>
    <%@ page import="model.MemberBean" %>
    <%@ page import="model.UserBean" %>
        <% List<MemberBean> membersList = (List<MemberBean>) request.getAttribute("membersList"); %>
        <% List<UserBean> usersList = (List<UserBean>) request.getAttribute("unassignedUsers"); %>
        <% String projectName = (String) request.getAttribute("projectName"); %>

    <div id="content">

        <h1>USER MANAGEMENT</h1>

        <div id="border_template" style="width: 40%; height: 90px">
            <div id="border_template_inner" style="height: 65px">

                <form action="/PGMember" id="inner_div">

                    <!-- Hidden values-->
                    <div><input type="hidden" name="projectName" value="<%= projectName %>"> </input></div>

                    <!-- Dropdown for submit-->
                    <div id="text_div" style="margin-top: 18px; width: 25%;"><p>Add User</p></div>

                    <div id="text_div" style="width: 25%;"><p>Username</p></br>
                        <select name="newMember" class="text-box" style="width: 110px;">
                            <% for (UserBean user : usersList) { %>
                            <option value="<%= user.getName() %>"><%= user.getName() %>
                            </option>
                            <% } %>
                        </select></div>

                    <div id="text_div" style="width: 25%;"><p>Role</p></br>
                        <select name="newRole" class="text-box" style="width: 50px;">
                            <option value="UG">UG</option>
                            <option value="TG">TG</option>
                            <option value="SG">SG</option>
                            <option value="PG">PG</option>
                        </select></div>

                    <!-- Button for submit -->
                    <div id="text_div" style="margin-top: 10px; width: 25%;">
                        <input type="submit" value="Submit" class="blue_button"/></div>

                </form>
            </div>
        </div>

        <div class="boxBorder" style="width: 60%">
            <div class="tableFixHead">
                <%-- Table gets information from database and gets html string from Java backend --%>
                <table>
                    <thead>
                    <tr>
                        <th>Username</th>
                        <th>Role</th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>

                    <!-- Hidden values-->
                    <div><input type="hidden" name="projectName" value="<%= projectName %>"> </input></div>

                    <!-- List of members within the selected project-->
                        <% for(MemberBean member : membersList) { %>

                    <form action="/PGMember">
                        <div><input type="hidden" name="projectName" value="<%= projectName %>"> </input></div>
                        <tr>


                            <td><input type="text" style="border:none;" readonly name="member"
                                       value="<%= member.getName() %>"></input></td>

                            <td style="width: 10%;"><select name="role" class="text-box" style="width: 50px;">
                                <option value="<%= member.getRole() %>"><%= member.getRole() %>
                                </option>
                                <option value="UG">UG</option>
                                <option value="TG">TG</option>
                                <option value="SG">SG</option>
                                <option value="PG">PG</option>
                            </select></td>
            </div>

            <td style="width: 5%;"><input type="submit" name="submitButton" value="Save"
                                          class="blue_button"></input></a></td>
            <td style="width: 5%;"><input type="submit" name="submitButton" value="Remove"
                                          class="blue_button"></input></a></td>

            </tr>
            </form>
            <% } %>

            </tbody>

            </table>
        </div>
    </div>

    </div>
    </div>
    </body>
    <%@ include file="footer.jsp" %>
</html>