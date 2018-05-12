package by.kamotskaya.internet_provider.receiver;

import by.kamotskaya.internet_provider.command.CommandResult;
import by.kamotskaya.internet_provider.constant.PagePath;
import by.kamotskaya.internet_provider.dao.impl.UserDAO;
import by.kamotskaya.internet_provider.exception.DAOException;
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
        UserDAO userDAO = new UserDAO();
        String response = "";
        try {
            response = userDAO.checkLogin(usLogin) ? "true" : "false";
        } catch (DAOException e) {
            //
        }
        return response;
    }
}
