package by.kamotskaya.internet_provider.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
/**
 * @author Lena Kamotskaya
 */
public class ErrorMessageTag extends TagSupport {

    private String role;

    public void setMessage(String role) {
        this.role = role;
    }
    @Override
    public int doStartTag() throws JspException {
        try {
            String to;
            if ("hi".equalsIgnoreCase(role)) {
                to = "Hello, " + role;
            } else {
                to = "Welcome, " + role;
            }
            pageContext.getOut().write("<hr/>" + to + "<hr/>");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}