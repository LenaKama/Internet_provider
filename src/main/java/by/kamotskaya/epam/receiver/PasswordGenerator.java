package by.kamotskaya.epam.receiver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

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

    public static String  encryptPassword(String password) {
        try {
            String algorithm = "PBKDF2WithHmacSHA1";
            int derivedKeyLength = 160;
            int iterations = 20000;

            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt,
                    iterations, derivedKeyLength);

            SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);

            return String.valueOf(f.generateSecret(spec).getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
/*
* This should only happen when running this code on a new
* environment. It will never happen unpredictably and therefore is
* caught here so that utilizing code doesn't require a try/catch
* block.
*/
            e.printStackTrace();
        }
        return null;
    }

    public String decryptPassword(String encryptedPassword) {
        String decryptedPassword = null;
        return decryptedPassword;    }
}
