package by.kamotskaya.internet_provider.controller.listener;

import by.kamotskaya.internet_provider.constant.ParamName;
import by.kamotskaya.internet_provider.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Provides logging of user's authentications operations
 *
 * @author Lena Kamotskaya
 */
@WebListener
public class LoggingAuthenticationListener implements HttpSessionAttributeListener{

    private static final Logger LOGGER = LogManager.getLogger(LoggingAuthenticationListener.class);

    public LoggingAuthenticationListener() {
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {

        if (event.getName().equals(ParamName.USER)) {
            User user = (User) event.getValue();
            LOGGER.log(Level.INFO, "User " + user.getUsLogin() + " (" + user.getUsRole() + ") signed in.");
        }

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

        if (event.getName().equals(ParamName.USER)) {
            User user = (User) event.getValue();
            LOGGER.log(Level.INFO, "User " + user.getUsLogin() + " (" + user.getUsRole() + ") signed out.");
        }

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }
}
