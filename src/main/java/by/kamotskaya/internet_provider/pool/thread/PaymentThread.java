package by.kamotskaya.internet_provider.pool.thread;

import by.kamotskaya.internet_provider.constant.ParamName;
import by.kamotskaya.internet_provider.dao.SessionDAO;
import by.kamotskaya.internet_provider.dao.TariffDAO;
import by.kamotskaya.internet_provider.dao.TransactionDAO;
import by.kamotskaya.internet_provider.dao.UserDAO;
import by.kamotskaya.internet_provider.entity.Tariff;
import by.kamotskaya.internet_provider.entity.Transaction;
import by.kamotskaya.internet_provider.entity.User;
import by.kamotskaya.internet_provider.exception.ConnectionPoolException;
import by.kamotskaya.internet_provider.exception.DAOException;

import java.sql.Date;

/**
 * Created by Администратор on 28.05.2018.
 */
public class PaymentThread implements Runnable {
    @Override
    public void run() {
        try {
            TransactionDAO transactionDAO = new TransactionDAO();
            TariffDAO tariffDAO = new TariffDAO();
            SessionDAO sessionDAO = new SessionDAO();
            UserDAO userDAO = new UserDAO();
            for (String usLogin : userDAO.findAllUsLogins()) { //go through all users
                if (!transactionDAO.checkTransaction(usLogin)) {//check if there is daily fee
                    User user = userDAO.createUserBean(usLogin);
                    int tId = user.getTId();
                    if (tId != 0) {//if tariff is set
                        Tariff tariff = tariffDAO.findTariffById(tId);
                        Transaction transaction = new Transaction();
                        transaction.setUsLogin(usLogin);
                        transaction.setTrInfo(ParamName.PAYMENT_INFO);
                        if (sessionDAO.findTrafficInStatus(usLogin) + sessionDAO.findTrafficOutStatus(usLogin) <= tariff.getTrafficLimit()) {
                            transaction.setTrSum(tariff.getDailyFee());
                        } else {
                            transaction.setTrSum(tariff.getOverrunFee());
                        }
                        transactionDAO.add(transaction);
                    } else {
                        System.out.println("no tariff");
                    }
                } else {
                    System.out.println("there is a transaction");
                }
                System.out.println("----" + usLogin);
            }
        } catch (ConnectionPoolException | DAOException e) {
            e.printStackTrace();
        }
    }
}
