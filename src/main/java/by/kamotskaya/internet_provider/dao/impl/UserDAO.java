package by.kamotskaya.internet_provider.dao.impl;

import by.kamotskaya.internet_provider.entity.User;
import by.kamotskaya.internet_provider.exception.DAOException;
import by.kamotskaya.internet_provider.dao.BaseDAO;
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
public class UserDAO implements BaseDAO<User> {

    private static final Logger LOGGER = LogManager.getLogger(UserDAO.class);

    public final static String ADD_NEW_CLIENT = "INSERT INTO user(us_login, us_password, us_email, us_name, us_surname, us_passport, us_role, us_ban, t_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public final static String FIND_PASSWORD_BY_LOGIN = "SELECT us_password FROM user WHERE us_login = ?";
    public final static String GET_USER_INFO = "SELECT * FROM user WHERE us_login = ?";
    public final static String SELECT_ALL_USERS = "SELECT * FROM user";
    public final static String UPDATE_USER = "UPDATE TABLE user SET us_login = ?, us_password = ?, us_email = ?, us_name = ?, us_surname = ?, us_passport = ?, us_role = ?, us_ban = ? WHERE us_login = ?";
    public final static String DELETE_USER = "ALTER TABLE user DELETE FROM user WHERE us_login = ?";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    public UserDAO() {
    }

    @Override
    public void add(User user) throws DAOException {
        List<Object> resultList = new ArrayList<>();
        try (ProxyConnection connection = connectionPool.takeConnection()) {

            PreparedStatement statement = connection.prepareStatement(ADD_NEW_CLIENT);
            statement.setString(1, user.getUsLogin());
            statement.setString(2, user.getUsPassword());
            statement.setString(3, user.getUsEmail());
            statement.setString(4, user.getUsName());
            statement.setString(5, user.getUsSurname());
            statement.setString(6, user.getUsPassport());
            statement.setString(7, user.getUsRole());
            statement.setBoolean(8, user.isUsBan());
            statement.setInt(9, 1);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception from UserDAO:", e);
        }
//map в которую запихивать все имена значения с формы решистрации, запускаем процесс валидации и если что-то не так выкидывать что неверно и отправляем клиенту checker будет
        //закрывать statement!!! 9 jdk
    }

    public List<User> findAllUsers() throws DAOException {
        List<User> users = new ArrayList<>();
        try (ProxyConnection connection = connectionPool.takeConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS);
            while (resultSet.next()) {
                User user = new User();
                user.setUsLogin(resultSet.getString("us_login"));
                user.setUsPassword(resultSet.getString("us_password"));//for what?
                user.setUsEmail(resultSet.getString("us_email"));
                user.setUsName(resultSet.getString("us_name"));
                user.setUsSurname(resultSet.getString("us_surname"));
                user.setUsPassport(resultSet.getString("us_passport"));
                user.setUsRole(resultSet.getString("us_role"));
                user.setUsBan(resultSet.getBoolean("us_ban"));
               // user.setTariffId(resultSet.getInt("t_id"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception from UserDAO:", e);
        }
        return users;
    }

    @Override
    public void delete(String login) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_USER);
            statement.setString(1, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception from UserDAO: ", e);
        }
    }

        @Override
    public void update(User user) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection()) {
              PreparedStatement statement = connection.prepareStatement(UPDATE_USER);
                statement.setString(1, user.getUsLogin());
                statement.setString(2, user.getUsPassword());
                statement.setString(3, user.getUsEmail());
                statement.setString(4, user.getUsName());
                statement.setString(5, user.getUsSurname());
                statement.setString(6, user.getUsPassport());
                statement.setString(7, user.getUsRole());
                statement.setBoolean(8, user.isUsBan());
                statement.setString(9, user.getUsLogin());//login is unique???
                statement.executeUpdate();
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Exception from UserDAO: ", e);
        }
    }

    public String findPasswordByLogin(String usLogin) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_PASSWORD_BY_LOGIN)) {
            statement.setString(1, usLogin);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            } else {
                return "";
            }
        } catch (SQLException e) {
            throw new DAOException("Exception from UserDAO: ", e);
        }
    }

    public User createUserBean(String usLogin) throws DAOException {
        User user = new User();
        try (ProxyConnection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_USER_INFO)) {
            statement.setString(1, usLogin);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            user.setUsLogin(resultSet.getString("us_login"));
            user.setUsPassword(resultSet.getString("us_password"));
            user.setUsEmail(resultSet.getString("us_email"));
            user.setUsName(resultSet.getString("us_name"));
            user.setUsSurname(resultSet.getString("us_surname"));
            user.setUsPassport(resultSet.getString("us_passport"));
            user.setUsRole(resultSet.getString("us_role"));
            user.setUsBan(resultSet.getBoolean("us_ban"));
            //tariff
        } catch (SQLException e) {
            throw new DAOException("Exception from UserDAO: ", e);
        }
        return user;
    }
}
