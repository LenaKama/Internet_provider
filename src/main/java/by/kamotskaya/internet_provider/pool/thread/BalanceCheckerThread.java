package by.kamotskaya.internet_provider.pool.thread;

import by.kamotskaya.internet_provider.dao.TransactionDAO;
import by.kamotskaya.internet_provider.dao.UserDAO;
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
                    System.out.println("current usLogin - " + usLogin);
                    User user = userDAO.createUserBean("login");
                    if (transactionDAO.findCurrentBalance("login", 50) < 0) {
                        System.out.println("settin true");
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
            System.out.println("exception");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
