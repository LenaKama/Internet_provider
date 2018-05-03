package by.kamotskaya.internet_provider.exception;

/**
 * @author Lena Kamotskaya
 */
public class ReceiverException extends Exception {

    public ReceiverException(String message) {
        super(message);
    }
    public ReceiverException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
