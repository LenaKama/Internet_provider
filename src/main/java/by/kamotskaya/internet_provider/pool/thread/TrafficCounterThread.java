package by.kamotskaya.internet_provider.pool.thread;

import by.kamotskaya.internet_provider.dao.SessionDAO;
import by.kamotskaya.internet_provider.exception.ConnectionPoolException;
import by.kamotskaya.internet_provider.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Lena Kamotskaya
 */
public class TrafficCounterThread extends Thread {
    private static final Logger LOGGER = LogManager.getLogger(TrafficCounterThread.class);

    @Override
    public void run() {
        try {
            SessionDAO sessionDAO = new SessionDAO();
            System.out.println("start thread");
            for (int sessionId : sessionDAO.findActiveSessions()) {
                LOGGER.log(Level.DEBUG, "usLogin - " + sessionId);
                sessionDAO.addTraffic(sessionId);
            }
        } catch (ConnectionPoolException | DAOException e) {
            System.out.println("exception");
        }
    }
}
