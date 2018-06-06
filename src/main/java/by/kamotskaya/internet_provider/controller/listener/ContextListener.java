package by.kamotskaya.internet_provider.controller.listener;

import by.kamotskaya.internet_provider.exception.ConnectionPoolException;
import by.kamotskaya.internet_provider.pool.ConnectionPool;
import by.kamotskaya.internet_provider.pool.thread.OpeningBalanceController;
import by.kamotskaya.internet_provider.pool.thread.PaymentThread;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Class for initialization and destroying ConnectionPool.
 *
 * @author Lena Kamotskaya
 */
@WebListener
public class ContextListener implements ServletContextListener {

    private static final Logger LOGGER = LogManager.getLogger(ContextListener.class);

    /**
     * Initializes connection pool when web application initialization
     * process is starting.
     *
     * @param sce the ServletContextEvent containing the ServletContext
     *            that is being initialized
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().initializeConnectionPool();
            new OpeningBalanceController().start();
            new PaymentThread().start();
        } catch (ConnectionPoolException e) {
            LOGGER.log(Level.ERROR, "Exception from ContextListener.", e);
        }
    }

    /**
     * Destroys connection pool when ServletContext is about to be
     * shut down.
     *
     * @param sce the ServletContextEvent containing the ServletContext
     *            that is being destroyed
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().destroyConnectionPool();
        } catch (ConnectionPoolException e) {
            LOGGER.log(Level.ERROR, "Exception from ContextListener.", e);
        }
    }
}
