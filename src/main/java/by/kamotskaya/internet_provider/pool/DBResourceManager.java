package by.kamotskaya.internet_provider.pool;


import by.kamotskaya.internet_provider.exception.DBResourceManagerException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Provides retrieving database parameters from {@code ResourceBundle}
 *
 * @author Lena Kamotskaya
 */
public class DBResourceManager {

    private static final Logger LOGGER = LogManager.getLogger(DBResourceManager.class);

    private final static DBResourceManager INSTANCE = new DBResourceManager();
    private final static String BASENAME = "prop.db";
    private ResourceBundle bundle = ResourceBundle.getBundle(BASENAME);

    public static DBResourceManager getInstance() {
        return INSTANCE;
    }

    /**
     * Gets the resource property by its string key.
     *
     * @param key {@code String} string key of resource.
     * @return {@code String} property
     * @throws DBResourceManagerException the DataBase Resource Manager exception
     */
    public String getProperty(String key) throws DBResourceManagerException {

        try {
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            LOGGER.log(Level.ERROR, "DBResourceManager error at getProperty() for key:" + key);
            throw new DBResourceManagerException(
                    "DBResourceManager error at getProperty() for key:" + key);
        }
    }

}