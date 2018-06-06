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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Class-thread for executing clients' everyday daily fees.
 *
 * @author Lena Kamotskaya
 */
public class PaymentThread extends Thread {

    private static final Logger LOGGER = LogManager.getLogger(PaymentThread.class);

    @Override
    public void run() {
        try {
            TransactionDAO transactionDAO = new TransactionDAO();
            TariffDAO tariffDAO = new TariffDAO();
            SessionDAO sessionDAO = new SessionDAO();
            UserDAO userDAO = new UserDAO();
            for (String usLogin : userDAO.findAllUsLogins()) {
                if (!transactionDAO.checkTransaction(usLogin)) {
                    User user = userDAO.createUserBean(usLogin);
                    int tId = user.getTId();
                    if (tId != 0) {
                        Tariff tariff = tariffDAO.findTariffById(tId);
                        Transaction transaction = new Transaction();
                        transaction.setUsLogin(usLogin);
                        transaction.setTrInfo(ParamName.PAYMENT_INFO);
                        int totalTraffic = sessionDAO.findTrafficInStatus(usLogin) + sessionDAO.findTrafficOutStatus(usLogin);
                        if(tariff.getTrafficLimit()==0) {
                            transaction.setTrSum(tariff.getDailyFee());
                        } else if ( totalTraffic <= tariff.getTrafficLimit() || tariff.getTrafficLimit() == 0) {
                                transaction.setTrSum(tariff.getDailyFee());
                            } else {
                                transaction.setTrSum(tariff.getOverrunFee());
                            }
                        transactionDAO.add(transaction);
                    }
                }
            }
        } catch (ConnectionPoolException | DAOException e) {
            LOGGER.log(Level.ERROR, "Error while executing daily fee.");
        }
    }
}

