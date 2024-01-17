package Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The LoginFilter class implements the Filter interface to filter incoming requests and responses based on the user's login status.
 * The filter checks if the user is logged in and has a valid session. If the user is not logged in, the filter redirects the user to the login page.
 * The class provides the init, doFilter, and destroy methods required by the Filter interface.
 */
public class LoginFilter implements Filter {
    /**
     * Called by the web container to indicate to a filter that it is being placed into service.
     * This method is only called once when the filter is first created.
     *
     * @param filterConfig a FilterConfig object containing the filter's configuration parameters
     * @throws ServletException if an error occurs while initializing the filter
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    /**
     * Filters requests and responses according to the specified criteria.
     * If the user is logged in or is accessing the login page, the request is passed to the next filter or servlet in the chain using the chain.doFilter() method.
     * Otherwise, the user is redirected to the login page using the sendRedirect() method.
     *
     * @param request  the ServletRequest object containing the client's request
     * @param response the ServletResponse object containing the filter's response
     * @param chain    the FilterChain object used to invoke the next filter or servlet in the chain
     * @throws IOException      if an error occurs while handling the request or response
     * @throws ServletException if an error occurs while handling the request or response
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("state") != null);
        boolean isLoginPage = httpRequest.getRequestURI().equals(httpRequest.getContextPath() + "/login");

        if (isLoggedIn || isLoginPage) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect("/login");
        }
    }

    /**
     * Called by the web container to indicate to a filter that it is being taken out of service.
     * This method is only called once when the filter is being destroyed.
     */
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}