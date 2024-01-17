/**
 * The TimeReportBean class represents a bean for holding time report information.
 */
package model;

public class TimeReportBean {
    /**
     * The project name associated with the time report.
     */
    private String projectName;

    /**
     * The username of the user who created the time report.
     */
    private String username;

    /**
     * The start time of the time report.
     */
    private String startTime;

    /**
     * The end time of the time report.
     */
    private String endTime;

    /**
     * The date of the time report.
     */
    private String reportDate;

    /**
     * The activity ID associated with the time report.
     */
    private int activityId;

    /**
     * The break time of the time report.
     */
    private int breakTime;

    /**
     * The ID of the time report.
     */
    private int reportId;

    /**
     * Whether or not the time report has been signed.
     */
    private boolean signed;

    /**
     * Returns the project name associated with the time report.
     *
     * @return The project name associated with the time report.
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Sets the project name associated with the time report.
     *
     * @param projectName The project name associated with the time report.
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Returns the username of the user who created the time report.
     *
     * @return The username of the user who created the time report.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user who created the time report.
     *
     * @param username The username of the user who created the time report.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the start time of the time report.
     *
     * @return The start time of the time report.
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time of the time report.
     *
     * @param startTime The start time of the time report.
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Returns the end time of the time report.
     *
     * @return The end time of the time report.
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time of the time report.
     *
     * @param endTime The end time of the time report.
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * Returns the date of the time report.
     *
     * @return The date of the time report.
     */
    public String getReportDate() {
        return reportDate;
    }

    /**
     * Sets the date of the time report.
     *
     * @param reportDate The date of the time report.
     */
    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    /**
     * Returns the activity ID associated with the time report.
     *
     * @return The activity ID associated with the time report.
     */
    public int getActivityId() {
        return activityId;
    }

    /**
     * Sets the activity ID associated with the time report.
     *
     * @param activityId The activity ID associated with the time report.
     */
    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    /**
     * Sets the break time for this TimeReportBean.
     *
     * @param breakTime the break time to be set
     */
    public void setBreakTime(int breakTime) {
        this.breakTime = breakTime;
    }

    /**
     * Returns the break time for this TimeReportBean.
     *
     * @return the break time for this TimeReportBean
     */
    public int getBreakTime() {
        return breakTime;
    }

    /**
     * Sets whether or not this TimeReportBean has been signed.
     *
     * @param signed true if this TimeReportBean has been signed, false otherwise
     */
    public void setSigned(boolean signed) {
        this.signed = signed;
    }

    /**
     * Returns whether or not this TimeReportBean has been signed.
     *
     * @return true if this TimeReportBean has been signed, false otherwise
     */
    public boolean getSigned() {
        return signed;
    }

    /**
     * Sets the report ID for this TimeReportBean.
     *
     * @param reportId the report ID to be set
     */
    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    /**
     * Returns the report ID for this TimeReportBean.
     *
     * @return the report ID for this TimeReportBean
     */
    public int getReportId() {
        return reportId;
    }
}
