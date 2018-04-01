package by.kamotskaya.epam.pool;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Lena Kamotskaya
 */
public class PropertiesReader {

    private FileInputStream inputStream;
    private Properties properties;
    private ClassLoader classLoader;

    private String url;
    private String username;
    private String password;

    public PropertiesReader() {
        properties = new Properties();
        classLoader = getClass().getClassLoader();
        try {
            inputStream = new FileInputStream(new File(classLoader.getResource("db.properties").getFile()));
            properties.load(inputStream);
            url = properties.getProperty("db.url");
            username = properties.getProperty("db.username");
            password = properties.getProperty("db.password");
        } catch (FileNotFoundException e1) {
            //LOGGER
        } catch (IOException e) {
            //LOGGER
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
}
