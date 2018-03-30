package by.kamotskaya.epam.pool;

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

    private static final ConnectionPool instance = new ConnectionPool();

    private static ReentrantLock instanceLock;
    private static ReentrantLock connectionLock;
    private static final int POOL_SIZE = 10;

    private static AtomicBoolean instanceCreated;
    private static ProxyConnection connection;
    private static Queue<ProxyConnection> freeConnections = new ArrayDeque<>(POOL_SIZE);
    private static Queue<ProxyConnection> busyConnections = new ArrayDeque<>(POOL_SIZE);

    private PropertiesReader propertiesReader = new PropertiesReader();

    private ConnectionPool() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            String url = propertiesReader.getValue(URL);
            for (int i = 0; i < POOL_SIZE; i++) {
                connection = new ProxyConnection(DriverManager.getConnection(propertiesReader));
                freeConnections.add(connection);
            }
        } catch (SQLException e) {
            LOGGER.catching(e);
        }
    }

    public static ConnectionPool getInstance() {
        if (!instanceCreated.get()) {
            instanceLock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    initializeConnectionPool();
                    instanceCreated.compareAndSet(false, true);
                }
            } finally {
                instanceLock.unlock();
            }
        }
        return instance;
    }

    public static ProxyConnection getConnection() {
        connectionLock.lock();
        Connection connection = null;
        if (availableConnections.size() > 0) {
            connection = availableConnections.get(FIRST_CONNECTION);
            availableConnections.remove(FIRST_CONNECTION);
        }
        connectionLock.unlock();
        return connection;

        ProxyConnection connection = freeConnections.poll();
        busyConnections.add(connection);
        return connection;
    }

    public static void releaseConnection(ProxyConnection connection) throws SQLException {
        connection.setAutoCommit(true);
        freeConnections.add(busyConnections.poll());
    }

    public void destroyConnections() throws SQLException {
        //check if all connections return to the freeConnections
        //locking while polling from freeConnections
        for (int i = 0; i < POOL_SIZE; i++) {
            freeConnections.poll().close();
        }
        try {
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            }
        } catch (SQLException e) {
            LOGGER.catching(e);
        }
    }


    //clone
    public void createConnection() {
    }

}
