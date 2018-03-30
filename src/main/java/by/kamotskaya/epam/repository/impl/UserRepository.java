package by.kamotskaya.epam.repository.impl;

import by.kamotskaya.epam.entity.Entity;
import by.kamotskaya.epam.repository.BaseRepository;
import by.kamotskaya.epam.pool.ConnectionPool;
import by.kamotskaya.epam.pool.ProxyConnection;
import by.kamotskaya.epam.specification.BaseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Lena Kamotskaya
 */
public class UserRepository implements BaseRepository {

    private static final Logger LOGGER = LogManager.getLogger(UserRepository.class);

    private static ProxyConnection connection;
    private final static String SQL_REGISTER_CLIENT = "INSERT INTO user VALUES(?, ?, ?, ?, ?)";

    public UserRepository() {
        connection = ConnectionPool.getConnection();
    }

    @Override
    public void add(Entity entity) {
        try {
            Statement statement = connection.createStatement();

        } catch (SQLException e) {
            LOGGER.catching(e);
        }
    }

    @Override
    public void delete(Entity entity) {

    }

    @Override
    public void update(Entity entity) {

    }

    @Override
    public void query(BaseSpecification specification) {

    }
}
