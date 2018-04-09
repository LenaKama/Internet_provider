package by.kamotskaya.epam.pool;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.sql.Driver;
import java.util.Properties;

/**
 * @author Lena Kamotskaya
 */
public class PropertiesReader {

    private static final Logger LOGGER = LogManager.getLogger(PropertiesReader.class);

    private FileInputStream inputStream;
    private Properties properties;

    private String driver;
    private String url;
    private String username;
    private String password;
    private int poolSize;

    public PropertiesReader() {
        properties = new Properties();

    }

    public void read(String fileName) {
        try {
            inputStream = new FileInputStream(getClass().getClassLoader().getResource(fileName).getFile());
            properties.load(inputStream);
            driver = properties.getProperty("db.driver");
            url = properties.getProperty("db.url");
            username = properties.getProperty("db.username");
            password = properties.getProperty("db.password");
            poolSize = Integer.parseInt(properties.getProperty("db.poolsize"));
        } catch (FileNotFoundException e) {
            LOGGER.catching(e);
        } catch (IOException e) {
            LOGGER.catching(e);
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }
}
