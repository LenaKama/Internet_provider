package by.kamotskaya.internet_provider.receiver;

import by.kamotskaya.internet_provider.command.CommandResult;
import by.kamotskaya.internet_provider.constant.PagePath;
import by.kamotskaya.internet_provider.constant.ParamName;
import by.kamotskaya.internet_provider.controller.RequestContent;
import by.kamotskaya.internet_provider.dao.TransactionDAO;
import by.kamotskaya.internet_provider.entity.Transaction;
import by.kamotskaya.internet_provider.exception.ConnectionPoolException;
import by.kamotskaya.internet_provider.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class for executing commands which relates to users' balances.
 *
 * @author Lena Kamotskaya
 */
public class BalanceReceiver {

    private static final Logger LOGGER = LogManager.getLogger(BalanceReceiver.class);

    public static CommandResult rechargeAccount(RequestContent content) {

        Double amount = Double.parseDouble(content.getRequestParameters().get("amount")[0]);
        String usLogin = String.valueOf(content.getSessionAttributes().get(ParamName.US_LOGIN));
        Transaction transaction = new Transaction();
        transaction.setTrInfo(ParamName.MONEY_INFLOW);
        transaction.setTrSum(amount);
        transaction.setUsLogin(usLogin);
        try {
            TransactionDAO transactionDAO = new TransactionDAO();
            transactionDAO.add(transaction);
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error while recharging your account");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return GoToPageReceiver.goToWelcomePage(content);
    }
}
