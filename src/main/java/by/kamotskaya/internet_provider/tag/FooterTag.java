package by.kamotskaya.internet_provider.tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * @author Lena Kamotskaya
 */
public class FooterTag extends TagSupport {

    private static final String FOOTER = "<footer>" +
            "<p class=\"copyright\">Copyright \u00A9 Lena Kamotskaya</p>" +
            "</footer>";

    @Override
    public int doStartTag() {

        try {
            JspWriter writer = pageContext.getOut();
            writer.write(FOOTER);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}