package by.kamotskaya.internet_provider.dao;

import by.kamotskaya.internet_provider.entity.Session;
import by.kamotskaya.internet_provider.exception.ConnectionPoolException;
import by.kamotskaya.internet_provider.exception.DAOException;
import by.kamotskaya.internet_provider.pool.ConnectionPool;
import by.kamotskaya.internet_provider.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lena Kamotskaya
 */
public class SessionDAO {

    private static final Logger LOGGER = LogManager.getLogger(SessionDAO.class);

    private final static String ADD_NEW_SESSION = "INSERT INTO session(session_start, us_login) VALUES(CURRENT_TIMESTAMP, ?)";
    private final static String END_SESSION = "UPDATE session SET session_end = CURRENT_TIMESTAMP , traffic_in = ?, traffic_out = ? WHERE us_login = ?";
    private final static String ALL_USER_SESSIONS = "SELECT * FROM session WHERE us_login = ?";
    private final static String SELECT_TRAFFIC_IN = "SELECT SUM(traffic_in) FROM session WHERE us_login = ? AND YEAR(session_start) = YEAR(CURDATE()) AND MONTH(session_start) = MONTH(CURDATE())";
    private final static String SELECT_TRAFFIC_OUT = "SELECT SUM(traffic_out) FROM session WHERE us_login = ? AND YEAR(session_start) = YEAR(CURDATE()) AND MONTH(session_start) = MONTH(CURDATE())";

    private final ConnectionPool connectionPool;

    public SessionDAO() throws ConnectionPoolException {
        connectionPool = ConnectionPool.getInstance();
    }

    public Session startSession(Session session) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_SESSION, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, session.getUsLogin());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int sessionId = resultSet.getInt(1);
            session.setSessionId(sessionId);
        } catch (SQLException e) {
            throw new DAOException("Exception from SessionDAO:", e);
        }
        return session;
    }

    public void endSession(Session session) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(END_SESSION)) {
            statement.setInt(1, session.getTrafficIn());
            statement.setInt(2, session.getTrafficOut());
            statement.setString(3, session.getUsLogin());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception from SessionDAO:", e);
        }
    }

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

    public int findTrafficInStatus(String usLogin) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_TRAFFIC_IN)) {
        statement.setString(1, usLogin);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return  resultSet.getInt(1);
    } catch (SQLException e) {
        throw new DAOException("Exception from SessionDAO", e);
    }
    }

    public int findTrafficOutStatus(String usLogin) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_TRAFFIC_OUT)) {
            statement.setString(1, usLogin);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return  resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DAOException("Exception from SessionDAO", e);
        }
    }
}