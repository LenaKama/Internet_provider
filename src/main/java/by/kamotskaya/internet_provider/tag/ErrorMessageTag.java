package by.kamotskaya.internet_provider.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
/**
 * @author Lena Kamotskaya
 */
public class ErrorMessageTag extends TagSupport {

    private String message;

    public void setMessage(String role) {
        this.message = role;
    }
    @Override
    public int doStartTag() throws JspException {
        try {
            String to;
            if ("hi".equalsIgnoreCase(message)) {
                to = "Hello, " + message;
            } else {
                to = "Welcome, " + message;
            }
            pageContext.getOut().write("<hr/>" + to + "<hr/>");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}