package by.kamotskaya.internet_provider.receiver;

import by.kamotskaya.internet_provider.command.CommandResult;
import by.kamotskaya.internet_provider.constant.PagePath;
import by.kamotskaya.internet_provider.constant.ParamName;
import by.kamotskaya.internet_provider.controller.RequestContent;
import by.kamotskaya.internet_provider.dao.impl.*;
import by.kamotskaya.internet_provider.entity.User;
import by.kamotskaya.internet_provider.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;


/**
 * @author Lena Kamotskaya
 */
public class UserReceiver {

    private static final Logger LOGGER = LogManager.getLogger(UserReceiver.class);

    private static UserDAO userDAO = new UserDAO();

    public UserReceiver() {
    }

    public static CommandResult authenticate(RequestContent content) {

        String usLogin = content.getRequestParameters().get("usLogin")[0];
        String usPassword = content.getRequestParameters().get("usPassword")[0];

        PasswordGenerator passwordGenerator = new PasswordGenerator();

        LOGGER.log(Level.DEBUG, usPassword + " - usPassword");

        TransactionDAO transactionDAO = new TransactionDAO();
        TariffDAO tariffDAO = new TariffDAO();
        try {
            if (passwordGenerator.authenticate(usPassword, userDAO.findPasswordByLogin(usLogin))) {
                LOGGER.log(Level.DEBUG, userDAO.findPasswordByLogin(usLogin) + " - from userDAO");

                User user = userDAO.createUserBean(usLogin);
                content.putSessionAttribute("user", user);
                RequestContent.getSessionAttributes().put("usLogin", usLogin);
                content.putSessionAttribute("usRole", user.getUsRole());
                loadGeneralUserInfo(content);
            } else {
                content.putRequestAttribute("message", "Wrong user credentials.");
            }
        } catch (DAOException e) {
            content.putRequestAttribute("errorMessage", "Error while authenticating the user.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.WELCOME);
    }

    public static void loadGeneralUserInfo(RequestContent content) throws DAOException {
        TransactionDAO transactionDAO = new TransactionDAO();
        TariffDAO tariffDAO = new TariffDAO();
        String usLogin = content.getRequestParameters().get("usLogin")[0];
        User user = userDAO.createUserBean(usLogin);
        if (user.getUsRole().equals("client")) {
        }
        if (user.getTId() != 0) {
            content.putRequestAttribute("currentBalance", transactionDAO.findCurrentBalance(usLogin));
            content.putRequestAttribute("currentTariff", tariffDAO.findTariffById(user.getTId()));
        }
    }

    public static CommandResult register(RequestContent content) {

        String usLogin = content.getRequestParameters().get("usLogin")[0];
        String usPassword = content.getRequestParameters().get("usPassword")[0];
        String usName = content.getRequestParameters().get("usName")[0];
        String usSurname = content.getRequestParameters().get("usSurname")[0];
        String usEmail = content.getRequestParameters().get("usEmail")[0];
        String usPassport = content.getRequestParameters().get("usPassport")[0];

        PasswordGenerator passwordGenerator = new PasswordGenerator();

        RequestContent.getSession();

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

    public static CommandResult showUserList(RequestContent content) {
        try {
            content.putRequestAttribute("userList", userDAO.findAllClients());
        } catch (DAOException e) {
            content.putRequestAttribute("errorMessage", "Error while loading list of users");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.WELCOME);
    }

    public static CommandResult changeTariff(RequestContent content) {
        int tId = Integer.parseInt(content.getRequestParameters().get("tId")[0]);
        LOGGER.log(Level.DEBUG, "tId -" + tId);
        UserDAO userDAO = new UserDAO();
        String usLogin = String.valueOf(RequestContent.getSessionAttributes().get(ParamName.US_LOGIN));
        try {
            User user = userDAO.createUserBean(usLogin);
            user.setTId(tId);
            userDAO.update(user);
            content.putSessionAttribute("user", user);
        } catch (DAOException e) {
            content.putRequestAttribute("errorMessage", "Error while updating user");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.WELCOME);
    }

    public static CommandResult logOut(RequestContent content) {
        content.putSessionAttribute("usRole", "quest");
        RequestContent.getSession().invalidate();
        return new CommandResult(CommandResult.ResponseType.REDIRECT, PagePath.WELCOME);
    }
}
