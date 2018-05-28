package by.kamotskaya.internet_provider.dao.impl;

import by.kamotskaya.internet_provider.entity.Transaction;
import by.kamotskaya.internet_provider.exception.ConnectionPoolException;
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
public class TransactionDAO {

    private static final Logger LOGGER = LogManager.getLogger(TransactionDAO.class);

    private final static String ADD_NEW_TRANSACTION = "INSERT INTO transaction(tr_date, tr_sum, tr_info, us_login) VALUES(CURRENT_DATE, ?, ?, ?)";
    private final static String ALL_USER_TRANSACTIONS = "SELECT * FROM transaction WHERE us_login = ?";
    private final static String FIND_THIS_MONTH_TRANSACTIONS = "SELECT SUM(tr_sum) FROM transaction WHERE MONTH(tr_date) = MONTH(CURDATE()) AND YEAR(tr_date) = YEAR(CURDATE()) AND us_login = ?";

    private final ConnectionPool connectionPool;

    public TransactionDAO() throws ConnectionPoolException {
        connectionPool = ConnectionPool.getInstance();
    }

    public void add(Transaction transaction) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection()) {
            PreparedStatement statement = connection.prepareStatement(ADD_NEW_TRANSACTION);
            statement.setDouble(1, transaction.getTrSum());
            statement.setString(2, transaction.getTrInfo());
            statement.setString(3, transaction.getUsLogin());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception from UserDAO:", e);
        }
    }

    public List<Transaction> createTransactionList(String usLogin) throws DAOException {
        List<Transaction> transactions = new ArrayList<>();
        try (ProxyConnection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(ALL_USER_TRANSACTIONS)) {
            statement.setString(1, usLogin);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setTrDate(resultSet.getDate("tr_date"));
                transaction.setTrSum(resultSet.getDouble("tr_sum"));
                transaction.setTrInfo(resultSet.getString("tr_info"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception from TransactionDAO", e);
        }
        return transactions;
    }

    public Double findCurrentBalance(String usLogin) throws DAOException {
        Double curBalance;
        try (ProxyConnection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_THIS_MONTH_TRANSACTIONS)) {
            OpeningBalanceDAO openingBalanceDAO = new OpeningBalanceDAO();
            statement.setString(1, usLogin);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            curBalance = openingBalanceDAO.findOpeningBalance(usLogin) + resultSet.getDouble(1);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception from TransactionDAO", e);
        }
        return curBalance;
    }
}
