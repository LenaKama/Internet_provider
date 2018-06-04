package by.kamotskaya.internet_provider.controller.filter;

import by.kamotskaya.internet_provider.constant.ParamName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Sets user's locale to init locale if language is not set yet.
 *
 * @author Lena Kamotskaya
 */
@WebFilter(urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "locale", value = "en_US")})
public class LanguageFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(LanguageFilter.class);

    private static final String PARAM_NAME = "locale";
    private String locale;

    @Override
    public void init(FilterConfig filterConfig) {

        LOGGER.log(Level.DEBUG, "I'm in init in LanguageFilter");
        locale = filterConfig.getInitParameter(PARAM_NAME);
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        HttpSession session = httpRequest.getSession();
        String usLocale = (String) session.getAttribute(ParamName.US_LOCALE);

        if (usLocale == null) {
            session.setAttribute(ParamName.WELCOME_LOCALE, locale);
            session.setAttribute(ParamName.ACTIVE_LOCALE, locale);
        }
        chain.doFilter(httpRequest, response);
    }

    @Override
    public void destroy() {
        locale = null;
    }
}

