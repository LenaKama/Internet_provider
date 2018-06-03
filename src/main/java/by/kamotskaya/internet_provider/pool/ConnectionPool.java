package by.kamotskaya.internet_provider.pool;

import by.kamotskaya.internet_provider.constant.ParamName;
import by.kamotskaya.internet_provider.exception.ConnectionPoolException;
import by.kamotskaya.internet_provider.exception.DBResourceManagerException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Enumeration;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Lena Kamotskaya
 */
public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static final int DEFAULT_POOL_SIZE = 10;

    private static ConnectionPool instance;

    private static ReentrantLock instanceLock = new ReentrantLock();
    private static ReentrantLock connectionLock = new ReentrantLock();

    private static AtomicBoolean instanceCreated = new AtomicBoolean();
    private static Queue<ProxyConnection> freeConnections;
    private static Queue<ProxyConnection> busyConnections;

    private String url;
    private String username;
    private String password;
    private int poolSize;

    private ConnectionPool() {

    }

    public static ConnectionPool getInstance() throws ConnectionPoolException {
        if (!instanceCreated.get()) {
            instanceLock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                   // initializeConnectionPool();
                    instanceCreated.compareAndSet(false, true);
                }
            } finally {
                instanceLock.unlock();
            }
        }
        return instance;
    }

    public void initializeConnectionPool() throws ConnectionPoolException {

        DBResourceManager dbResourceManager = DBResourceManager.getInstance();

        try {

            this.url = dbResourceManager.getProperty(ParamName.DB_URL);
            this.username = dbResourceManager.getProperty(ParamName.DB_USERNAME);
            this.password = dbResourceManager.getProperty(ParamName.DB_PASSWORD);
            this.poolSize = Integer.parseInt(dbResourceManager.getProperty(ParamName.DB_POLL_SIZE));

        } catch (DBResourceManagerException e) {
            LOGGER.log(Level.ERROR, "Error in DataBase Resource Manager", e);
            throw new ConnectionPoolException("Error in DataBase Resource Manager.", e);

        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARN, "Wrong pool size value. Will be set to default value(10).");
            this.poolSize = DEFAULT_POOL_SIZE;
        }

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            freeConnections = new ArrayDeque<>(poolSize);
            busyConnections = new ArrayDeque<>(poolSize);

            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, username, password);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.add(proxyConnection);
            }

        } catch (SQLException e) {
            throw new ConnectionPoolException("SQLException", e);
			/*
			 * } catch (ClassNotFoundException e) { throw new
			 * ConnectionPoolException("Can't find database driver class.", e);
			 */
        }
        LOGGER.log(Level.INFO, "ConnectionPool is initialized.");
    }

    public ProxyConnection takeConnection() {
        connectionLock.lock();
        ProxyConnection connection = null;
        if (freeConnections.size() > 0) {
            connection = freeConnections.poll();
            busyConnections.add(connection);
        }
        connectionLock.unlock();
        return connection;
    }
/////////??????????
    static void releaseConnection(ProxyConnection connection) throws SQLException {
        if (!connection.getAutoCommit()) {
            connection.setAutoCommit(true);
        }
        freeConnections.add(busyConnections.poll());
    }

    public void destroyConnectionPool() throws SQLException, ConnectionPoolException {
        //check if all connections return to the freeConnections
        //locking while polling from freeConnections
        for (int i = 0; i < poolSize; i++) {
            freeConnections.poll().close();
        }
        try {
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException("Exception from ConnectionPool.", e);
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone the ConnectionPool");
    }

}
