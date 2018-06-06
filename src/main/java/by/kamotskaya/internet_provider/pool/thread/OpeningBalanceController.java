package by.kamotskaya.internet_provider.pool.thread;

import by.kamotskaya.internet_provider.constant.ParamName;
import by.kamotskaya.internet_provider.dao.OpeningBalanceDAO;
import by.kamotskaya.internet_provider.dao.TransactionDAO;
import by.kamotskaya.internet_provider.dao.UserDAO;
import by.kamotskaya.internet_provider.entity.OpeningBalance;
import by.kamotskaya.internet_provider.exception.ConnectionPoolException;
import by.kamotskaya.internet_provider.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

/**
 * Class-thread for controlling opening balances.
 *
 * @author Lena Kamotskaya
 */
public class OpeningBalanceController extends Thread {

    private static final Logger LOGGER = LogManager.getLogger(OpeningBalanceController.class);

    @Override
    public void run() {
        LocalDate localDate = LocalDate.now();
        int day = localDate.getDayOfMonth();
        if (day == ParamName.FIRST_MONTH_DAY) {
            try {
                TransactionDAO transactionDAO = new TransactionDAO();
                OpeningBalanceDAO openingBalanceDAO = new OpeningBalanceDAO();
                OpeningBalance openingBalance = new OpeningBalance();
                UserDAO userDAO = new UserDAO();
                for (String usLogin : userDAO.findAllUsLogins()) {
                    if (!openingBalanceDAO.findOpeningBalance(usLogin, true).isPresent()) {
                        openingBalance.setUsLogin(usLogin);
                        openingBalance.setObSum(transactionDAO.findCurrentBalance(usLogin, openingBalanceDAO.findOpeningBalance(usLogin, false).get()));
                        openingBalanceDAO.add(openingBalance);
                    }
                }
            } catch (ConnectionPoolException | DAOException e) {
                LOGGER.log(Level.ERROR, "Error while controlling opening balances.");
            }
        }
    }
}
