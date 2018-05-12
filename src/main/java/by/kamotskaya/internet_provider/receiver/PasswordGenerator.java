package by.kamotskaya.internet_provider.receiver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author Lena Kamotskaya
 */
public class PasswordGenerator {

    private static final Logger LOGGER = LogManager.getLogger(PasswordGenerator.class);

    private static byte[] salt = generateSalt();

           private static byte[] generateSalt() {
               try {
                   SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

// Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
                   byte[] salt = new byte[8];
                   random.nextBytes(salt);

                   return salt;
               } catch (NoSuchAlgorithmException e) {
                   LOGGER.catching(e);
               }
               return null;
           }

    public boolean authenticate(String attemptedPassword, String encryptedPassword) {
// Encrypt the clear-text password using the same salt that was used to
// encrypt the original password
        String encryptedAttemptedPassword = encryptPassword(attemptedPassword);

// Authentication succeeds if encrypted password that the user entered
// is equal to the stored hash
        return encryptedPassword.equals(encryptedAttemptedPassword);
    }

    public String encryptPassword(String password) {
        String generatedPassword = "";
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public String decryptPassword(String encryptedPassword) {
        String decryptedPassword = null;
        return decryptedPassword;    }
}
