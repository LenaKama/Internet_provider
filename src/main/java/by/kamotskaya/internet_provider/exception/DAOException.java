package by.kamotskaya.internet_provider.exception;

/**
 * Class for exceptions from DAO classes.
 *
 * @author Lena Kamotskaya
 */
public class DAOException extends Exception{

    public DAOException(String message) {
        super(message);
    }
    public DAOException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
