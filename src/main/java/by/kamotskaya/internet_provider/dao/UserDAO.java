package by.kamotskaya.internet_provider.dao;

import by.kamotskaya.internet_provider.entity.User;
import by.kamotskaya.internet_provider.exception.ConnectionPoolException;
import by.kamotskaya.internet_provider.exception.DAOException;
import by.kamotskaya.internet_provider.pool.ConnectionPool;
import by.kamotskaya.internet_provider.pool.ProxyConnection;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class provides data access methods for operations with users.
 *
 * @author Lena Kamotskaya
 */
public class UserDAO {

    private static final Logger LOGGER = LogManager.getLogger(UserDAO.class);

    private final static String ADD_NEW_CLIENT = "INSERT INTO user(us_login, us_password, us_email, us_name, us_surname, us_passport, us_role, us_ban) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String FIND_PASSWORD_BY_LOGIN = "SELECT us_password FROM user WHERE us_login = ?";
    private final static String GET_USER_INFO = "SELECT * FROM user WHERE us_login = ?";
    private final static String SELECT_ALL_CLIENTS = "SELECT * FROM user WHERE us_role = 'client'";
    private final static String UPDATE_USER = "UPDATE user SET us_password = ?, us_email = ?, us_name = ?, us_surname = ?, us_passport = ?, us_role = ?, us_ban = ?, t_id = ? WHERE us_login = ?";
    private final static String DELETE_USER = "ALTER TABLE user DELETE FROM user WHERE us_login = ?";
    private final static String SELECT_ALL_US_LOGINS = "SELECT us_login FROM user";

    private final ConnectionPool connectionPool;

    public UserDAO() throws ConnectionPoolException {
        connectionPool = ConnectionPool.getInstance();

    }

    /**
     * Adds a user in application base.
     *
     * @param user {@link User} entity object
     * @throws DAOException
     */
    public void add(User user) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_NEW_CLIENT)) {
            statement.setString(1, user.getUsLogin());
            statement.setString(2, user.getUsPassword());
            statement.setString(3, user.getUsEmail());
            statement.setString(4, user.getUsName());
            statement.setString(5, user.getUsSurname());
            statement.setString(6, user.getUsPassport());
            statement.setString(7, user.getUsRole());
            statement.setBoolean(8, user.isUsBan());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception from UserDAO:", e);
        }
    }

    /**
     * Creates a list of admins from application base.
     *
     * @return {@link List} of {@link User} objects
     * @throws DAOException
     */
    public List<User> findAllAdmins() throws DAOException {
        List<User> admins = new ArrayList<>();
        try (ProxyConnection connection = connectionPool.takeConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_CLIENTS);
            while (resultSet.next()) {
                User admin = new User();
                admin.setUsLogin(resultSet.getString("us_login"));
                admin.setUsPassword(resultSet.getString("us_password"));//for what?
                admin.setUsEmail(resultSet.getString("us_email"));
                admin.setUsName(resultSet.getString("us_name"));
                admin.setUsSurname(resultSet.getString("us_surname"));
                admin.setUsPassport(resultSet.getString("us_passport"));
                admin.setUsRole(resultSet.getString("us_role"));
                admin.setUsBan(resultSet.getBoolean("us_ban"));
                admin.setTId(resultSet.getInt("t_id"));
                admins.add(admin);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception from UserDAO:", e);
        }
        return admins;
    }

    /**
     * Creates a list of clients from application base.
     *
     * @return {@link List} of {@link User} objects
     * @throws DAOException
     */
    public List<User> findAllClients() throws DAOException {
        List<User> clients = new ArrayList<>();
        try (ProxyConnection connection = connectionPool.takeConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_CLIENTS);
            while (resultSet.next()) {
                User client = new User();
                client.setUsLogin(resultSet.getString("us_login"));
                client.setUsPassword(resultSet.getString("us_password"));//for what?
                client.setUsEmail(resultSet.getString("us_email"));
                client.setUsName(resultSet.getString("us_name"));
                client.setUsSurname(resultSet.getString("us_surname"));
                client.setUsPassport(resultSet.getString("us_passport"));
                client.setUsRole(resultSet.getString("us_role"));
                client.setUsBan(resultSet.getBoolean("us_ban"));
                client.setTId(resultSet.getInt("t_id"));
                clients.add(client);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception from UserDAO:", e);
        }
        return clients;
    }

    /**
     * Deletes a user from application base.
     *
     * @param usLogin login of user
     * @throws DAOException
     */
    public void delete(String usLogin) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
            statement.setString(1, usLogin);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception from UserDAO: ", e);
        }
    }

    /**
     * Updates a user in application base.
     *
     * @param user {@link User} object
     * @throws DAOException
     */
    public void update(User user) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setString(1, user.getUsPassword());
            statement.setString(2, user.getUsEmail());
            statement.setString(3, user.getUsName());
            statement.setString(4, user.getUsSurname());
            statement.setString(5, user.getUsPassport());
            statement.setString(6, user.getUsRole());
            statement.setBoolean(7, user.isUsBan());
            statement.setInt(8, user.getTId());
            statement.setString(9, user.getUsLogin());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception from UserDAO: ", e);
        }
    }

    /**
     * Finds user's password.
     *
     * @param usLogin login of user
     * @return string of password
     * @throws DAOException
     */
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

    /**
     * Creates user bean from application base.
     *
     * @param usLogin login of user
     * @return {@link User} entity object
     * @throws DAOException
     */
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
            user.setTId(resultSet.getInt("t_id"));
        } catch (SQLException e) {
            throw new DAOException("Exception from UserDAO: ", e);
        }
        return user;
    }

    /**
     * Checks if there's such a login in application base.
     *
     * @param usLogin checking login
     * @return {@code true} if login exists
     * @throws DAOException
     */
    public boolean checkLogin(String usLogin) throws DAOException {
        try (ProxyConnection connection = connectionPool.takeConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_US_LOGINS);
            while (resultSet.next()) {
                if (resultSet.getString(1).equals(usLogin)) {
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Exception from UserDAO: ", e);
        }
        return true;
    }

    /**
     * Creates a list of users' logins.
     *
     * @return {@link List} of {@link String} users' logins
     */
    public List<String> findAllUsLogins() {
        List<String> usLogins = new ArrayList<>();
        try (ProxyConnection connection = connectionPool.takeConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_US_LOGINS);
            while (resultSet.next()) {
                usLogins.add(resultSet.getString("us_login"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while loading users' list.");
        }
        return usLogins;
    }
}