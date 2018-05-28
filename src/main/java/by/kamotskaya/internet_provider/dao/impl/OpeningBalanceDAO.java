package by.kamotskaya.internet_provider.dao.impl;

import by.kamotskaya.internet_provider.entity.OpeningBalance;
import by.kamotskaya.internet_provider.exception.ConnectionPoolException;
import by.kamotskaya.internet_provider.exception.DAOException;
import by.kamotskaya.internet_provider.pool.ConnectionPool;
import by.kamotskaya.internet_provider.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Lena Kamotskaya
 */
public class OpeningBalanceDAO {

    private static final Logger LOGGER = LogManager.getLogger(OpeningBalanceDAO.class);

    private final static String ADD_NEW_OPENING_BALANCE = "INSERT INTO opening_balance(ob_date, ob_sum, us_login) VALUES(?, ?, ?)";
    private final static String SELECT_LATEST_BALANCE = "SELECT ob_sum FROM opening_balance WHERE MONTH(ob_date) = MONTH(CURDATE()) AND YEAR(ob_date) = YEAR(CURDATE()) AND us_login = ?";

    private final ConnectionPool connectionPool;

    public OpeningBalanceDAO() throws ConnectionPoolException {
    connectionPool = ConnectionPool.getInstance();
    }

    public void add(OpeningBalance openingBalance) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_NEW_OPENING_BALANCE)) {
            statement.setDate(1, openingBalance.getObDate());
            statement.setDouble(2, openingBalance.getObSum());
            statement.setString(3, openingBalance.getUsLogin());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception from OpeningBalanceDAO", e);
        }
    }

    public Double findOpeningBalance(String usLogin) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_LATEST_BALANCE)) {
            statement.setString(1, usLogin);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getDouble(1);
        } catch (SQLException e) {
            throw new DAOException("Exception from OpeningBalanceDAO", e);
        }
    }
}

