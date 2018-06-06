package by.kamotskaya.internet_provider.receiver;

import by.kamotskaya.internet_provider.dao.OpeningBalanceDAO;
import by.kamotskaya.internet_provider.dao.TariffDAO;
import by.kamotskaya.internet_provider.dao.TransactionDAO;
import by.kamotskaya.internet_provider.dao.UserDAO;
import by.kamotskaya.internet_provider.entity.User;
import by.kamotskaya.internet_provider.exception.ConnectionPoolException;
import by.kamotskaya.internet_provider.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

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
            LOGGER.log(Level.ERROR, "DAOException from AjaxReceiver.");
        }
        return response;
    }

    public String changeTariff(String usLogin, int tId) {
        String response = null;
        try {
            TransactionDAO transactionDAO = new TransactionDAO();
            TariffDAO tariffDAO = new TariffDAO();
            OpeningBalanceDAO openingBalanceDAO = new OpeningBalanceDAO();
            Optional<Double> balance = openingBalanceDAO.findOpeningBalance(usLogin, true);
            UserDAO userDAO = new UserDAO();
            User user = userDAO.createUserBean(usLogin);
            if (transactionDAO.findCurrentBalance(usLogin, balance.get()) >= tariffDAO.findTariffById(tId).getConnectionPayment()) {
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