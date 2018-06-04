package by.kamotskaya.internet_provider.receiver;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class for encrypting users' passwords before inserting in database.
 *
 * @author Lena Kamotskaya
 */
public class PasswordEncryptor {

    public PasswordEncryptor() {
    }

    /**
     * Checks if inputting password and password in database are equal using the same encrypting algorithm.
     *
     * @param attemptedPassword - password from input field
     * @param encryptedPassword - password from database
     * @return
     */
    public boolean authenticate(String attemptedPassword, String encryptedPassword) {
        String encryptedAttemptedPassword = encryptPassword(attemptedPassword);
        return encryptedPassword.equals(encryptedAttemptedPassword);
    }

    /**
     * @param password - password from input field
     * @return
     */
    public String encryptPassword(String password) {
        String generatedPassword = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
