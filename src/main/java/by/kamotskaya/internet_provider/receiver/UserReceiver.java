package by.kamotskaya.internet_provider.receiver;

import by.kamotskaya.internet_provider.command.CommandResult;
import by.kamotskaya.internet_provider.constant.PagePath;
import by.kamotskaya.internet_provider.constant.ParamName;
import by.kamotskaya.internet_provider.controller.RequestContent;
import by.kamotskaya.internet_provider.dao.*;
import by.kamotskaya.internet_provider.entity.OpeningBalance;
import by.kamotskaya.internet_provider.entity.Session;
import by.kamotskaya.internet_provider.entity.User;
import by.kamotskaya.internet_provider.exception.ConnectionPoolException;
import by.kamotskaya.internet_provider.exception.DAOException;
import by.kamotskaya.internet_provider.pool.thread.TrafficCounterThread;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.time.LocalDate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static by.kamotskaya.internet_provider.receiver.GoToPageReceiver.goToWelcomePage;


/**
 * 
 * @author Lena Kamotskaya
 */
public class UserReceiver {

    private static final Logger LOGGER = LogManager.getLogger(UserReceiver.class);

    public static CommandResult authenticate(RequestContent content) {


        //new Thread(new BalanceCheckerThread()).start();

//        new Thread(new OpeningBalanceController()).start();
//        new Thread(new PaymentThread()).start();

        String usLogin = content.getRequestParameters().get(ParamName.US_LOGIN)[0];
        String usPassword = content.getRequestParameters().get(ParamName.US_PASSWORD)[0];

        PasswordEncryptor passwordGenerator = new PasswordEncryptor();
        try {
            UserDAO userDAO = new UserDAO();
            if (passwordGenerator.authenticate(usPassword, userDAO.findPasswordByLogin(usLogin))) {
                User user = userDAO.createUserBean(usLogin);
                content.putSessionAttribute(ParamName.USER, user);
                content.putSessionAttribute(ParamName.US_LOGIN, usLogin);
                content.putSessionAttribute(ParamName.US_ROLE, user.getUsRole());
                loadGeneralUserInfo(content);
                startUserSession(content);
/*
                if (user.getTId() != 0) {
                    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
                    executorService.scheduleAtFixedRate(new TrafficCounterThread(), 5, 10, TimeUnit.SECONDS);
                }*/
            }else {
                content.putRequestAttribute(ParamName.WRONG_USER_CREDENTIALS, true);
            }
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error while authenticating the user.");
            content.putRequestAttribute(ParamName.ERROR_TRACE, e);
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.WELCOME);
    }

    static void loadGeneralUserInfo(RequestContent content) throws DAOException, ConnectionPoolException {
        TransactionDAO transactionDAO = new TransactionDAO();
        TariffDAO tariffDAO = new TariffDAO();
        String usLogin = String.valueOf(content.getSessionAttributes().get(ParamName.US_LOGIN));
        OpeningBalanceDAO openingBalanceDAO = new OpeningBalanceDAO();
        UserDAO userDAO = new UserDAO();
        User user = userDAO.createUserBean(usLogin);
        if (user.getTId() != 0) {
            content.putRequestAttribute(ParamName.CURRENT_BALANCE, transactionDAO.findCurrentBalance(usLogin, openingBalanceDAO.findOpeningBalance(usLogin, true).get()));
            content.putRequestAttribute(ParamName.CURRENT_TARIFF, tariffDAO.findTariffById(user.getTId()));
        }
    }

    private static void startUserSession(RequestContent content) throws ConnectionPoolException, DAOException {
        String usLogin = String.valueOf(content.getSessionAttributes().get(ParamName.US_LOGIN));
        SessionDAO sessionDAO = new SessionDAO();
        Session session = new Session();
        session.setUsLogin(usLogin);
        content.putSessionAttribute(ParamName.SESSION_ID, sessionDAO.startSession(session));
    }

    public static CommandResult register(RequestContent content) {

        String usLogin = content.getRequestParameters().get(ParamName.US_LOGIN)[0];
        String usPassword = content.getRequestParameters().get(ParamName.US_PASSWORD)[0];
        String usName = content.getRequestParameters().get(ParamName.US_NAME)[0];
        String usSurname = content.getRequestParameters().get(ParamName.US_SURNAME)[0];
        String usEmail = content.getRequestParameters().get(ParamName.US_EMAIL)[0];
        String usPassport = content.getRequestParameters().get(ParamName.US_PASSPORT)[0];

        PasswordEncryptor passwordGenerator = new PasswordEncryptor();

        User user = new User();
        user.setUsLogin(usLogin);
        user.setUsPassword(passwordGenerator.encryptPassword(usPassword));
        user.setUsName(usName);
        user.setUsSurname(usSurname);
        user.setUsEmail(usEmail);
        user.setUsPassport(usPassport);
        user.setUsRole("client");
        user.setUsBan(false);
        try {
            OpeningBalanceDAO openingBalanceDAO = new OpeningBalanceDAO();
            OpeningBalance openingBalance = new OpeningBalance();
            openingBalance.setObDate(Date.valueOf(LocalDate.now()));
            openingBalance.setObSum(0);
            openingBalance.setUsLogin(usLogin);

            UserDAO userDAO = new UserDAO();
            userDAO.add(user);
            openingBalanceDAO.add(openingBalance);
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error while adding a new user.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.WELCOME);
    }

    public static CommandResult updateUser(RequestContent content) {
        String usLogin = String.valueOf(content.getSessionAttributes().get(ParamName.US_LOGIN));
        try {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.createUserBean(usLogin);
            user.setUsEmail(content.getRequestParameters().get(ParamName.US_EMAIL)[0]);
            user.setUsName(content.getRequestParameters().get(ParamName.US_NAME)[0]);
            user.setUsSurname(content.getRequestParameters().get(ParamName.US_SURNAME)[0]);
            user.setUsPassport(content.getRequestParameters().get(ParamName.US_PASSPORT)[0]);
            userDAO.update(user);
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error while updating the user.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.WELCOME);
    }

    //change password

    public static CommandResult deleteUser(RequestContent content) {
        String login = content.getRequestParameters().get("loginForDelete")[0];
        try {
            UserDAO userDAO = new UserDAO();
            userDAO.delete(login);
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error while deleting the user.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.WELCOME);
    }

    public static CommandResult changeTariff(RequestContent content) {
        int tId = Integer.parseInt(content.getRequestParameters().get(ParamName.T_ID)[0]);
        String usLogin = String.valueOf(content.getRequestParameters().get(ParamName.US_LOGIN)[0]);
        try {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.createUserBean(usLogin);
            user.setTId(tId);
            userDAO.update(user);
            content.putSessionAttribute("user", user);
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error while updating user.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return goToWelcomePage(content);
    }

    public static CommandResult logOut(RequestContent content) {
        content.putSessionAttribute(ParamName.US_ROLE, ParamName.GUEST);
        int sessionId = (int) content.getSessionAttributes().get(ParamName.SESSION_ID);
        try {
            SessionDAO sessionDAO = new SessionDAO();
            sessionDAO.endSession(sessionId);
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error while ending the session.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.REDIRECT, PagePath.WELCOME);
    }

    public static CommandResult changeLocale(RequestContent content) {

        String locale = content.getRequestParameters().get("locale")[0];
        content.putSessionAttribute(ParamName.US_LOCALE, locale);
        content.putSessionAttribute(ParamName.ACTIVE_LOCALE, locale);
        return GoToPageReceiver.goToWelcomePage(content);
    }
}
