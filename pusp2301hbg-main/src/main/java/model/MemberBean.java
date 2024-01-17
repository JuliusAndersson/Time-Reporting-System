/**
 * This class represents a MemberBean object that stores information about a team member.
 */
package model;

public class MemberBean {

    /**
     * The name of the team member.
     */
    private String name;

    /**
     * The project the team member is working on.
     */
    private String project;

    /**
     * The role of the team member.
     */
    private String role;

    /**
     * Sets the name of the team member.
     *
     * @param name the name of the team member to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the project the team member is working on.
     *
     * @param project the project the team member is working on to be set
     */
    public void setProject(String project) {
        this.project = project;
    }

    /**
     * Sets the role of the team member.
     *
     * @param role the role of the team member to be set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Returns the name of the team member.
     *
     * @return the name of the team member
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the project the team member is working on.
     *
     * @return the project the team member is working on
     */
    public String getProject() {
        return project;
    }

    /**
     * Returns the role of the team member.
     *
     * @return the role of the team member
     */
    public String getRole() {
        return role;
    }
}