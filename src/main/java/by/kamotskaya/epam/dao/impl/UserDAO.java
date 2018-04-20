package by.kamotskaya.epam.dao.impl;

import by.kamotskaya.epam.entity.User;
import by.kamotskaya.epam.exception.DAOException;
import by.kamotskaya.epam.dao.BaseDAO;
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
public class UserDAO implements BaseDAO<User> {

    private static final Logger LOGGER = LogManager.getLogger(UserDAO.class);

    public final static String ADD_NEW_CLIENT = "INSERT INTO user(us_login, us_password, us_email, us_name, us_surname, us_passport, us_role, us_ban, t_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public final static String FIND_BY_LOGIN = "SELECT us_password FROM user WHERE us_login = ?"; // WHERE us_login = ?";
    public final static String UPDATE_USER = "ALTER TABLE user SET us_login = ? SET us_password WHERE us_login = ?";
    public final static String DELETE_USER = "ALTER TABLE user DELETE FROM user WHERE us_login = ?";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    public UserDAO() {
    }

    @Override
    public List<Object> add(User user) throws DAOException {
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
            statement.setBoolean(8, user.isUsBban());
            statement.setInt(9, 1);
            statement.executeUpdate();
            resultList.add("user " + user.getUsLogin() + " is inserted.");

        } catch (SQLException e) {
            throw new DAOException("Exception from UserDAO:", e);
        }
//map в которую запихивать все имена значения с формы решистрации, запускаем процесс валидации и если что-то не так выкидывать что неверно и отправляем клиенту checker будет
        //закрывать statement!!! 9 jdk
        return resultList;
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
    public void update(String usLogin) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER);
            statement.setString(1, usLogin);
            statement.setString(1, usLogin);
            statement.setString(1, usLogin);
            statement.setString(1, usLogin);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Exception from UserDAO: ", e);
        }
    }

    public boolean findUserByLogin (String login) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN);
            statement.setString(1, login);
            statement.execute();
        } catch (SQLException e) {
            throw new DAOException("Exception from UserDAO: ", e);
        }
    }
}
