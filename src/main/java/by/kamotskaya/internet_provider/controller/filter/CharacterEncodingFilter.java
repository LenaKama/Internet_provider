package by.kamotskaya.internet_provider.controller.filter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * @author Lena Kamotskaya
 */
@WebFilter(urlPatterns = { "/*" },
        initParams = {
                @WebInitParam(name = "encoding", value = "UTF-8")})
public class CharacterEncodingFilter implements Filter{

    private static final Logger LOGGER = LogManager.getLogger(CharacterEncodingFilter.class);

    private static final String PARAM_ENCODING = "encoding";
        private String code;

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            LOGGER.log(Level.DEBUG, "I'm in init in CharacterFilter");
            code = filterConfig.getInitParameter(PARAM_ENCODING);
        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
                throws IOException, ServletException {
            String codeRequest = servletRequest.getCharacterEncoding();
            if (code != null && !code.equalsIgnoreCase(codeRequest)) {
                servletRequest.setCharacterEncoding(code);
                servletResponse.setCharacterEncoding(code);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }

        @Override
        public void destroy() {
            code = null;
        }
}
