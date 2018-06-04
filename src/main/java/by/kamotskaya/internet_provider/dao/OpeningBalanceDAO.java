package by.kamotskaya.internet_provider.dao;

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
import java.util.Optional;

/**
 * Class provides data access methods for operations with openingBalances.
 *
 * @author Lena Kamotskaya
 */
public class OpeningBalanceDAO {

    private static final Logger LOGGER = LogManager.getLogger(OpeningBalanceDAO.class);

    private final static String ADD_NEW_OPENING_BALANCE = "INSERT INTO opening_balance(ob_date, ob_sum, us_login) VALUES(CURRENT_DATE, ?, ?)";
    private final static String SELECT_LAST_MONTH_BALANCE = "SELECT ob_sum FROM opening_balance WHERE MONTH(ob_date) = MONTH(DATE_SUB(CURDATE(), INTERVAL 1 DAY)) AND YEAR(ob_date) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 DAY)) AND us_login = ?";
    private final static String SELECT_THIS_MONTH_BALANCE = "SELECT ob_sum FROM opening_balance WHERE MONTH(ob_date) = MONTH(CURDATE()) AND YEAR(ob_date) = YEAR(CURDATE()) AND us_login = ?";

    private final ConnectionPool connectionPool;

    public OpeningBalanceDAO() throws ConnectionPoolException {
    connectionPool = ConnectionPool.getInstance();
    }

    /**
     * Adds openingBalance in application base.
     *
     * @param openingBalance {@link OpeningBalance}
     * @throws DAOException
     */
    public void add(OpeningBalance openingBalance) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_NEW_OPENING_BALANCE)) {
            statement.setDouble(1, openingBalance.getObSum());
            statement.setString(2, openingBalance.getUsLogin());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception from OpeningBalanceDAO", e);
        }
    }

    /**
     * Finds opening balance of this or previous month.
     *
     * @param usLogin foreign key with User table
     * @param thisMonthFlag sets kind of month for executing
     * @return {@link Optional} of {@link Double} sum of balance
     * @throws DAOException
     */
    public Optional<Double> findOpeningBalance(String usLogin, boolean thisMonthFlag) throws DAOException {
        Optional<Double> openingBalance;
        try (ProxyConnection connection = connectionPool.takeConnection();
             PreparedStatement thisMonthStatement = connection.prepareStatement(SELECT_THIS_MONTH_BALANCE);
            PreparedStatement lastMonthStatement = connection.prepareStatement(SELECT_LAST_MONTH_BALANCE)) {
            ResultSet resultSet;
            if (thisMonthFlag) {
                    thisMonthStatement.setString(1, usLogin);
                    resultSet = thisMonthStatement.executeQuery();
                } else {
                    lastMonthStatement.setString(1, usLogin);
                    resultSet = lastMonthStatement.executeQuery();
                }
            if (resultSet.next()) {
                openingBalance = Optional.of(resultSet.getDouble(1));
            } else {
                openingBalance = Optional.empty();
            }
        } catch (SQLException e) {
            throw new DAOException("Exception from OpeningBalanceDAO", e);
        }
        return openingBalance;
    }
}

