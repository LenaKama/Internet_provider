package by.kamotskaya.internet_provider.dao;

import by.kamotskaya.internet_provider.entity.Session;
import by.kamotskaya.internet_provider.exception.ConnectionPoolException;
import by.kamotskaya.internet_provider.exception.DAOException;
import by.kamotskaya.internet_provider.pool.ConnectionPool;
import by.kamotskaya.internet_provider.pool.ProxyConnection;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class provides data access methods for operations with sessions.
 *
 * @author Lena Kamotskaya
 */
public class SessionDAO {

    private static final Logger LOGGER = LogManager.getLogger(SessionDAO.class);

    private final static String ADD_NEW_SESSION = "INSERT INTO session(session_start, us_login) VALUES(CURRENT_TIMESTAMP, ?)";
    private final static String ADD_TRAFFIC = "UPDATE session SET traffic_in = traffic_in + ?, traffic_out = traffic_out + ? WHERE session_id = ?";
    private final static String END_SESSION = "UPDATE session SET session_end = CURRENT_TIMESTAMP WHERE session_id = ?";
    private final static String ALL_USER_SESSIONS = "SELECT * FROM session WHERE us_login = ?";
    private final static String SELECT_TRAFFIC_IN = "SELECT SUM(traffic_in) FROM session WHERE us_login = ? AND YEAR(session_start) = YEAR(CURDATE()) AND MONTH(session_start) = MONTH(CURDATE())";
    private final static String SELECT_TRAFFIC_OUT = "SELECT SUM(traffic_out) FROM session WHERE us_login = ? AND YEAR(session_start) = YEAR(CURDATE()) AND MONTH(session_start) = MONTH(CURDATE())";
    private final static String SELECT_ACTIVE_SESSIONS = "SELECT session_id FROM session WHERE session_end IS NULL";

    private final ConnectionPool connectionPool;

    public SessionDAO() throws ConnectionPoolException {
        connectionPool = ConnectionPool.getInstance();
    }

    /**
     * Adds new row in session table that represents start of user's session.
     *
     * @param session {@link Session} entity object
     * @return id of started session
     * @throws DAOException
     */
    public int startSession(Session session) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_SESSION, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, session.getUsLogin());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DAOException("Exception from SessionDAO:", e);
        }
    }

    /**
     * Updates row of session when user sign out.
     *
     * @param sessionId id of session table
     * @throws DAOException
     */
    public void endSession(int sessionId) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(END_SESSION)) {
            statement.setInt(1, sessionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception from SessionDAO:", e);
        }
    }

    /**
     * Finds all sessions of concrete user.
     *
     * @param usLogin id from user table
     * @return {@link List} of {@link Session} objects
     * @throws DAOException
     */
    public List<Session> createSessionList(String usLogin) throws DAOException {
        List<Session> sessions = new ArrayList<>();
        try (ProxyConnection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(ALL_USER_SESSIONS)) {
            statement.setString(1, usLogin);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Session session = new Session();
                session.setSessionStart(resultSet.getDate("session_start"));
                session.setSessionEnd(resultSet.getDate("session_end"));
                session.setTrafficIn(resultSet.getInt("traffic_in"));
                session.setTrafficOut(resultSet.getInt("traffic_out"));
                sessions.add(session);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception from SessionDAO", e);
        }
        return sessions;
    }

    /**
     * Calculates incoming traffic of user.
     *
     * @param usLogin binds with user
     * @return value of incoming traffic
     * @throws DAOException
     */
    public int findTrafficInStatus(String usLogin) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_TRAFFIC_IN)) {
            statement.setString(1, usLogin);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DAOException("Exception from SessionDAO", e);
        }
    }

    /**
     * Calculates outcoming traffic of user.
     *
     * @param usLogin binds with user
     * @return value of outcoming traffic
     * @throws DAOException
     */
    public int findTrafficOutStatus(String usLogin) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_TRAFFIC_OUT)) {
            statement.setString(1, usLogin);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DAOException("Exception from SessionDAO", e);
        }
    }

    /**
     * Chooses sessions that aren't finished
     * @return {@link List} of {@link Integer} ids of sessions
     * @throws DAOException
     */
    public List<Integer> findActiveSessions() throws DAOException {
        List<Integer> sessionIds = new ArrayList<>();
        try (ProxyConnection connection = connectionPool.takeConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ACTIVE_SESSIONS);
            while (resultSet.next()) {
                sessionIds.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new DAOException("Exception from SessionDAO", e);
        }
        return sessionIds;
    }

    /**
     * Updates session adding traffic_in and traffic_out
     *
     * @param sessionId id of session table
     * @throws DAOException
     */
    public void addTraffic(int sessionId) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_TRAFFIC)) {
            Random random = new Random();
            statement.setInt(1, random.nextInt(50));
            statement.setInt(2, random.nextInt(25));
            statement.setInt(3, sessionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception from SessionDAO", e);
        }
    }
}