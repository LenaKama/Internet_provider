package by.kamotskaya.epam.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Lena Kamotskaya
 */
public class DBConnector {

    private static Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/internet-provider";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    public static Connection createConnection() {

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Printing connection object " + connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static Connection getConnection() {
        if (connection == null) {
            createConnection();
        }
        return connection;
    }
}
