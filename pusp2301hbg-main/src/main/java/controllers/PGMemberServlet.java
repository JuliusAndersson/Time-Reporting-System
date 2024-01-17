package controllers;

import model.MemberBean;
import model.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PGMemberServlet", value = "/PGMember")
public class PGMemberServlet extends ServletBase {
    private static final long serialVersionUID = 1L;

    /**
     * @see ServletBase#ServletBase()
     */
    public PGMemberServlet() {
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

        if (!myName.equals("admin")) {
            if (!database.isUserPG(myName)) {
                String errorMessage = "You do not have permission to access this page.";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("/").forward(request, response);
                return;
            }
        }

        // get attributes for JSP
        String project = request.getParameter("projectName");
        String button = request.getParameter("submitButton");

        // handle requests
        HandleAddMember(request, project);
        if (button != null) {
            List<MemberBean> members = new ArrayList<>(database.getProjectMembers(project));
            if (button.equals("Save")) {
                HandleUpdateMember(request, project, members);
            } else if (button.equals("Remove")) {
                HandleRemoveMember(request, project, members);
            }
        }


        // set attributes for JSP, if admin SHOW ALL PROJECTS AND MEMBERS.
        if (!myName.equals("admin")) {
            request.setAttribute("membersList", MembersUnderPG(myName, project));
        } else {
            request.setAttribute("membersList", database.getProjectMembers(project));
        }

        request.setAttribute("unassignedUsers", AllUsersNotPartOfProject(project));
        request.setAttribute("projectName", project);

        // forward request to JSP
        request.getRequestDispatcher("views/projectmgmtusers.jsp").forward(request, response);


    }

    private void HandleAddMember(HttpServletRequest request, String project) {
        String newMember = request.getParameter("newMember");
        String newRole = request.getParameter("newRole");

        if (newMember != null && checkUser(newMember) && project != null && checkProject(project)) {
            if (database.addMemberToProject(newMember, project, newRole)) {
                request.setAttribute("success", "Member added successfully!");
            } else {
                request.setAttribute("notPossible", "Member already exists within the project!");
            }
        } else if (newMember != null) {
            request.setAttribute("InvalidMember", "User Must Exist Within The System.");
        } else if (checkProject(project)) {
            request.setAttribute("InvalidProject", "User Must Exist Within The System.");
        }
    }

    private void HandleUpdateMember(HttpServletRequest request, String project, List<MemberBean> members) {
        String updateMember = request.getParameter("member");
        String updateRole = request.getParameter("role");

        if (updateMember != null && updateRole != null) {
            for (MemberBean member : members) {
                if (member.getName().equals(updateMember)) {
                    if (database.changeRole(updateMember, project, updateRole)) {
                        request.setAttribute("success", "Member updated successfully!");
                    } else {
                        request.setAttribute("notPossible", "Member don't exist!");
                    }
                }
            }
        }
    }

    private void HandleRemoveMember(HttpServletRequest request, String project, List<MemberBean> members) {
        String removeMember = (String) request.getParameter("member");
        if (removeMember != null) {
            for (MemberBean member : members) {
                if (member.getName().equals(removeMember)) {
                    if (database.removeMemberFromProject(removeMember, project)) {
                        request.setAttribute("success", "Member removed successfully!");
                    } else {
                        request.setAttribute("notPossible", "Member don't exists within the project!");
                    }
                }
            }
        }
    }

    private boolean checkUser(String user) {
        return database.isCorrectUser(user);
    }

    private boolean checkProject(String project) {
        return database.getProjects().contains(project);
    }

    private List<MemberBean> MembersUnderPG(String PG, String projectName) {
        List<MemberBean> list = new ArrayList<>();
        for (MemberBean member : database.getProjectMembers(projectName)) {
            if (!member.getRole().equals("PG")) {
                list.add(member);
            }
        }
        return list;
    }

    private List<UserBean> AllUsersNotPartOfProject(String project) {
        List<UserBean> list = new ArrayList<>();
        List<MemberBean> members = new ArrayList<>();
        for (MemberBean member : database.getProjectMembers(project)) {
            members.add(member);
        }

        for (UserBean user : database.getUsers()) {
            list.add(user);
            for (MemberBean member : members) {
                if (member.getName().equals(user.getName())) {
                    list.remove(user);
                }
            }
        }
        return list;
    }

    /**
     *
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
    }
}