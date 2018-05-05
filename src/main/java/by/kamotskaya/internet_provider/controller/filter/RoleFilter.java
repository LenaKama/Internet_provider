package by.kamotskaya.internet_provider.controller.filter;

import by.kamotskaya.internet_provider.constant.ParamNames;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Sets role to 'guest' if role is not set yet.
 *
 * @author Lena Kamotskaya
 */
@WebFilter(urlPatterns = {"/*"})
public class RoleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();

        session.setAttribute(ParamNames.WELCOME_LOCALE, "en_US");

        String role = (String) session.getAttribute(ParamNames.ROLE);
        if (role == null || role.isEmpty()) {
            session.setAttribute(ParamNames.ROLE, "quest");
        }

        chain.doFilter(httpRequest, response);
    }

    @Override
    public void destroy() {
    }
}