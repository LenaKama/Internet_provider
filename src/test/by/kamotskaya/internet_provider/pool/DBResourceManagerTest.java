package by.kamotskaya.internet_provider.pool;

import by.kamotskaya.internet_provider.exception.DBResourceManagerException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Lena Kamotskaya
 */
public class DBResourceManagerTest {

    DBResourceManager dbResourceManager = DBResourceManager.getInstance();

    @DataProvider
    public Object[][] validProperties() {
        return new Object[][]{{"db.url", "jdbc:mysql://localhost:3306/internet_provider?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=true&serverSslCert=classpath:server.crt"},
                {"db.username", "root"}, {"db.password", "password"}, {"db.poolSize", "20"},};
    }

    @Test
    public void getInstance() throws DBResourceManagerException {
        Object dbResourceManagerTest = DBResourceManager.getInstance();
        Assert.assertEquals(dbResourceManagerTest.getClass(), DBResourceManager.class);
    }

    @Test(dataProvider = "validProperties")
    public void getPropertyPositive(String name, String value) throws DBResourceManagerException {
        Assert.assertEquals(dbResourceManager.getProperty(name), value);

    }

    @Test(expectedExceptions = DBResourceManagerException.class)
    public void getPropertyNegative() throws DBResourceManagerException {
        dbResourceManager.getProperty("Wrong property");
    }
}