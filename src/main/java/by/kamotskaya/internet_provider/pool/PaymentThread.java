package by.kamotskaya.internet_provider.pool;

import by.kamotskaya.internet_provider.dao.impl.FeedbackDAO;
import by.kamotskaya.internet_provider.dao.impl.TransactionDAO;
import by.kamotskaya.internet_provider.entity.Feedback;
import by.kamotskaya.internet_provider.entity.Transaction;
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
            FeedbackDAO feedbackDAO = new FeedbackDAO();
            Feedback feedback = new Feedback();
            feedback.setfName("Katya");
            feedback.setfEmail("kat@gmail.com");
            feedback.setfMessage("Hi");
            feedbackDAO.add(feedback);
           /*
            TransactionDAO transactionDAO = new TransactionDAO();
            double balance = transactionDAO.findCurrentBalance("pablo");
            if (balance > 0) {
                Transaction transaction = new Transaction();
                transaction.setUsLogin("pablo");
                transaction.setTrSum(0.05);
                transaction.setTrDate(Date.valueOf("2018-05-20 12:34:09"));
                transactionDAO.add(transaction);
            }
            */
        } catch (ConnectionPoolException | DAOException e) {
            e.printStackTrace();
        }
    }
}
