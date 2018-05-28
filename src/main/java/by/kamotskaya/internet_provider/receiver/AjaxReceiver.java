package by.kamotskaya.internet_provider.receiver;

import by.kamotskaya.internet_provider.command.CommandResult;
import by.kamotskaya.internet_provider.constant.PagePath;
import by.kamotskaya.internet_provider.dao.impl.UserDAO;
import by.kamotskaya.internet_provider.exception.ConnectionPoolException;
import by.kamotskaya.internet_provider.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Lena Kamotskaya
 */
public class AjaxReceiver {

    private static final Logger LOGGER = LogManager.getLogger(AjaxReceiver.class);

    public AjaxReceiver() {
    }

    public String checkLogin(String usLogin) {
        UserDAO userDAO = null;
        try {
            userDAO = new UserDAO();
        } catch (ConnectionPoolException e) {
            LOGGER.log(Level.ERROR, "Exception from AjaxReceiver.");
        }
        String response = "";
        try {
            response = userDAO.checkLogin(usLogin) ? "true" : "false";
        } catch (DAOException e) {
            //
        }
        return response;
    }
}
