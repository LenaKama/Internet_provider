package by.kamotskaya.internet_provider.pool;

import by.kamotskaya.internet_provider.dao.impl.TransactionDAO;
import by.kamotskaya.internet_provider.dao.impl.UserDAO;
import by.kamotskaya.internet_provider.entity.User;
import by.kamotskaya.internet_provider.exception.ConnectionPoolException;
import by.kamotskaya.internet_provider.exception.DAOException;

import java.util.concurrent.TimeUnit;

/**
 * @author Lena Kamotskaya
 */
public class BalanceCheckerThread implements Runnable {
    @Override
    public void run() {
        try {
            UserDAO userDAO = new UserDAO();
            TransactionDAO transactionDAO = new TransactionDAO();
            //while (true) {
                System.out.println("in while");
                for (String usLogin : userDAO.findAllUsLogins()) {
                    User user = userDAO.createUserBean(usLogin);
                    if (transactionDAO.findCurrentBalance(usLogin) < 0) {
                        user.setUsBan(true);

                    } else {
                        user.setUsBan(false);
                        System.out.println("settin false");
                    }
                    userDAO.update(user);
                }
                TimeUnit.MINUTES.sleep(1);
          //  }
        } catch (ConnectionPoolException | DAOException e) {
            ///
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
