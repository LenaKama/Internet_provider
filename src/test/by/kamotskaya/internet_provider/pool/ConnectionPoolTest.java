package by.kamotskaya.internet_provider.pool;

import by.kamotskaya.internet_provider.exception.ConnectionPoolException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * @author Lena Kamotskaya
 */
public class ConnectionPoolTest {

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @BeforeMethod
    private void initializeConnectionPool() throws ConnectionPoolException {
        connectionPool.initializeConnectionPool();
    }

    // @AfterMethod
    private void destroyConnectionPool() throws ConnectionPoolException {
        connectionPool.destroyConnectionPool();
    }

    @Test
    public void getInstancePositive() {
        ConnectionPool poolInstance = ConnectionPool.getInstance();
        Assert.assertEquals(poolInstance.getClass(), ConnectionPool.class);
    }

    @Test
    public void takeConnection() throws SQLException {
        Connection connection = connectionPool.takeConnection();
        Assert.assertTrue(connection instanceof ProxyConnection);
        Assert.assertFalse(connection.isClosed());

    }

    @Test
    public void testFreeConnections() throws ConnectionPoolException {
        connectionPool.takeConnection();
        Assert.assertEquals(ConnectionPool.getFreeConnectionsSize(), 49);
    }

    @Test
    public void testBusyConnections() throws ConnectionPoolException {
        connectionPool.takeConnection();
        Assert.assertEquals(ConnectionPool.getBusyConnectionsSize(), 1);
    }

    @Test
    public void releaseConnectionPositive() throws SQLException, ConnectionPoolException {
        ProxyConnection connection1 = ConnectionPool.getInstance().takeConnection();
        ConnectionPool.releaseConnection(connection1);
        Assert.assertFalse(connection1.isClosed());
        Assert.assertEquals(ConnectionPool.getBusyConnectionsSize(), 0);
        connectionPool.destroyConnectionPool();
        Assert.assertTrue(connection1.isClosed());
    }

    @Test(expectedExceptions = SQLException.class)
    public void closeClosedConnection() throws SQLException, ConnectionPoolException {
        ProxyConnection connection = connectionPool.takeConnection();
        ConnectionPool.releaseConnection(connection);
        ConnectionPool.getInstance().destroyConnectionPool();
        connection.closeConnection();
    }
}

