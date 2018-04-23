package by.kamotskaya.epam.controller.filter;

import by.kamotskaya.epam.constant.PagePath;
import by.kamotskaya.epam.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Lena Kamotskaya
 */
//@WebFilter
public class UserAuthenticationFilter implements Filter {

    private static final String LOGIN = "login";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(LOGIN);
        if (user == null) {
            response.sendRedirect(PagePath.WELCOME);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
