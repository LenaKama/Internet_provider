package by.kamotskaya.internet_provider.receiver;

import by.kamotskaya.internet_provider.command.CommandResult;
import by.kamotskaya.internet_provider.constant.PagePath;
import by.kamotskaya.internet_provider.constant.ParamName;
import by.kamotskaya.internet_provider.controller.RequestContent;
import by.kamotskaya.internet_provider.dao.impl.*;
import by.kamotskaya.internet_provider.entity.OpeningBalance;
import by.kamotskaya.internet_provider.entity.Session;
import by.kamotskaya.internet_provider.entity.User;
import by.kamotskaya.internet_provider.exception.ConnectionPoolException;
import by.kamotskaya.internet_provider.exception.DAOException;
import by.kamotskaya.internet_provider.pool.PaymentThread;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.time.LocalDate;


/**
 * @author Lena Kamotskaya
 */
public class UserReceiver {

    private static final Logger LOGGER = LogManager.getLogger(UserReceiver.class);

    public static CommandResult authenticate(RequestContent content) {


        new Thread(new PaymentThread()).start();

        String usLogin = content.getRequestParameters().get("usLogin")[0];
        String usPassword = content.getRequestParameters().get("usPassword")[0];

        PasswordGenerator passwordGenerator = new PasswordGenerator();
LOGGER.log(Level.DEBUG, "in authenticate");
        Session session = new Session();
        session.setUsLogin(usLogin);
        try {
            UserDAO userDAO = new UserDAO();
            if (passwordGenerator.authenticate(usPassword, userDAO.findPasswordByLogin(usLogin))) {
                SessionDAO sessionDAO = new SessionDAO();
                User user = userDAO.createUserBean(usLogin);
                content.putSessionAttribute("user", user);
                RequestContent.getSessionAttributes().put(ParamName.US_LOGIN, usLogin);
                content.putSessionAttribute(ParamName.US_LOGIN, usLogin);
                content.putSessionAttribute(ParamName.US_ROLE, user.getUsRole());
                content = loadGeneralUserInfo(content);
                // RequestContent.getSessionAttributes().put(ParamName.US_ROLE, user.getUsRole());
                content.putSessionAttribute("currentSession", sessionDAO.startSession(session));
            } else {
                content.putRequestAttribute("message", "Wrong user credentials.");
            }
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error while authenticating the user.");
            content.putRequestAttribute(ParamName.ERROR_TRACE, e);
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.WELCOME);
    }

    public static RequestContent loadGeneralUserInfo(RequestContent content) throws DAOException, ConnectionPoolException {
        TransactionDAO transactionDAO = new TransactionDAO();
        TariffDAO tariffDAO = new TariffDAO();
        String usLogin = String.valueOf(RequestContent.getSessionAttributes().get(ParamName.US_LOGIN));
        UserDAO userDAO = new UserDAO();
        User user = userDAO.createUserBean(usLogin);
        if (user.getTId() != 0) {
            content.putRequestAttribute("currentBalance", transactionDAO.findCurrentBalance(usLogin));
            content.putRequestAttribute("currentTariff", tariffDAO.findTariffById(user.getTId()));
        }
        return content;
    }

    public static CommandResult register(RequestContent content) {

        String usLogin = content.getRequestParameters().get("usLogin")[0];
        String usPassword = content.getRequestParameters().get("usPassword")[0];
        String usName = content.getRequestParameters().get("usName")[0];
        String usSurname = content.getRequestParameters().get("usSurname")[0];
        String usEmail = content.getRequestParameters().get("usEmail")[0];
        String usPassport = content.getRequestParameters().get("usPassport")[0];

        PasswordGenerator passwordGenerator = new PasswordGenerator();

///////////session
        User user = new User();
        user.setUsLogin(usLogin);
        user.setUsPassword(passwordGenerator.encryptPassword(usPassword));
        user.setUsName(usName);
        user.setUsSurname(usSurname);
        user.setUsEmail(usEmail);
        user.setUsPassport(usPassport);
        user.setUsRole("client");
        user.setUsBan(false);
//////// при регистрации создавать пустые поля в таблицах openning balance and transactions sessions
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
            content.putRequestAttribute("errorMessage", "Error while adding a new user.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.WELCOME);
    }

    public static CommandResult checkLogin(RequestContent content) {
        content.putSessionAttribute("loginExists", "true");
        return null;
    }


    public static CommandResult updateUser(RequestContent content) {
        String usLogin = String.valueOf(RequestContent.getSessionAttributes().get(ParamName.US_LOGIN));
        try {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.createUserBean(usLogin);
            user.setUsEmail(content.getRequestParameters().get("usEmail")[0]);
            user.setUsName(content.getRequestParameters().get("usName")[0]);
            user.setUsSurname(content.getRequestParameters().get("usSurname")[0]);
            user.setUsPassport(content.getRequestParameters().get("usPassport")[0]);

            // String usLogin = content.getRequestParameters().get("usLogin")[0];
            // String usPassword = content.getRequestParameters().get("usPassword")[0];

            userDAO.update(user);
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error while deleting the user.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.WELCOME);
    }

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

    public static CommandResult showUserList(RequestContent content) {
        try {
            UserDAO userDAO = new UserDAO();
            content.putRequestAttribute("userList", userDAO.findAllClients());
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute("errorMessage", "Error while loading list of users");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.WELCOME);
    }

    public static CommandResult changeTariff(RequestContent content) {
        int tId = Integer.parseInt(content.getRequestParameters().get("tId")[0]);
        LOGGER.log(Level.DEBUG, "tId -" + tId);
        String usLogin = String.valueOf(RequestContent.getSessionAttributes().get(ParamName.US_LOGIN));
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
        return GoToPageReceiver.goToWelcomePage(content);
    }

    public static CommandResult logOut(RequestContent content) {
        content.putSessionAttribute("usRole", "quest");
        Session currentSession = (Session) RequestContent.getSessionAttributes().get("currentSession");
        try {
            SessionDAO sessionDAO = new SessionDAO();
            sessionDAO.endSession(currentSession);
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error while ending the session.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.REDIRECT, PagePath.WELCOME);
    }

}
