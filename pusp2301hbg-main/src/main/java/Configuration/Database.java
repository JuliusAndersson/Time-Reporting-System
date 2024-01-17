package Configuration;

import model.MemberBean;
import model.TimeReportBean;
import model.UserBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Database {
    Connection getConnection() throws SQLException, ClassNotFoundException;

    // Report methods
    Boolean createReport(TimeReportBean report);

    Boolean updateReport(int reportId, TimeReportBean updatedReport);

    Boolean signReport(int reportId);

    List<TimeReportBean> getAllUnsignedReports(String project);

    List<TimeReportBean> getAllSignedReportsByUser(String user);

    List<TimeReportBean> getAllReportsByUser(String user);

    List<TimeReportBean> getAllReportsByWeek(String project, int week);

    List<TimeReportBean> getAllReportsByProject(String projectName);

    TimeReportBean getReportById(int id);

    List<TimeReportBean> getReportsByActivity(int activity, String projectName);

    List<TimeReportBean> getReportsByRole(String project, String role);
    Boolean removeReport(int reportId);

    // User methods
    Boolean createUser(String user, String password);

    Boolean isCorrectUser(String user);

    Boolean isUserPG(String user);

    Boolean checkPassword(String user, String password);

    Boolean changePassword(String user, String newPassword);

    List<String> getUserProjects(String user);

    List<UserBean> getUsers();

    String decryptPassword(String d);

    Boolean removeUser(String user);

    // Project and Members methods
    Boolean createProject(String name, String startDate, String endDate);

    Boolean setEndProject(String name, String endDate);

    Boolean removeProject(String name);

    Boolean addMemberToProject(String member, String project, String role);

    Boolean removeMemberFromProject(String menber, String project);

    List<MemberBean> getProjectMembers(String project);

    List<String> getPGProjects(String user);

    List<String> getProjects();

    String getProjectStartDate(String projectName);

    String getProjectEndDate(String projectName);

    Boolean changeRole(String username, String projectName, String role);
    String getRole(String username, String projectName);

    List<TimeReportBean> getAllUnsignedReportsByUser(String user);

    /*###########OBS####################################################################################
    //TODO: Activity table is no longer part of the project. Activities are hardcoded.
    Boolean addActivityToProject(String project, String name, String description); //###Delete?
    Boolean removeActivityFromProject(String project, int activityId); //###Delete?
    List<Activity> getActivitiesByProject(String project); //###Delete?
    Boolean updateActivity(String project, int activityId, String name, String description); //###Delete?
    Boolean assignActivity(String user, int activityId, String project); //###Delete?
    Boolean removeActivityAssignment(String user, int activityId, String project); //###Delete?
    List<ActivityAssignment> getActivityAssignmentsByUser(String user); //###Delete?
    List<ActivityAssignment> getActivityAssignmentsByActivity(int activityId); //###Delete?
    #################################################################################################
    */
}
