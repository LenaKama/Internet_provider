package by.kamotskaya.internet_provider.pool.thread;

import by.kamotskaya.internet_provider.dao.SessionDAO;
import by.kamotskaya.internet_provider.exception.ConnectionPoolException;
import by.kamotskaya.internet_provider.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class-thread for calculating random traffic value
 * when user signed in.
 *
 * @author Lena Kamotskaya
 */
public class TrafficCounterThread extends Thread {

    private static final Logger LOGGER = LogManager.getLogger(TrafficCounterThread.class);

    @Override
    public void run() {
        try {
            SessionDAO sessionDAO = new SessionDAO();
            for (int sessionId : sessionDAO.findActiveSessions()) {
                sessionDAO.addTraffic(sessionId);
            }
        } catch (ConnectionPoolException | DAOException e) {
            LOGGER.log(Level.ERROR, "Error while counting the traffic.");
        }
    }
}
