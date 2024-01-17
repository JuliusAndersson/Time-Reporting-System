package Configuration;

import model.MemberBean;
import model.TimeReportBean;
import model.UserBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLDatabase implements Database {

    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String databaseName = "pusp2301hbg";
        String username = "pusp2301hbg";
        String password = "ps94kv8d";

        String connectionString = "jdbc:mysql://pusp.cs.lth.se/" + databaseName + "?user=" + username + "&password=" + password;

        return DriverManager.getConnection(connectionString);
    }

    private void closeConnection(Connection connection) throws SQLException {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection = null;

        System.err.println("Database connection closed.");
    }

    @Override
    public Boolean createReport(TimeReportBean report) {
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Reports Values(?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                ps.setString(1, report.getProjectName());
                ps.setInt(2, 0);
                ps.setInt(3, report.getActivityId());
                ps.setString(4, report.getStartTime());
                ps.setString(5, report.getEndTime());
                ps.setInt(6, report.getBreakTime());
                ps.setString(7, report.getReportDate());
                ps.setString(8, report.getUsername());
                ps.setBoolean(9, report.getSigned());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                closeConnection(conn);
                return false;
            }
            closeConnection(conn);
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateReport(int reportId, TimeReportBean updatedReport) {
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE Reports SET projectName = ?, activityId = ?, startTime = ?, endTime = ?, breakTime = ?, reportDate = ?, username = ?, signed = ? WHERE reportId = ?")) {
                ps.setString(1, updatedReport.getProjectName());
                ps.setInt(2, updatedReport.getActivityId());
                ps.setString(3, updatedReport.getStartTime());
                ps.setString(4, updatedReport.getEndTime());
                ps.setInt(5, updatedReport.getBreakTime());
                ps.setString(6, updatedReport.getReportDate());
                ps.setString(7, updatedReport.getUsername());
                ps.setBoolean(8, updatedReport.getSigned());
                ps.setInt(9, reportId);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                closeConnection(conn);
                return false;
            }
            closeConnection(conn);
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean signReport(int reportId) {
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE Reports SET signed = ? WHERE reportId = ?")) {
                ps.setBoolean(1, true);
                ps.setInt(2, reportId);
                ps.executeUpdate();
                closeConnection(conn);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(conn);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public List<TimeReportBean> getAllUnsignedReports(String project) {
        List<TimeReportBean> reports = new ArrayList<>();
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Reports WHERE signed = false AND projectName = ?")) {
                ps.setString(1, project);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    TimeReportBean bean = new TimeReportBean();

                    bean.setProjectName(rs.getString("projectName"));
                    bean.setReportId(rs.getInt("reportId"));
                    bean.setActivityId(rs.getInt("activityId"));
                    bean.setStartTime(rs.getString("startTime"));
                    bean.setEndTime(rs.getString("endTime"));
                    bean.setBreakTime(rs.getInt("breakTime"));
                    bean.setReportDate(rs.getString("reportDate"));
                    bean.setUsername(rs.getString("username"));
                    bean.setSigned(rs.getBoolean("signed"));

                    reports.add(bean);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(conn);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return reports;
    }

    @Override
    public List<TimeReportBean> getAllUnsignedReportsByUser(String user) {
        List<TimeReportBean> reports = new ArrayList<>();
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Reports LEFT JOIN Users ON Users.username = Reports.username WHERE signed = false AND Users.username = ?")) {
                ps.setString(1, user);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    TimeReportBean bean = new TimeReportBean();

                    bean.setProjectName(rs.getString("projectName"));
                    bean.setReportId(rs.getInt("reportId"));
                    bean.setActivityId(rs.getInt("activityId"));
                    bean.setStartTime(rs.getString("startTime"));
                    bean.setEndTime(rs.getString("endTime"));
                    bean.setBreakTime(rs.getInt("breakTime"));
                    bean.setReportDate(rs.getString("reportDate"));
                    bean.setUsername(rs.getString("username"));
                    bean.setSigned(rs.getBoolean("signed"));

                    reports.add(bean);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(conn);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return reports;
    }


    @Override
    public List<TimeReportBean> getAllSignedReportsByUser(String user) {
        List<TimeReportBean> reports = new ArrayList<>();
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Reports LEFT JOIN Users ON Users.username = Reports.username WHERE signed = true AND Users.username = ?")) {
                ps.setString(1, user);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    TimeReportBean bean = new TimeReportBean();

                    bean.setProjectName(rs.getString("projectName"));
                    bean.setReportId(rs.getInt("reportId"));
                    bean.setActivityId(rs.getInt("activityId"));
                    bean.setStartTime(rs.getString("startTime"));
                    bean.setEndTime(rs.getString("endTime"));
                    bean.setBreakTime(rs.getInt("breakTime"));
                    bean.setReportDate(rs.getString("reportDate"));
                    bean.setUsername(rs.getString("username"));
                    bean.setSigned(rs.getBoolean("signed"));

                    reports.add(bean);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(conn);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return reports;
    }

    @Override
    public List<TimeReportBean> getAllReportsByUser(String user) {
        List<TimeReportBean> reports = new ArrayList<>();
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Reports LEFT JOIN Users ON Users.username = Reports.username WHERE Users.username = ?")) {
                ps.setString(1, user);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    TimeReportBean bean = new TimeReportBean();

                    bean.setProjectName(rs.getString("projectName"));
                    bean.setReportId(rs.getInt("reportId"));
                    bean.setActivityId(rs.getInt("activityId"));
                    bean.setStartTime(rs.getString("startTime"));
                    bean.setEndTime(rs.getString("endTime"));
                    bean.setBreakTime(rs.getInt("breakTime"));
                    bean.setReportDate(rs.getString("reportDate"));
                    bean.setUsername(rs.getString("username"));
                    bean.setSigned(rs.getBoolean("signed"));

                    reports.add(bean);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(conn);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return reports;
    }


    @Override
    public List<TimeReportBean> getAllReportsByWeek(String project, int week) {
        List<TimeReportBean> reports = new ArrayList<>();
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Reports INNER JOIN Projects ON Projects.projectName = Reports.projectName WHERE Reports.projectName = ? AND WEEK(Reports.reportDate) = ?")) {
                ps.setString(1, project);
                ps.setInt(2, week);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    TimeReportBean bean = new TimeReportBean();

                    bean.setProjectName(rs.getString("projectName"));
                    bean.setReportId(rs.getInt("reportId"));
                    bean.setActivityId(rs.getInt("activityId"));
                    bean.setStartTime(rs.getString("startTime"));
                    bean.setEndTime(rs.getString("endTime"));
                    bean.setBreakTime(rs.getInt("breakTime"));
                    bean.setReportDate(rs.getString("reportDate"));
                    bean.setUsername(rs.getString("username"));
                    bean.setSigned(rs.getBoolean("signed"));

                    reports.add(bean);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(conn);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return reports;
    }

    @Override
    public List<TimeReportBean> getAllReportsByProject(String projectName) {
        List<TimeReportBean> reports = new ArrayList<>();
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Reports INNER JOIN Projects ON Projects.projectName = Reports.projectName WHERE Reports.projectName = ?")) {
                ps.setString(1, projectName);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    TimeReportBean bean = new TimeReportBean();

                    bean.setProjectName(rs.getString("projectName"));
                    bean.setReportId(rs.getInt("reportId"));
                    bean.setActivityId(rs.getInt("activityId"));
                    bean.setStartTime(rs.getString("startTime"));
                    bean.setEndTime(rs.getString("endTime"));
                    bean.setBreakTime(rs.getInt("breakTime"));
                    bean.setReportDate(rs.getString("reportDate"));
                    bean.setUsername(rs.getString("username"));
                    bean.setSigned(rs.getBoolean("signed"));

                    reports.add(bean);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(conn);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return reports;
    }

    @Override
    public List<TimeReportBean> getReportsByActivity(int activity, String projectName) {
        List<TimeReportBean> reports = new ArrayList<>();
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Reports WHERE Reports.projectName = ? AND activityId = ?")) {
                ps.setString(1, projectName);
                ps.setInt(2, activity);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    TimeReportBean bean = new TimeReportBean();

                    bean.setProjectName(rs.getString("projectName"));
                    bean.setReportId(rs.getInt("reportId"));
                    bean.setActivityId(rs.getInt("activityId"));
                    bean.setStartTime(rs.getString("startTime"));
                    bean.setEndTime(rs.getString("endTime"));
                    bean.setBreakTime(rs.getInt("breakTime"));
                    bean.setReportDate(rs.getString("reportDate"));
                    bean.setUsername(rs.getString("username"));
                    bean.setSigned(rs.getBoolean("signed"));

                    reports.add(bean);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(conn);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return reports;
    }

    @Override
    public List<TimeReportBean> getReportsByRole(String project, String role) {
        List<TimeReportBean> reports = new ArrayList<>();
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Reports INNER JOIN Members ON Reports.projectName = Members.projectName AND Reports.username = Members.username WHERE Reports.projectName = ? AND role = ?")) {
                ps.setString(1, project);
                ps.setString(2, role);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    TimeReportBean bean = new TimeReportBean();

                    bean.setProjectName(rs.getString("projectName"));
                    bean.setReportId(rs.getInt("reportId"));
                    bean.setActivityId(rs.getInt("activityId"));
                    bean.setStartTime(rs.getString("startTime"));
                    bean.setEndTime(rs.getString("endTime"));
                    bean.setBreakTime(rs.getInt("breakTime"));
                    bean.setReportDate(rs.getString("reportDate"));
                    bean.setUsername(rs.getString("username"));
                    bean.setSigned(rs.getBoolean("signed"));

                    reports.add(bean);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(conn);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return reports;
    }

    @Override
    public Boolean removeReport(int reportId) {
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM Reports WHERE reportId = ?")) {
                ps.setInt(1, reportId);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                closeConnection(conn);
                return false;
            }
            closeConnection(conn);
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public TimeReportBean getReportById(int id) {
        TimeReportBean report = new TimeReportBean();
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Reports WHERE reportId = ?")) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    report.setProjectName(rs.getString("projectName"));
                    report.setReportId(rs.getInt("reportId"));
                    report.setActivityId(rs.getInt("activityId"));
                    report.setStartTime(rs.getString("startTime"));
                    report.setEndTime(rs.getString("endTime"));
                    report.setBreakTime(rs.getInt("breakTime"));
                    report.setReportDate(rs.getString("reportDate"));
                    report.setUsername(rs.getString("username"));
                    report.setSigned(rs.getBoolean("signed"));
                    return report;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(conn);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean createUser(String user, String password) {
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Users Values(?, PASSWORD(?))")) {
                ps.setString(1, user);
                ps.setString(2, password);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Boolean isCorrectUser(String user) {
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("Select username FROM Users WHERE username = ?")) {
                ps.setString(1, user);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    closeConnection(conn);
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public Boolean isUserPG(String user) {
        boolean isPG = false;
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT Role FROM Members WHERE username = ?")) {
                ps.setString(1, user);
                ResultSet result = ps.executeQuery();
                while (result.next()) {
                    if (result.getString("Role").equals("PG")) {
                        isPG = true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return isPG;
    }

    @Override
    public Boolean checkPassword(String user, String password) {
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("Select username FROM Users WHERE username = ? AND pass = PASSWORD(?)")) {
                ps.setString(1, user);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(conn);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<UserBean> getUsers() {
        List<UserBean> userList = new ArrayList<>();
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT username FROM Users");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserBean bean = new UserBean();
                bean.setName(rs.getString("username"));
                String deleteURL = "admin?deletename=" + rs.getString("username");
                String deleteCode = "<a href=\"" + deleteURL + "\" onclick=\"return confirm('Are you sure you want to delete " + rs.getString("username") + "?')\">" + "delete</a>";
                bean.setDeleteUrl(deleteCode);
                userList.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }


    public String decryptPassword(String encryptedPassword) {
        String decryptedPassword = null;
        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT OLD_PASSWORD(?) AS decrypted_password");
            ps.setString(1, encryptedPassword);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                decryptedPassword = rs.getString("decrypted_password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return decryptedPassword;
    }


    @Override
    public Boolean changePassword(String user, String newPassword) {
        if (newPassword.isEmpty()) {
            return false;
        }
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE Users SET pass = PASSWORD(?) WHERE username = ?")) {
                ps.setString(1, newPassword);
                ps.setString(2, user);
                ps.executeUpdate();
                closeConnection(conn);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public List<String> getUserProjects(String user) {
        List<String> projects = new ArrayList<>();
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT projectName FROM Members WHERE username = ?")) {
                ps.setString(1, user);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    projects.add(rs.getString("projectName"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(conn);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return projects;
    }

    @Override
    public Boolean removeUser(String user) {
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM Users WHERE username = ?")) {
                ps.setString(1, user);
                int result = ps.executeUpdate();
                if (result == 1) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public Boolean createProject(String name, String startDate, String endDate) {
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Projects Values(?,?,?)")) {
                ps.setString(1, name);
                ps.setString(2, startDate);
                ps.setString(3, endDate);
                ps.execute();
                closeConnection(conn);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(conn);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<String> getProjects() {

        ArrayList<String> result = new ArrayList<>();
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("Select projectName FROM Projects")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    result.add(rs.getString("projectName"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(conn);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getProjectStartDate(String projectName) {
        String startDate = "";
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT startDate FROM Projects WHERE projectName = ?")) {
                ps.setString(1, projectName);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    startDate = rs.getString("startDate");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(conn);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return startDate;
    }

    @Override
    public String getProjectEndDate(String projectName) {
        String result = "";
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Projects WHERE projectName = ?")) {
                ps.setString(1, projectName);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    result = rs.getString("endDate");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(conn);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Boolean setEndProject(String name, String endDate) {
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE Projects SET endDate = ? WHERE projectName = ?")) {
                ps.setString(1, endDate);
                ps.setString(2, name);
                ps.executeUpdate();
                closeConnection(conn);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(conn);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean removeProject(String name) {
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM Projects WHERE projectName = ?")) {
                ps.setString(1, name);
                ps.executeUpdate();
                closeConnection(conn);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(conn);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean addMemberToProject(String user, String project, String role) {
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Members VALUE(?,?,?)")) {
                ps.setString(1, user);
                ps.setString(2, project);
                ps.setString(3, role);
                ps.executeUpdate();
                closeConnection(conn);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(conn);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean removeMemberFromProject(String user, String project) {
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM Members WHERE username = ? AND projectName = ?")) {
                ps.setString(1, user);
                ps.setString(2, project);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                closeConnection(conn);
                return false;
            }
            closeConnection(conn);
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean changeRole(String username, String projectName, String role) {
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE Members SET Role = ? WHERE username = ? AND projectName = ?")) {
                ps.setString(1, role);
                ps.setString(2, username);
                ps.setString(3, projectName);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(conn);
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getRole(String username, String projectName) {
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn
                    .prepareStatement("Select Role FROM Members WHERE username = ? AND projectName = ?")) {
                ps.setString(1, username);
                ps.setString(2, projectName);
                ResultSet rs = ps.executeQuery();

                if(rs.next()){
                    return rs.getString("Role");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(conn);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<MemberBean> getProjectMembers(String project) {
        ArrayList<MemberBean> result = new ArrayList<>();
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Members WHERE projectName = ?")) {
                ps.setString(1, project);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    MemberBean temp = new MemberBean();
                    temp.setName(rs.getString("username"));
                    temp.setProject(rs.getString("projectName"));
                    temp.setRole(rs.getString("Role"));
                    result.add(temp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(conn);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public List<String> getPGProjects(String user) {
        List<String> projects = new ArrayList<>();
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT projectName FROM Members WHERE username = ? AND Role = ?")) {
                ps.setString(1, user);
                ps.setString(2, "PG");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    projects.add(rs.getString("projectName"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(conn);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return projects;
    }

    // **DOCUMENT CHANGE**
    // ALL ACTIVITES ARE HARDCODED, THEREFORE THE FOLLOWING METHODS ARE OBSOLETE.
    /*
     * @Override
     * public Boolean addActivityToProject(String project, String name, String
     * description) {
     * return null;
     * }
     *
     * @Override
     * public Boolean removeActivityFromProject(String project, int activityId) {
     * return null;
     * }
     *
     * @Override
     * public List<Activity> getActivitiesByProject(String project) {
     * return null;
     * }
     *
     * @Override
     * public Boolean updateActivity(String project, int activityId, String name,
     * String description) {
     * return null;
     * }
     *
     * @Override
     * public Boolean assignActivity(String user, int activityId, String project) {
     * return null;
     * }
     *
     * @Override
     * public Boolean removeActivityAssignment(String user, int activityId, String
     * project) {
     * return null;
     * }
     *
     * @Override
     * public List<ActivityAssignment> getActivityAssignmentsByUser(String user) {
     * return null;
     * }
     *
     * @Override
     * public List<ActivityAssignment> getActivityAssignmentsByActivity(int
     * activityId) {
     * return null;
     * }
     */
}
