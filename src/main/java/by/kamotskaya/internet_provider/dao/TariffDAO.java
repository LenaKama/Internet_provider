package by.kamotskaya.internet_provider.dao;

import by.kamotskaya.internet_provider.entity.Tariff;
import by.kamotskaya.internet_provider.exception.ConnectionPoolException;
import by.kamotskaya.internet_provider.exception.DAOException;
import by.kamotskaya.internet_provider.pool.ConnectionPool;
import by.kamotskaya.internet_provider.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lena Kamotskaya
 */
public class TariffDAO {

    private static final Logger LOGGER = LogManager.getLogger(TariffDAO.class);

    private final static String ADD_NEW_TARIFF = "INSERT INTO tariff(t_name, connection_payment, daily_fee, traffic_limit, speed_in, speed_out, overrun_fee) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private final static String UPDATE_TARIFF = "UPDATE tariff SET t_name = ?, connection_payment = ?, daily_fee = ?, traffic_limit = ?, speed_in = ?, speed_out = ?, overrun_fee = ?, sale_percent = ?, sale_expiration_date = ? WHERE t_id = ?";
    private final static String DELETE_TARIFF = "DELETE FROM tariff WHERE t_id = ?";
    private final static String FIND_TARIFF_BY_ID = "SELECT * FROM tariff where t_id = ?";
    private final static String SELECT_ALL = "SELECT * FROM tariff";
   public final static String ADD_SALE = "ALTER TABLE tariff SET sale_percent = ? SET sale_expiration_date = ? SET daily_fee = ? WHERE t_id = ?";
    private final static String FIND_DAILY_FEE = "SELECT daily_fee FROM tariff WHERE t_id = ?";
    private final static String FIND_OVERRUN_FEE = "SELECT overrun_fee FROM tariff WHERE t_id = ?";

    private final ConnectionPool connectionPool;

    public TariffDAO() throws ConnectionPoolException {
        connectionPool = ConnectionPool.getInstance();
    }

    public void add(Tariff tariff) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_NEW_TARIFF)) {
            statement.setString(1, tariff.getTName());
            statement.setDouble(2, tariff.getConnectionPayment());
            statement.setDouble(3, tariff.getDailyFee());
            statement.setInt(4, tariff.getTrafficLimit());
            statement.setString(5, tariff.getSpeedIn());
            statement.setString(6, tariff.getSpeedOut());
            statement.setDouble(7, tariff.getOverrunFee());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception from TariffDAO:", e);
        }
    }

    public void delete(int tId) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_TARIFF)) {
            statement.setInt(1, tId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception from UserDAO: ", e);
        }
    }

    public void update(Tariff tariff) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TARIFF)) {
            statement.setString(1, tariff.getTName());
            statement.setDouble(2, tariff.getConnectionPayment());
            statement.setDouble(3, tariff.getDailyFee());
            statement.setInt(4, tariff.getTrafficLimit());
            statement.setString(5, tariff.getSpeedIn());
            statement.setString(6, tariff.getSpeedOut());
            statement.setDouble(7, tariff.getOverrunFee());
            statement.setInt(8, tariff.getSalePercent());
            statement.setDate(9, tariff.getSaleExpirationDate());
            statement.setInt(10, tariff.getTId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception from TariffDAO:", e);
        }
    }

    public List<Tariff> findAllTariffs() throws DAOException {
        List<Tariff> tariffs = new ArrayList<>();
        try (ProxyConnection connection = connectionPool.takeConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                Tariff tariff = new Tariff();
                tariff.setTId(resultSet.getInt("t_id"));
                tariff.setTName(resultSet.getString("t_name"));
                tariff.setConnectionPayment(resultSet.getDouble("connection_payment"));
                tariff.setDailyFee(resultSet.getDouble("daily_fee"));
                tariff.setTrafficLimit(resultSet.getInt("traffic_limit"));
                tariff.setSpeedIn(resultSet.getString("speed_in"));
                tariff.setSpeedOut(resultSet.getString("speed_out"));
                tariff.setOverrunFee(resultSet.getDouble("overrun_fee"));
                tariff.setSalePercent(resultSet.getInt("sale_percent"));
                tariff.setSaleExpirationDate(resultSet.getDate("sale_expiration_date"));
                tariffs.add(tariff);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception from TariffDAO:", e);
        }
        return tariffs;
    }

    public Tariff findTariffById(int tId) throws DAOException {
        Tariff tariff = new Tariff();
        try (ProxyConnection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_TARIFF_BY_ID)) {
            statement.setInt(1, tId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            tariff.setTId(tId);
            tariff.setTName(resultSet.getString("t_name"));
            tariff.setConnectionPayment(resultSet.getDouble("connection_payment"));
            tariff.setDailyFee(resultSet.getDouble("daily_fee"));
            tariff.setTrafficLimit(resultSet.getInt("traffic_limit"));
            tariff.setSpeedIn(resultSet.getString("speed_in"));
            tariff.setSpeedOut(resultSet.getString("speed_out"));
            tariff.setOverrunFee(resultSet.getDouble("overrun_fee"));
            tariff.setSalePercent(resultSet.getInt("sale_percent"));
            tariff.setSaleExpirationDate(resultSet.getDate("sale_expiration_date"));
        } catch (SQLException e) {
            throw new DAOException("Exception from TariffDAO:", e);
        }
        return tariff;
    }

}
