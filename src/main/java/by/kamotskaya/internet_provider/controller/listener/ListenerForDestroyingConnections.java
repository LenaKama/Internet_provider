package by.kamotskaya.internet_provider.controller.listener;

import by.kamotskaya.internet_provider.exception.ConnectionPoolException;
import by.kamotskaya.internet_provider.pool.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

/**
 * @author Lena Kamotskaya
 */
@WebListener
public class ListenerForDestroyingConnections implements ServletContextListener {

    /*
            * Initializes connection pool when web application initialization
     * process is starting.
     *
             * @param sce the ServletContextEvent containing the ServletContext
     * that is being initialized
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().initializeConnectionPool();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    /**
     * Destroys connection pool when ServletContext is about to be
     * shut down.
     *
     * @param sce the ServletContextEvent containing the ServletContext
     * that is being destroyed
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().destroyConnectionPool();
        } catch (SQLException | ConnectionPoolException e) {

        }
    }
}
