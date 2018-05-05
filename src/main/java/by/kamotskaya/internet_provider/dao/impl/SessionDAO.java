package by.kamotskaya.internet_provider.dao.impl;

import by.kamotskaya.internet_provider.dao.BaseDAO;
import by.kamotskaya.internet_provider.entity.Session;
import by.kamotskaya.internet_provider.exception.DAOException;
import by.kamotskaya.internet_provider.pool.ConnectionPool;
import by.kamotskaya.internet_provider.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lena Kamotskaya
 */
public class SessionDAO implements BaseDAO<Session> {

    private static final Logger LOGGER = LogManager.getLogger(SessionDAO.class);

    private final static String ADD_NEW_SESSION = "INSERT INTO session(session_date, us_login) VALUES(CURRENT_DATE , ?)";
    private final static String ALL_SESSIONS_BY_USLOGIN = "SELECT * FROM session WHERE us_login = ?";
    private final static String SELECT_TRAFFIC_IN = "SELECT SUM(traffic_in) FROM session WHERE us_login = ? AND YEAR(session_start) = YEAR(CURDATE()) AND MONTH(session_start) = MONTH(CURDATE())";
    private final static String SELECT_TRAFFIC_OUT = "SELECT SUM(traffic_out) FROM session WHERE us_login = ? AND YEAR(session_start) = YEAR(CURDATE()) AND MONTH(session_start) = MONTH(CURDATE())";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void add(Session session) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection()) {
            PreparedStatement statement = connection.prepareStatement(ADD_NEW_SESSION);
            statement.setString(1, session.getUsLogin());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception from TariffDAO:", e);
        }
    }

    @Override
    public void delete(String us_login) throws DAOException {

    }

    @Override
    public void update(Session entity) throws DAOException {

    }

    public List<Session> createSessionList(String usLogin) throws DAOException {
        List<Session> sessions = new ArrayList<>();
        try (ProxyConnection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(ALL_SESSIONS_BY_USLOGIN)) {
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