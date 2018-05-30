package by.kamotskaya.internet_provider.receiver;

import by.kamotskaya.internet_provider.command.CommandResult;
import by.kamotskaya.internet_provider.constant.PagePath;
import by.kamotskaya.internet_provider.constant.ParamName;
import by.kamotskaya.internet_provider.controller.RequestContent;
import by.kamotskaya.internet_provider.dao.impl.TariffDAO;
import by.kamotskaya.internet_provider.dao.impl.TransactionDAO;
import by.kamotskaya.internet_provider.dao.impl.UserDAO;
import by.kamotskaya.internet_provider.entity.User;
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

    public String changeTariff(int tId) {
        String response = null;
        String usLogin = String.valueOf(RequestContent.getSessionAttributes().get(ParamName.US_LOGIN));
        User user = (User) RequestContent.getSessionAttributes().get(ParamName.USER);
        try {
            TransactionDAO transactionDAO = new TransactionDAO();
            TariffDAO tariffDAO = new TariffDAO();
            if (transactionDAO.findCurrentBalance(usLogin) >= tariffDAO.findTariffById(tId).getConnectionPayment()) {
                user.setTId(tId);
                response = "true";
            } else {
                response = "false";
            }
        } catch (DAOException | ConnectionPoolException e) {
            LOGGER.log(Level.ERROR, "Error ");
        }
        return response;
    }
}