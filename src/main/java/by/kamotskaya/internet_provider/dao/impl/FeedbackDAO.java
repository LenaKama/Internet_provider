package by.kamotskaya.internet_provider.dao.impl;

import by.kamotskaya.internet_provider.dao.BaseDAO;
import by.kamotskaya.internet_provider.entity.Feedback;
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
public class FeedbackDAO implements BaseDAO<Feedback> {

    private static final Logger LOGGER = LogManager.getLogger(FeedbackDAO.class);

    public final static String ADD_NEW_FEEDBACK = "INSERT INTO feedback(f_name, f_email, f_message) VALUES(?, ?, ?)";
    //  public final static String UPDATE_FEEDBACK = "UPDATE TABLE feedback SET f_name = ?, f_email = ?, us_email = ?, us_name = ?, us_surname = ?, us_passport = ?, us_role = ?, us_ban = ? WHERE us_login = ?";
    public final static String DELETE_FEEDBACK = "ALTER TABLE feedback DELETE FROM feedback WHERE f_id = ?";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void add(Feedback feedback) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection()) {
            PreparedStatement statement = connection.prepareStatement(ADD_NEW_FEEDBACK);
            statement.setString(1, feedback.getfName());
            statement.setString(2, feedback.getfEmail());
            statement.setString(3, feedback.getfMessage());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception from TariffDAO:", e);
        }
    }

    @Override
    public void delete(String us_login) throws DAOException {

    }

    //@Override
    public void delete(int fId) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_FEEDBACK);
            statement.setInt(1, fId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception from TariffDAO:", e);
        }
    }

    @Override
    public void update(Feedback feedback) throws DAOException {

    }


}
