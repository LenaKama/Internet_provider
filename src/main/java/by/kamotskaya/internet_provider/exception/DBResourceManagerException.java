package by.kamotskaya.internet_provider.exception;

import java.sql.SQLException;

/**
 * @author Lena Kamotskaya
 */
public class DBResourceManagerException extends SQLException {

    private static final long serialVersionUID = -7324419689895500333L;

    public DBResourceManagerException() {
        super();
    }

    public DBResourceManagerException(String message) {
        super(message);
    }

    public DBResourceManagerException(Exception e) {
        super(e);
    }

    public DBResourceManagerException(String message, Exception e) {
        super(message, e);
    }
}
