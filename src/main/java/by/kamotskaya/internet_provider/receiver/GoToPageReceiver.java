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
import by.kamotskaya.internet_provider.exception.ConnectionPoolException;
import by.kamotskaya.internet_provider.exception.DAOException;

import java.util.Collections;
import java.util.List;

/**
 * @author Lena Kamotskaya
 */
public class GoToPageReceiver {

    public static CommandResult goToWelcomePage(RequestContent content) {
        content.putRequestAttribute(ParamName.ACTIVE_CLASS, "sign_in");
        try {
            if (!RequestContent.getSessionAttributes().get(ParamName.US_ROLE).equals(ParamName.QUEST)) {
                content = UserReceiver.loadGeneralUserInfo(content);
            }
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute("errorMessage", "Error while loading user's information.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.WELCOME);
    }

    public static CommandResult goToTariffs(RequestContent content) {
         content.putRequestAttribute(ParamName.ACTIVE_CLASS, "tariffs");
        try {
            TariffDAO tariffDAO = new TariffDAO();
            content.putRequestAttribute("tariffList", tariffDAO.findAllTariffs());
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error while loading tariffs' list.");
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
        try {
            SessionDAO sessionDAO = new SessionDAO();
            content.putSessionAttribute("trafficInStatus", sessionDAO.findTrafficInStatus(usLogin));
            content.putRequestAttribute("trafficOutStatus", sessionDAO.findTrafficOutStatus(usLogin));
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error while loading traffic status.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.TRAFFIC_STATUS);
    }

    public static CommandResult goToGeneral(RequestContent content) {
        content.putSessionAttribute(ParamName.ACTIVE_MENU, "general");
        try {
            content = UserReceiver.loadGeneralUserInfo(content);
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute("errorMessage", "Error while loading user's information.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.WELCOME);
    }

    public static CommandResult goToMessages(RequestContent content) {
        content.putRequestAttribute(ParamName.ACTIVE_MENU, "messages");
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.MESSAGES);
    }

    public static CommandResult goToSessions(RequestContent content) {
        content.putRequestAttribute(ParamName.ACTIVE_MENU, "sessions");
        String usLogin = String.valueOf(RequestContent.getSessionAttributes().get(ParamName.US_LOGIN));
        try {
            SessionDAO sessionDAO = new SessionDAO();
            content.putRequestAttribute("sessionList", sessionDAO.createSessionList(usLogin));
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error while creating list of sessions.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.SESSIONS);
    }

    public static CommandResult goToTransactions(RequestContent content) {
        content.putRequestAttribute(ParamName.ACTIVE_MENU, "transactions");
        String usLogin = String.valueOf(RequestContent.getSessionAttributes().get(ParamName.US_LOGIN));
        try {
            TransactionDAO transactionDAO = new TransactionDAO();
            List transactionList = transactionDAO.createTransactionList(usLogin);
            Collections.reverse(transactionList);
            content.putRequestAttribute("transactionList", transactionList);
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error while creating transactions' list.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.TRANSACTIONS);
    }

    public static CommandResult goToAccountSettings(RequestContent content) {
        content.putRequestAttribute(ParamName.ACTIVE_MENU, "account_settings");
        String usLogin = String.valueOf(RequestContent.getSessionAttributes().get(ParamName.US_LOGIN));
        try {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.createUserBean(usLogin);
            content.putRequestAttribute("user", user);
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error when creating user's bean.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ACCOUNT_SETTINGS);
    }

    public static CommandResult goToClients(RequestContent content) {
        content.putRequestAttribute(ParamName.ACTIVE_MENU, "clients");
        try {
            UserDAO userDAO = new UserDAO();
            content.putRequestAttribute("clients", userDAO.findAllClients());
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error while creating clients' list.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.CLIENTS);
    }

    public static CommandResult goToAdmins(RequestContent content) {
        content.putRequestAttribute(ParamName.ACTIVE_MENU, "admins");
        try {
            UserDAO userDAO = new UserDAO();
            content.putRequestAttribute("admins", userDAO.findAllAdmins());
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error while loading a list of admins.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ADMINS);
    }
}

