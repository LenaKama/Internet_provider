package by.kamotskaya.epam.dao.impl;

import by.kamotskaya.epam.dao.BaseDAO;
import by.kamotskaya.epam.entity.Tariff;
import by.kamotskaya.epam.exception.DAOException;
import by.kamotskaya.epam.pool.ConnectionPool;
import by.kamotskaya.epam.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lena Kamotskaya
 */
public class TariffDAO implements BaseDAO<Tariff> {

    private static final Logger LOGGER = LogManager.getLogger(TariffDAO.class);

    public final static String ADD_NEW_TARIFF = "INSERT INTO tariff(t_name, connection_payment, daily_fee, traffic_limit, speed_in, speed_out, overrun_fee) VALUES(?, ?, ?, ?, ?, ?, ?)";
    public final static String UPDATE_TARIFF = "ALTER TABLE tariff SET t_name = ? SET connection_payment = ? SET daily_fee = ? FROM user";
    public final static String DELETE_TARIFF = "ALTER TABLE tariff DELETE FROM tariff WHERE t_name = ?";
    public final static String FIND_BY_NAME = "SELECT t_id FROM tariff where t_name = ?";
    public final static String ADD_SALE = "ALTER TABLE tariff SET sale_percent = ? SET sale_expiration_date = ? SET daily_fee = ? WHERE t_id = ?";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public List<Object> add(Tariff tariff) throws DAOException {
        List<Object> resultList = new ArrayList<>();
        try (ProxyConnection connection = connectionPool.takeConnection()) {
//where to storage sql scripts
            PreparedStatement statement = connection.prepareStatement(ADD_NEW_TARIFF);
            statement.setString(1, tariff.gettName());
            statement.setDouble(2, tariff.getConnectionPayment());
            statement.setDouble(3, tariff.getDailyFee());
            statement.setInt(4, tariff.getTrafficLimit());
            statement.setString(5, tariff.getSpeedIn());
            statement.setString(6, tariff.getSpeedOut());
            statement.setDouble(7, tariff.getOverrunFee());
            statement.executeUpdate();
            resultList.add("user " + tariff.gettName() + " is inserted.");

        } catch (SQLException e) {
            throw new DAOException("Exception from TariffDAO:", e);
        }

        //закрывать statement!!! 9 jdk
        return resultList;
    }

    @Override
    public void delete(String us_login) {

    }

    @Override
    public void update(String t_id) {

    }
}
