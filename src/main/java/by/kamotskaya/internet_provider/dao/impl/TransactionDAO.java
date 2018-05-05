package by.kamotskaya.internet_provider.dao.impl;

import by.kamotskaya.internet_provider.dao.BaseDAO;
import by.kamotskaya.internet_provider.entity.Session;
import by.kamotskaya.internet_provider.entity.Transaction;
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
public class TransactionDAO implements BaseDAO<Transaction> {

    private static final Logger LOGGER = LogManager.getLogger(TransactionDAO.class);

    private final static String ADD_NEW_TRANSACTION = "INSERT INTO transaction(tr_date, tr_sum, tr_info, us_login) VALUES(?, ?, ?, ?)";
    private final static String ALL_TRANSACTIONS_BY_USLOGIN = "SELECT * FROM transaction WHERE us_login = ?";
  //  public final static String UPDATE_TRANSACTION = "UPDATE TABLE transaction SET t_name = ?, connection_payment = ?, daily_fee = ?, traffic_limit = ?, speed_in = ?, speed_out = ?, overrun_fee = ?, sale_percent = ?, sale_expiration_date = ? WHERE t_id = ?";
   // public final static String DELETE_TRANSACTION = "ALTER TABLE transaction DELETE FROM transaction WHERE t_name = ?";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void add(Transaction transaction) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection()) {
            PreparedStatement statement = connection.prepareStatement(ADD_NEW_TRANSACTION);
            statement.setDate(1, transaction.getTrDate());
            statement.setDouble(2, transaction.getTrSum());
            statement.setString(3, transaction.getTrInfo());
            statement.setString(4, transaction.getUsLogin());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception from UserDAO:", e);
        }
    }

    @Override
    public void delete(String us_login) throws DAOException {

    }

    @Override
    public void update(Transaction entity) throws DAOException {

    }

    public List<Transaction> createTransactionList(String usLogin) throws DAOException {
        List<Transaction> transactions = new ArrayList<>();
        try (ProxyConnection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(ALL_TRANSACTIONS_BY_USLOGIN)) {
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
}
