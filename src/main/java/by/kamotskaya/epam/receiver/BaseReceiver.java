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

    public static CommandResult changeLocale(RequestContent content) {

        String locale = content.getRequestParameters().get("locale")[0];
        String pageUrl = content.getRequestParameters().get("previousPage")[0];
        content.putSessionAttribute("userLocale", locale);
        LOGGER.log(Level.INFO, "Change the locale, pageUrl - " + pageUrl);

        return new CommandResult(CommandResult.ResponseType.REDIRECT, pageUrl);
    }


}
