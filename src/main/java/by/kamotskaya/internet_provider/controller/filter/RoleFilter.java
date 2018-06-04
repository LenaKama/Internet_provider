package by.kamotskaya.internet_provider.controller.filter;

import by.kamotskaya.internet_provider.constant.ParamName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Sets user's role to 'guest' if role is not set yet.
 *
 * @author Lena Kamotskaya
 */
@WebFilter(urlPatterns = {"/*"})
public class RoleFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(RoleFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        LOGGER.log(Level.DEBUG, "I'm in init in RoleFilter");
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();


        String usAvatar = (String) session.getAttribute("usAvatar");
        if(usAvatar == null || usAvatar.isEmpty()) {
            session.setAttribute("usAvatar", "none");
        }

        String role = (String) session.getAttribute(ParamName.US_ROLE);
        if (role == null || role.isEmpty()) {
            session.setAttribute(ParamName.US_ROLE, ParamName.GUEST);
        }

        chain.doFilter(httpRequest, response);
    }

    @Override
    public void destroy() {
    }
}