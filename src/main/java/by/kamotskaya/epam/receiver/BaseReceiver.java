package by.kamotskaya.epam.receiver;

import by.kamotskaya.epam.command.CommandResult;
import by.kamotskaya.epam.constant.PagePath;
import by.kamotskaya.epam.controller.RequestContent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Lena Kamotskaya
 */
public class BaseReceiver {

    private static final Logger LOGGER = LogManager.getLogger(BaseReceiver.class);

    public BaseReceiver() {
    }

    public static CommandResult selectLanguage(RequestContent content) {

        String language;
        String country;
        String lang = content.getRequestParameters().get("Language")[0];///??????

        if ("Russian".equals(lang)) {
            language = "ru";
            country = "RU";
        } else {
            language = "en";
            country = "US";
        }
        content.getRequestParameters().put("value", new String[]{language + "_" + country});

        LOGGER.log(Level.INFO, "Got a language");

        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.HOME);
    }


}
