package by.kamotskaya.epam.tag;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
/**
 * @author Lena Kamotskaya
 */
public class RoleTag extends TagSupport {

    private String role;

    public void setRole(String role) {
        this.role = role;
    }
    @Override
    public int doStartTag() throws JspException {
        try {
            String to = null;
            if ("administrator".equalsIgnoreCase(role)) {
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