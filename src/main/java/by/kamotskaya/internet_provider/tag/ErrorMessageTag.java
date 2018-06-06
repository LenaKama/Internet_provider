package by.kamotskaya.internet_provider.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
/**
 * Class for outputting error message.
 *
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
            String to = "ERROR:<hr> " + message + "</hr>";
            pageContext.getOut().write(to);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}