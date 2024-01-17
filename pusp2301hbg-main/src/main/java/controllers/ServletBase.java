package controllers;

import Configuration.Database;
import Configuration.MySQLDatabase;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * This class is the superclass for all servlets in the application.
 * It includes basic functionality required by many servlets, like for example a
 * page head
 * written by all servlets, and the connection to the database.
 * This application requires a database.
 * For username and password, see the constructor in this class.
 *
 * <p>
 * The database can be created with the following SQL command:
 * mysql> create database base;
 * <p>
 * The required table can be created with created with:
 * mysql> create table users(name varchar(10), password varchar(10), primary key
 * (name));
 * <p>
 * The administrator can be added with:
 * mysql> insert into users (name, password) values('admin', 'adminp');
 *
 * @author Martin Host
 * @version 1.0
 */
public class ServletBase extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Define states
    protected static final int LOGIN_FALSE = 0;
    protected static final int LOGIN_TRUE = 1;
    protected Database database = new MySQLDatabase();

    /**
     * Constructs a servlet and makes a connection to the database.
     * It also writes all user names on the console for test purpose.
     */
    public ServletBase() {
    }

    /**
     * Checks if a user is logged in or not.
     *
     * @param request The HTTP Servlet request (so that the session can be found)
     * @return true if the user is logged in, otherwise false.
     */
    protected boolean loggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Object objectState = session.getAttribute("state");
        int state = LOGIN_FALSE;
        if (objectState != null)
            state = (Integer) objectState;
        return (state == LOGIN_TRUE);
    }
}
