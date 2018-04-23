package by.kamotskaya.epam.receiver;

import by.kamotskaya.epam.command.CommandResult;
import by.kamotskaya.epam.constant.PagePath;
import by.kamotskaya.epam.controller.RequestContent;
import by.kamotskaya.epam.dao.impl.UserDAO;
import by.kamotskaya.epam.entity.User;
import by.kamotskaya.epam.exception.DAOException;
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

        String login = content.getRequestParameters().get("login")[0];
        String password = content.getRequestParameters().get("password")[0];

        PasswordGenerator passwordGenerator = new PasswordGenerator();

        try {
            if (passwordGenerator.authenticate(password,userDAO.findPasswordByLogin(login))) {
                User user = userDAO.createUserBean(login);
                content.putSessionAttribute("user", user);
                 content.putSessionAttribute("login", login);//${user.login}
                 content.putRequestAttribute("message", "Welcome " + login + "!");
             } else {
                 content.putRequestAttribute("message", "Wrong user credentials.");
                 return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.SIGN_IN);

             }
        } catch (DAOException e) {
            LOGGER.catching(e);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.WELCOME);
    }



        public static CommandResult register(RequestContent content) {

        String login = content.getRequestParameters().get("login")[0];
        String password = content.getRequestParameters().get("password")[0];
        String name = content.getRequestParameters().get("name")[0];
        String surname = content.getRequestParameters().get("surname")[0];
        String email = content.getRequestParameters().get("email")[0];
        String passport = content.getRequestParameters().get("passport")[0];

        User user = new User();
        user.setUsLogin(login);
        user.setUsPassword(password);
        user.setUsName(name);
        user.setUsSurname(surname);
        user.setUsEmail(email);
        user.setUsPassport(passport);
        user.setUsRole("client");
        user.setUsBan(false);
        //set Tariff

        LOGGER.log(Level.INFO, user.toString());
        try {
            userDAO.add(user);
        } catch (DAOException e) {
            LOGGER.catching(e);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.WELCOME);
    }

    public static CommandResult deleteUser(RequestContent content) {
        String login = content.getRequestParameters().get("loginForDelete")[0];
        try {
            userDAO.delete(login);
        } catch (DAOException e) {
            LOGGER.catching(e);
            // return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.WELCOME);
    }
}
