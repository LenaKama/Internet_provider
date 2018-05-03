package by.kamotskaya.internet_provider.dao.impl;

import by.kamotskaya.internet_provider.dao.BaseDAO;
import by.kamotskaya.internet_provider.entity.Transaction;
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
public class TransactionDAO implements BaseDAO<Transaction> {

    private static final Logger LOGGER = LogManager.getLogger(TransactionDAO.class);

    public final static String ADD_NEW_TRANSACTION = "INSERT INTO transaction(tr_date, tr_sum, tr_info, us_login) VALUES(?, ?, ?, ?)";
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


}
