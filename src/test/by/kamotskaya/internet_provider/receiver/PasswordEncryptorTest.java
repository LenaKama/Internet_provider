package by.kamotskaya.internet_provider.receiver;

import org.testng.annotations.*;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Lena Kamotskaya
 */
public class PasswordEncryptorTest {

    private PasswordEncryptor passwordEncryptor;
    private String decryptedPassword = "123";
    private String encryptedPassword = "202cb962ac59075b964b07152d234b70";

    @BeforeClass
    public void init(){
        passwordEncryptor = new PasswordEncryptor();
    }

    @Test
    public void authenticate() throws Exception {
        Assert.assertTrue(passwordEncryptor.authenticate(decryptedPassword, encryptedPassword));
        }

    @Test
    public void encryptPassword() throws Exception {
        String password = "123";
        Assert.assertEquals(passwordEncryptor.encryptPassword(password), "202cb962ac59075b964b07152d234b70");
    }


}
