package by.kamotskaya.internet_provider.receiver;

import by.kamotskaya.internet_provider.command.CommandResult;
import by.kamotskaya.internet_provider.constant.PagePath;
import by.kamotskaya.internet_provider.constant.ParamName;
import by.kamotskaya.internet_provider.controller.RequestContent;
import by.kamotskaya.internet_provider.dao.*;
import by.kamotskaya.internet_provider.entity.Feedback;
import by.kamotskaya.internet_provider.entity.User;
import by.kamotskaya.internet_provider.exception.ConnectionPoolException;
import by.kamotskaya.internet_provider.exception.DAOException;

import java.util.Collections;
import java.util.List;

/**
 * Class for executing commands which forwards to pages.
 *
 * @author Lena Kamotskaya
 */
public class GoToPageReceiver {

    public static CommandResult goToWelcomePage(RequestContent content) {
        content.putRequestAttribute(ParamName.ACTIVE_CLASS, "sign_in");
        try {
            if (!content.getSessionAttributes().get(ParamName.US_ROLE).equals(ParamName.GUEST)) {
                UserReceiver.loadGeneralUserInfo(content);
            }
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error while loading user's information.");
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

    public static CommandResult goToHelp(RequestContent content) {
        content.putRequestAttribute(ParamName.ACTIVE_CLASS, "help");
        try {
            FeedbackDAO feedbackDAO = new FeedbackDAO();
            List<Feedback> userFeedbacks = feedbackDAO.loadRepliedFeedbacks();
            content.putRequestAttribute(ParamName.USER_FEEDBACKS, userFeedbacks);
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error while loading user's feedbacks.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.HELP);
    }

    public static CommandResult goToAboutUs(RequestContent content) {
        content.putRequestAttribute(ParamName.ACTIVE_CLASS, "about_us");
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ABOUT_US);
    }

    public static CommandResult goToTrafficStatus(RequestContent content) {
        content.putSessionAttribute(ParamName.ACTIVE_MENU, "traffic_status");
        String usLogin = String.valueOf(content.getSessionAttributes().get(ParamName.US_LOGIN));
        User user = (User) content.getSessionAttributes().get(ParamName.USER);
        try {
            SessionDAO sessionDAO = new SessionDAO();
            TariffDAO tariffDAO = new TariffDAO();
            content.putRequestAttribute(ParamName.CURRENT_TARIFF, tariffDAO.findTariffById(user.getTId()));
            content.putSessionAttribute(ParamName.TRAFFIC_IN_STATUS, sessionDAO.findTrafficInStatus(usLogin));
            content.putRequestAttribute(ParamName.TRAFFIC_OUT_STATUS, sessionDAO.findTrafficOutStatus(usLogin));
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error while loading traffic status.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.TRAFFIC_STATUS);
    }

    public static CommandResult goToGeneral(RequestContent content) {
        content.putSessionAttribute(ParamName.ACTIVE_MENU, "general");
        try {
            UserReceiver.loadGeneralUserInfo(content);
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error while loading user's information.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.WELCOME);
    }

    public static CommandResult goToMessages(RequestContent content) {
        content.putRequestAttribute(ParamName.ACTIVE_MENU, "messages");
        try {
            FeedbackDAO feedbackDAO = new FeedbackDAO();
            List unrepliedFeedbacks = feedbackDAO.loadUnrepliedFeedbacks();
            content.putRequestAttribute(ParamName.UNREPLIED_FEEDBACKS, unrepliedFeedbacks);

        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error while loading user's information.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.MESSAGES);
    }

    public static CommandResult goToSessions(RequestContent content) {
        content.putRequestAttribute(ParamName.ACTIVE_MENU, "sessions");
        String usLogin = String.valueOf(content.getSessionAttributes().get(ParamName.US_LOGIN));
        try {
            SessionDAO sessionDAO = new SessionDAO();
            List sessionList = sessionDAO.createSessionList(usLogin);
            Collections.reverse(sessionList);
            content.putRequestAttribute("sessionList", sessionList);
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error while creating list of sessions.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.SESSIONS);
    }

    public static CommandResult goToTransactions(RequestContent content) {
        content.putRequestAttribute(ParamName.ACTIVE_MENU, "transactions");
        String usLogin = String.valueOf(content.getSessionAttributes().get(ParamName.US_LOGIN));
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
        String usLogin = String.valueOf(content.getSessionAttributes().get(ParamName.US_LOGIN));
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

