package by.kamotskaya.epam.dao.impl;

import by.kamotskaya.epam.constant.SqlQuery;
import by.kamotskaya.epam.entity.Entity;
import by.kamotskaya.epam.entity.User;
import by.kamotskaya.epam.exception.DAOException;
import by.kamotskaya.epam.dao.BaseDAO;
import by.kamotskaya.epam.pool.ConnectionPool;
import by.kamotskaya.epam.pool.ProxyConnection;
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

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    public UserDAO() {
    }

    @Override
    public List<Object> add(User user) throws DAOException {
        List<Object> resultList = new ArrayList<>();
        try (ProxyConnection connection = connectionPool.takeConnection()) {

            PreparedStatement statement = connection.prepareStatement(SqlQuery.REGISTER_CLIENT);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getName());
            statement.setString(5, user.getSurname());
            statement.setString(6, user.getPassport());
            statement.setString(7, "client");
            statement.setInt(8, 1);

            statement.executeUpdate();
            resultList.add("user " + user.getLogin() + " is inserted.");

        } catch (SQLException e) {
            throw new DAOException("Exception from UserRepository:", e);
        }

        //закрывать statement!!! 9 jdk
        return resultList;
    }

    @Override
    public void delete(Entity entity) {

    }

    @Override
    public void update(String us_login) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection()) {
            PreparedStatement statement = connection.prepareStatement(SqlQuery.REGISTER_CLIENT);

        } catch (SQLException e) {
            throw new DAOException("Exception from UserRepository:", e);
        }
    }

    public void find(Entity entity) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection()) {


        } catch (SQLException e) {
            throw new DAOException("Exception from UserRepository: ", e);
        }

    }

    public boolean findUserByLogin(User user) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SqlQuery.FIND_BY_LOGIN);

            while (resultSet.next()) {
                if (resultSet.getString("us_login").equals(user.getLogin())
                        && resultSet.getString("us_password").equals(user.getPassword())) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Exception from UserDAO: ", e);
        }
        return false;
    }
}
