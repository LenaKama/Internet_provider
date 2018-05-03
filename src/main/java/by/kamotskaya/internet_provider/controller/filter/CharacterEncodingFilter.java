package by.kamotskaya.internet_provider.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author Lena Kamotskaya
 */
@WebFilter
public class CharacterEncodingFilter implements Filter{

        private static final String PARAM_ENCODING = "encoding";
        private String code;

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
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
