/**
 * UserBean represents a user object containing user's name, password and delete URL.
 */
package model;

public class UserBean {
    private String name;
    private String password;
    private String DeleteUrl;

    /**
     * Gets the user's name.
     *
     * @return the user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name.
     *
     * @param name the user's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the user's password.
     *
     * @return the user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password the user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets to delete URL of the user.
     *
     * @return to delete URL of the user.
     */
    public String getDeleteUrl() {
        return DeleteUrl;
    }

    /**
     * Sets to delete URL of the user.
     *
     * @param deleteUrl to delete URL of the user.
     */
    public void setDeleteUrl(String deleteUrl) {
        DeleteUrl = deleteUrl;
    }
}





