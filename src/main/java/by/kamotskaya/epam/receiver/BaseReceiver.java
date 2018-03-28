package by.kamotskaya.epam.receiver;

import by.kamotskaya.epam.command.CommandResult;
import by.kamotskaya.epam.content.RequestContent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Lena Kamotskaya
 */
public class BaseReceiver {

    private static final Logger LOGGER = LogManager.getLogger(BaseReceiver.class);
    private static final String FILE_PAGE = "/pages/file.jsp";

    public BaseReceiver() {
    }

    public static CommandResult selectLanguage(RequestContent content) {

        String language;
        String country;
        String lang = content.getRequestParameters().get("Language")[0];///??????

        if (lang.equals("Russian")) {
            language = "ru";
            country = "RU";
        } else {
            language = "en";
            country = "US";
        }
       // content.getRequestParameters().put("value", language + "_" + country);

        LOGGER.log(Level.INFO, "Got a language");

        return new CommandResult(CommandResult.ResponseType.FORWARD, FILE_PAGE);
    }
}
