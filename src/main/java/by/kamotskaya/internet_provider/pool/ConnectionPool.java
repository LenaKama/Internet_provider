package by.kamotskaya.internet_provider.pool;

import by.kamotskaya.internet_provider.exception.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private static final String PROPERTIES_FILE_NAME = "prop/db.properties";

    private static ConnectionPool instance;

    private static ReentrantLock instanceLock = new ReentrantLock();
    private static ReentrantLock connectionLock = new ReentrantLock();

    private static PropertiesReader propertiesReader = new PropertiesReader();

    private static AtomicBoolean instanceCreated = new AtomicBoolean();
    private static Queue<ProxyConnection> freeConnections;
    private static Queue<ProxyConnection> busyConnections;


    private ConnectionPool() {

    }

    public static ConnectionPool getInstance() throws ConnectionPoolException {
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

    private static void initializeConnectionPool() throws ConnectionPoolException {
        try {
            propertiesReader.read(PROPERTIES_FILE_NAME);
            freeConnections = new ArrayDeque<>(propertiesReader.getPoolSize());
            busyConnections = new ArrayDeque<>(propertiesReader.getPoolSize());

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            String url = propertiesReader.getUrl();
            String username = propertiesReader.getUsername();
            String password = propertiesReader.getPassword();
            for (int i = 0; i < propertiesReader.getPoolSize(); i++) {
                ProxyConnection connection = new ProxyConnection(DriverManager.getConnection(url, username, password));
                freeConnections.add(connection);
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException("Exception from ConnectionPool.", e);
        }
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

    public static void destroyConnectionPool() throws SQLException, ConnectionPoolException {
        //check if all connections return to the freeConnections
        //locking while polling from freeConnections
        for (int i = 0; i < propertiesReader.getPoolSize(); i++) {
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


    //clone
    public void createConnection() {
    }

}
