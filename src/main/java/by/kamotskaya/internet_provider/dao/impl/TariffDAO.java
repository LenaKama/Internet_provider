package by.kamotskaya.internet_provider.dao.impl;

import by.kamotskaya.internet_provider.dao.BaseDAO;
import by.kamotskaya.internet_provider.entity.Tariff;
import by.kamotskaya.internet_provider.exception.DAOException;
import by.kamotskaya.internet_provider.pool.ConnectionPool;
import by.kamotskaya.internet_provider.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Lena Kamotskaya
 */
public class TariffDAO implements BaseDAO<Tariff> {

    private static final Logger LOGGER = LogManager.getLogger(TariffDAO.class);

    public final static String ADD_NEW_TARIFF = "INSERT INTO tariff(t_name, connection_payment, daily_fee, traffic_limit, speed_in, speed_out, overrun_fee) VALUES(?, ?, ?, ?, ?, ?, ?)";
    public final static String UPDATE_TARIFF = "UPDATE TABLE tariff SET t_name = ?, connection_payment = ?, daily_fee = ?, traffic_limit = ?, speed_in = ?, speed_out = ?, overrun_fee = ?, sale_percent = ?, sale_expiration_date = ? WHERE t_id = ?";
    public final static String DELETE_TARIFF = "ALTER TABLE tariff DELETE FROM tariff WHERE t_name = ?";
    public final static String FIND_ID_BY_NAME = "SELECT t_id FROM tariff where t_name = ?";
    public final static String SELECT_ALL = "SELECT * FROM tariff";
    public final static String ADD_SALE = "ALTER TABLE tariff SET sale_percent = ? SET sale_expiration_date = ? SET daily_fee = ? WHERE t_id = ?";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void add(Tariff tariff) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection()) {
            PreparedStatement statement = connection.prepareStatement(ADD_NEW_TARIFF);
            statement.setString(1, tariff.gettName());
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

        //закрывать statement!!! 9 jdk
       // return resultList;
    }

    @Override
    public void delete(String us_login) {

    }

  //  @Override
    public void update(Tariff tariff) throws DAOException {
        int tId = (int) this.findIdByName(tariff.gettName()).get();
        try (ProxyConnection connection = connectionPool.takeConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_TARIFF);
            statement.setString(1, tariff.gettName());
            statement.setDouble(2, tariff.getConnectionPayment());
            statement.setDouble(3, tariff.getDailyFee());
            statement.setInt(4, tariff.getTrafficLimit());
            statement.setString(5, tariff.getSpeedIn());
            statement.setString(6, tariff.getSpeedOut());
            statement.setDouble(7, tariff.getOverrunFee());
            statement.setInt(8, tariff.getSalePercent());
            statement.setDate(9, (Date) tariff.getSaleExpirationDate());
            statement.setInt(10, tId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception from TariffDAO:", e);
        }
    }

        public List<Tariff> findAllTariffs() throws DAOException {
        List<Tariff> tariffs = new ArrayList<>();
        try (ProxyConnection connection = connectionPool.takeConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                Tariff tariff = new Tariff();
                tariff.settName(resultSet.getString("t_name"));
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

    public Optional findIdByName(String tName) throws DAOException {
        Optional optionalId = Optional.empty();
            try (ProxyConnection connection = connectionPool.takeConnection()) {
                PreparedStatement statement = connection.prepareStatement(FIND_ID_BY_NAME);
                statement.setString(1, tName);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    optionalId = Optional.of(resultSet.getInt("t_id"));
                }
            } catch (SQLException e) {
                throw new DAOException("Exception from TariffDAO:", e);
            }
            return optionalId;
        }
/*
    public void addSale(String tName) {
        if( stringToUse.isPresent() )
            3
        {
            4
            System.out.println(stringToUse.get());
            5
    }
    */
}
