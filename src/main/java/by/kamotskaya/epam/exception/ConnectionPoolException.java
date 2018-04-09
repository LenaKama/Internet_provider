package by.kamotskaya.epam.exception;

/**
 * @author Lena Kamotskaya
 */
public class ConnectionPoolException extends Exception{

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
