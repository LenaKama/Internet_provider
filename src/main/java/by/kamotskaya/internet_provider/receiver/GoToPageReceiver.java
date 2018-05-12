package by.kamotskaya.internet_provider.receiver;

import by.kamotskaya.internet_provider.command.CommandResult;
import by.kamotskaya.internet_provider.constant.PagePath;
import by.kamotskaya.internet_provider.constant.ParamName;
import by.kamotskaya.internet_provider.controller.RequestContent;
import by.kamotskaya.internet_provider.dao.impl.SessionDAO;
import by.kamotskaya.internet_provider.dao.impl.TariffDAO;
import by.kamotskaya.internet_provider.dao.impl.TransactionDAO;
import by.kamotskaya.internet_provider.dao.impl.UserDAO;
import by.kamotskaya.internet_provider.entity.User;
import by.kamotskaya.internet_provider.exception.DAOException;

/**
 * @author Lena Kamotskaya
 */
public class GoToPageReceiver {

    public static CommandResult goToWelcomePage(RequestContent content) {
        content.putRequestAttribute(ParamName.ACTIVE_CLASS, "sign_in");
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.WELCOME);
    }

    public static CommandResult goToTariffs(RequestContent content) {
        TariffDAO tariffDAO = new TariffDAO();
        content.putRequestAttribute(ParamName.ACTIVE_CLASS, "tariffs");
        try {
            content.putRequestAttribute("tariffList", tariffDAO.findAllTariffs());
        } catch (DAOException e) {
            //content.putRequestAttr
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.TARIFFS);
    }

    public static CommandResult goToNews(RequestContent content) {
        content.putRequestAttribute(ParamName.ACTIVE_CLASS, "news");
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.NEWS);
    }

    public static CommandResult goToAboutUs(RequestContent content) {
        content.putRequestAttribute(ParamName.ACTIVE_CLASS, "about_us");
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ABOUT_US);
    }

    public static CommandResult goToTrafficStatus(RequestContent content) {
        content.putSessionAttribute(ParamName.ACTIVE_MENU, "traffic_status");
        String usLogin = String.valueOf(RequestContent.getSessionAttributes().get(ParamName.US_LOGIN));
        SessionDAO sessionDAO = new SessionDAO();
        try {
            content.putSessionAttribute("trafficInStatus", sessionDAO.findTrafficInStatus(usLogin));
            content.putRequestAttribute("trafficOutStatus", sessionDAO.findTrafficOutStatus(usLogin));
        } catch (DAOException e) {
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.TRAFFIC_STATUS);
    }

    public static CommandResult goToGeneral(RequestContent content) {
        TransactionDAO transactionDAO = new TransactionDAO();
        content.putSessionAttribute(ParamName.ACTIVE_MENU, "general");
        String usLogin = String.valueOf(RequestContent.getSessionAttributes().get(ParamName.US_LOGIN));
        SessionDAO sessionDAO = new SessionDAO();
        try {
            content.putSessionAttribute("currentBalance", transactionDAO.findCurrentBalance(usLogin));
        } catch (DAOException e) {
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.CLIENT);

    }

    public static CommandResult goToMessages(RequestContent content) {
        content.putRequestAttribute(ParamName.ACTIVE_MENU, "messages");
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.MESSAGES);
    }

    public static CommandResult goToSessions(RequestContent content) {
        content.putRequestAttribute(ParamName.ACTIVE_MENU, "sessions");
        SessionDAO sessionDAO = new SessionDAO();
        String usLogin = String.valueOf(RequestContent.getSessionAttributes().get(ParamName.US_LOGIN));
        try {
            content.putRequestAttribute("sessionList", sessionDAO.createSessionList(usLogin));
        } catch (DAOException e) {
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.SESSIONS);
    }

    public static CommandResult goToTransactions(RequestContent content) {
        content.putRequestAttribute(ParamName.ACTIVE_MENU, "transactions");
        TransactionDAO transactionDAO = new TransactionDAO();
        String usLogin = String.valueOf(RequestContent.getSessionAttributes().get(ParamName.US_LOGIN));
        try {
            content.putRequestAttribute("transactionList", transactionDAO.createTransactionList(usLogin));
        } catch (DAOException e) {
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.TRANSACTIONS);
    }

    public static CommandResult goToAccountSettings(RequestContent content) {
        content.putRequestAttribute(ParamName.ACTIVE_MENU, "account_settings");
        String usLogin = String.valueOf(RequestContent.getSessionAttributes().get(ParamName.US_LOGIN));
        UserDAO userDAO = new UserDAO();
        try {
            User user = userDAO.createUserBean(usLogin);
            content.putRequestAttribute("user", user);
        } catch (DAOException e) {
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ACCOUNT_SETTINGS);
    }
}
