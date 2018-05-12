package by.kamotskaya.internet_provider.receiver;

import by.kamotskaya.internet_provider.command.CommandResult;
import by.kamotskaya.internet_provider.constant.PagePath;
import by.kamotskaya.internet_provider.constant.ParamName;
import by.kamotskaya.internet_provider.controller.RequestContent;
import by.kamotskaya.internet_provider.dao.impl.TransactionDAO;
import by.kamotskaya.internet_provider.entity.Transaction;
import by.kamotskaya.internet_provider.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Lena Kamotskaya
 */
public class BalanceReceiver {

    private static final Logger LOGGER = LogManager.getLogger(BalanceReceiver.class);

    private static TransactionDAO transactionDAO = new TransactionDAO();

    public BalanceReceiver() {
    }

    public static CommandResult rechargeAccount(RequestContent content) {

        Double amount = Double.parseDouble(content.getRequestParameters().get("amount")[0]);
        String usLogin = String.valueOf(RequestContent.getSessionAttributes().get("usLogin"));
        //.getRequestParameters().get("usLogin")[0];
        Transaction transaction = new Transaction();
        transaction.setTrInfo("Money inflow");
        transaction.setTrSum(amount);
        transaction.setUsLogin(usLogin);
        try {
            transactionDAO.add(transaction);
        } catch (DAOException e) {
            content.putRequestAttribute("errorMessage", "Error while recharging your account");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.WELCOME);
    }

    public static CommandResult findCurrentBalance(RequestContent content) {
        try {
            String usLogin = content.getRequestParameters().get(ParamName.US_LOGIN)[0];
            TransactionDAO transactionDAO = new TransactionDAO();
            content.putSessionAttribute("curBalance", transactionDAO.findCurrentBalance(usLogin));
        } catch (DAOException e) {
            content.putRequestAttribute("errorMessage", "Error while calcilating your balance.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.WELCOME);
    }
}
