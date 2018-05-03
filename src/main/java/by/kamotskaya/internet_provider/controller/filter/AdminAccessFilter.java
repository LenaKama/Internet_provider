package by.kamotskaya.internet_provider.controller.filter;

import by.kamotskaya.internet_provider.constant.PagePath;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Redirects to login page if role is not 'admin' and
 * visitor trying to access admin pages.
 */
/**
 * @author Lena Kamotskaya
 */
@WebFilter
public class AdminAccessFilter implements Filter {

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

        String role = (String) session.getAttribute("usRole");

        if (!role.equals("admin")) {
            String contextPath = httpRequest.getContextPath();
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect(contextPath + PagePath.WELCOME);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
