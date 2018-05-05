package by.kamotskaya.internet_provider.receiver;

import by.kamotskaya.internet_provider.command.CommandResult;
import by.kamotskaya.internet_provider.constant.PagePath;
import by.kamotskaya.internet_provider.controller.RequestContent;
import by.kamotskaya.internet_provider.dao.impl.OpeningBalanceDAO;
import by.kamotskaya.internet_provider.dao.impl.SessionDAO;
import by.kamotskaya.internet_provider.dao.impl.TransactionDAO;
import by.kamotskaya.internet_provider.dao.impl.UserDAO;
import by.kamotskaya.internet_provider.entity.User;
import by.kamotskaya.internet_provider.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * @author Lena Kamotskaya
 */
public class UserReceiver {

    private static final Logger LOGGER = LogManager.getLogger(UserReceiver.class);

    private static UserDAO userDAO = new UserDAO();

    public UserReceiver() {}

    public static CommandResult authenticate(RequestContent content) {

        String usLogin = content.getRequestParameters().get("usLogin")[0];
        String usPassword = content.getRequestParameters().get("usPassword")[0];

        PasswordGenerator passwordGenerator = new PasswordGenerator();

        LOGGER.log(Level.DEBUG, usPassword + " - usPassword");

        SessionDAO sessionDAO = new SessionDAO();
        TransactionDAO transactionDAO = new TransactionDAO();
        OpeningBalanceDAO openingBalanceDAO = new OpeningBalanceDAO();

        try {
            if (passwordGenerator.authenticate(usPassword,userDAO.findPasswordByLogin(usLogin))) {
               LOGGER.log(Level.DEBUG, userDAO.findPasswordByLogin(usLogin) + " - from userDAO");

               User user = userDAO.createUserBean(usLogin);
                content.putSessionAttribute("user", user);
                if(user.getUsRole().equals("admin")) {
                    content.putRequestAttribute("userList", userDAO.findAllUsers());
                }
                if(user.getUsRole().equals("client")) {}
                content.putSessionAttribute("currentBalance", openingBalanceDAO.getCurrentBalance(usLogin));
                content.putSessionAttribute("trafficInStatus", sessionDAO.findTrafficInStatus(usLogin));
                content.putSessionAttribute("trafficOutStatus", sessionDAO.findTrafficOutStatus(usLogin));
                content.putSessionAttribute("sessionList", sessionDAO.createSessionList(usLogin));
                content.putSessionAttribute("transactionList", transactionDAO.createTransactionList(usLogin));
                 content.putSessionAttribute("usLogin", usLogin);//${user.login}
                 content.putSessionAttribute("usRole", user.getUsRole());//${user.login}
                content.putRequestAttribute("message", "Welcome " + usLogin + "!");
             } else {
                 LOGGER.log(Level.DEBUG, userDAO.findPasswordByLogin(usLogin) + " - from userDAO");
                LOGGER.log(Level.DEBUG, passwordGenerator.encryptPassword(usPassword) + " - from passwordGenerator");
                content.putRequestAttribute("message", "Wrong user credentials.");
                 return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.SIGN_IN);

             }
        } catch (DAOException e) {
            LOGGER.catching(e);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.WELCOME);
    }

        public static CommandResult register(RequestContent content) {

        String usLogin = content.getRequestParameters().get("usLogin")[0];
        String usPassword = content.getRequestParameters().get("usPassword")[0];
        String usName = content.getRequestParameters().get("usName")[0];
        String usSurname = content.getRequestParameters().get("usSurname")[0];
        String usEmail = content.getRequestParameters().get("usEmail")[0];
        String usPassport = content.getRequestParameters().get("usPassport")[0];
        int tariffId = Integer.parseInt(content.getRequestParameters().get("tariff")[0]);

        PasswordGenerator passwordGenerator = new PasswordGenerator();

        User user = new User();
        user.setUsLogin(usLogin);
        user.setUsPassword(passwordGenerator.encryptPassword(usPassword));
        user.setUsName(usName);
        user.setUsSurname(usSurname);
        user.setUsEmail(usEmail);
        user.setUsPassport(usPassport);
        user.setUsRole("admin");
        user.setUsBan(false);
        user.setTariffId(1);

        LOGGER.log(Level.INFO, user.toString());
        try {
            userDAO.add(user);
        } catch (DAOException e) {
            content.putRequestAttribute("errorMessage", "Error while adding a new user.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.WELCOME);
    }

    public static CommandResult checkLogin(RequestContent content) {
        content.putSessionAttribute("loginExists", "true");
        return null;
    }

    public static CommandResult deleteUser(RequestContent content) {
        String login = content.getRequestParameters().get("loginForDelete")[0];
        try {
            userDAO.delete(login);
        } catch (DAOException e) {
            LOGGER.catching(e);
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.WELCOME);
    }

    public static CommandResult loadUserList(RequestContent content) {
        try {
        content.putRequestAttribute("userList", userDAO.findAllUsers());
    } catch (DAOException e) {
        content.putRequestAttribute("errorMessage", "Error while loading list of users");
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
    }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.WELCOME);
    }

}
