package by.kamotskaya.internet_provider.dao.impl;

import by.kamotskaya.internet_provider.dao.BaseDAO;
import by.kamotskaya.internet_provider.entity.Session;
import by.kamotskaya.internet_provider.exception.DAOException;
import by.kamotskaya.internet_provider.pool.ConnectionPool;
import by.kamotskaya.internet_provider.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Lena Kamotskaya
 */
public class SessionDAO implements BaseDAO<Session> {

    private static final Logger LOGGER = LogManager.getLogger(SessionDAO.class);

    public final static String ADD_NEW_SESSION = "INSERT INTO session(session_date, us_login) VALUES(CURRENT_DATE , ?)";

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


}
