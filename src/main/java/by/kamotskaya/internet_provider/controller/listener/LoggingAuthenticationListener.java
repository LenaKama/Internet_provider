package by.kamotskaya.internet_provider.controller.listener;

import by.kamotskaya.internet_provider.constant.ParamName;
import by.kamotskaya.internet_provider.entity.User;
import by.kamotskaya.internet_provider.pool.thread.TrafficCounterThread;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Provides logging of user's authentications operations
 *
 * @author Lena Kamotskaya
 */
@WebListener
public class LoggingAuthenticationListener implements HttpSessionAttributeListener {

    private static final Logger LOGGER = LogManager.getLogger(LoggingAuthenticationListener.class);

    private ScheduledExecutorService executorService;

    public LoggingAuthenticationListener() {
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {

        if (event.getName().equals(ParamName.USER)) {
            User user = (User) event.getValue();
            LOGGER.log(Level.INFO, "User " + user.getUsLogin() + " (" + user.getUsRole() + ") signed in.");
            if (user.getTId() != 0) {
                executorService = Executors.newScheduledThreadPool(1);
                executorService.scheduleAtFixedRate(new TrafficCounterThread(), 5, 10, TimeUnit.SECONDS);
            }
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

        if (event.getName().equals(ParamName.USER)) {
            User user = (User) event.getValue();
            LOGGER.log(Level.INFO, "User " + user.getUsLogin() + " (" + user.getUsRole() + ") signed out.");
        }
        executorService.shutdown();
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }
}
