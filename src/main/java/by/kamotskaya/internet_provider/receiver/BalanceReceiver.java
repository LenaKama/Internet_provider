package by.kamotskaya.internet_provider.receiver;

import by.kamotskaya.internet_provider.command.CommandResult;
import by.kamotskaya.internet_provider.constant.PagePath;
import by.kamotskaya.internet_provider.constant.ParamName;
import by.kamotskaya.internet_provider.controller.RequestContent;
import by.kamotskaya.internet_provider.dao.impl.TransactionDAO;
import by.kamotskaya.internet_provider.entity.Transaction;
import by.kamotskaya.internet_provider.exception.ConnectionPoolException;
import by.kamotskaya.internet_provider.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Lena Kamotskaya
 */
public class BalanceReceiver {

    private static final Logger LOGGER = LogManager.getLogger(BalanceReceiver.class);

    public static CommandResult rechargeAccount(RequestContent content) {

        Double amount = Double.parseDouble(content.getRequestParameters().get("amount")[0]);
       String usLogin = String.valueOf(RequestContent.getSessionAttributes().get("usLogin"));
       // String usLogin = content.getRequestParameters().get(ParamName.US_LOGIN)[0];
        LOGGER.log(Level.DEBUG, "amount - " + amount);
        LOGGER.log(Level.DEBUG, "usLogin - " + usLogin);
        Transaction transaction = new Transaction();
        transaction.setTrInfo("Money inflow");
        transaction.setTrSum(amount);
        transaction.setUsLogin(usLogin);
        try {
            TransactionDAO transactionDAO = new TransactionDAO();
            transactionDAO.add(transaction);
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute("errorMessage", "Error while recharging your account");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return GoToPageReceiver.goToWelcomePage(content);
    }
}
