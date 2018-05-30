package by.kamotskaya.internet_provider.dao.impl;

import by.kamotskaya.internet_provider.dao.BaseDAO;
import by.kamotskaya.internet_provider.entity.Feedback;
import by.kamotskaya.internet_provider.entity.Tariff;
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
public class FeedbackDAO implements BaseDAO<Feedback> {

    private static final Logger LOGGER = LogManager.getLogger(FeedbackDAO.class);

    private final static String ADD_NEW_FEEDBACK = "INSERT INTO feedback(f_name, f_email, f_message) VALUES(?, ?, ?)";
    //  public final static String UPDATE_FEEDBACK = "UPDATE eedback SET f_name = ?, f_email = ?, us_email = ?, us_name = ?, us_surname = ?, us_passport = ?, us_role = ?, us_ban = ? WHERE us_login = ?";
    private final static String DELETE_FEEDBACK = "ALTER TABLE feedback DELETE FROM feedback WHERE f_id = ?";
    private final static String SELECT_ALL = "SELECT * FROM feedback";
    private final static String SELECT_USER_FEEDBACKS = "SELECT * FROM feedback WHERE us_login = ?";

    private final ConnectionPool connectionPool;

    public FeedbackDAO() throws ConnectionPoolException {
        connectionPool = ConnectionPool.getInstance();
    }

    @Override
    public void add(Feedback feedback) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection()) {
            PreparedStatement statement = connection.prepareStatement(ADD_NEW_FEEDBACK);
            statement.setString(1, feedback.getfName());
            statement.setString(2, feedback.getfEmail());
            statement.setString(3, feedback.getfMessage());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception from FeedbackDAO:", e);
        }
    }

    @Override
    public void delete(String us_login) throws DAOException {

    }

    public List<Feedback> loadAllFeedbacks() throws DAOException {
        List<Feedback> feedbacks = new ArrayList<>();
        try (ProxyConnection connection = connectionPool.takeConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                Feedback feedback = new Feedback();
                feedback.setfId(resultSet.getInt("f_id"));
                feedback.setfName(resultSet.getString("f_name"));
                feedback.setfEmail(resultSet.getString("f_email"));
                feedback.setfMessage(resultSet.getString("f_message"));
                feedback.setfAnswer(resultSet.getString("f_answer"));
                feedback.setUsLogin(resultSet.getString("us_login"));
                feedbacks.add(feedback);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception from FeedbackDAO:", e);
        }
        return feedbacks;
    }

    public List<Feedback> loadUserFeedbacks(String usLogin) throws DAOException {
        List<Feedback> feedbacks = new ArrayList<>();
        try (ProxyConnection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_FEEDBACKS)) {
            statement.setString(1, usLogin);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Feedback feedback = new Feedback();
                feedback.setfId(resultSet.getInt("f_id"));
                feedback.setfName(resultSet.getString("f_name"));
                feedback.setfEmail(resultSet.getString("f_email"));
                feedback.setfMessage(resultSet.getString("f_message"));
                feedback.setfAnswer(resultSet.getString("f_answer"));
                feedback.setUsLogin(resultSet.getString("us_login"));
                feedbacks.add(feedback);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception from FeedbackDAO:", e);
        }
        return feedbacks;
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
