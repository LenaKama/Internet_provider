package by.kamotskaya.internet_provider.pool.thread;

import by.kamotskaya.internet_provider.constant.ParamName;
import by.kamotskaya.internet_provider.dao.OpeningBalanceDAO;
import by.kamotskaya.internet_provider.dao.TransactionDAO;
import by.kamotskaya.internet_provider.dao.UserDAO;
import by.kamotskaya.internet_provider.entity.OpeningBalance;
import by.kamotskaya.internet_provider.exception.ConnectionPoolException;
import by.kamotskaya.internet_provider.exception.DAOException;

import java.time.LocalDate;

/**
 * @author Lena Kamotskaya
 */
public class OpeningBalanceController implements Runnable {
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
                for (String usLogin : userDAO.findAllUsLogins()) { //go through all users
                    if (openingBalanceDAO.findOpeningBalance(usLogin, true).isPresent()) { //check if already added opening balances
                        System.out.println("already added");
                    } else {
                        openingBalance.setUsLogin(usLogin);
                        openingBalance.setObSum(transactionDAO.findCurrentBalance(usLogin, openingBalanceDAO.findOpeningBalance(usLogin, false).get()));
                        openingBalanceDAO.add(openingBalance);
                    }
                }
            } catch (ConnectionPoolException | DAOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("not 1 day");
        }
    }
}
