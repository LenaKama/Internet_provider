package by.kamotskaya.epam.receiver;

import by.kamotskaya.epam.command.CommandResult;
import by.kamotskaya.epam.constant.PagePath;
import by.kamotskaya.epam.controller.RequestContent;
import by.kamotskaya.epam.dao.impl.UserDAO;
import by.kamotskaya.epam.entity.User;
import by.kamotskaya.epam.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

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
        User user = new User();

        user.setLogin(login);
        user.setPassword(password);

        try {
            if (userDAO.findUserByLogin(user)) {
                 content.putRequestAttribute("login", login);
                 content.putSessionAttribute("login", login);
                 content.putRequestAttribute("password", password);
                 content.putRequestAttribute("message", "Welcome " + login + "!");
             } else {
                 content.putRequestAttribute("message", "Wrong user credentials.");
                 return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.LOGIN);

             }
        } catch (DAOException e) {
            LOGGER.catching(e);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.HOME);
    }

    public static CommandResult goToRegistration(RequestContent content) {
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.REGISTRATION);
    }

        public static CommandResult register(RequestContent content) {

        List<Object> list = new ArrayList<>();

        String login = content.getRequestParameters().get("login")[0];
        String password = content.getRequestParameters().get("password")[0];
        String name = content.getRequestParameters().get("name")[0];
        String surname = content.getRequestParameters().get("surname")[0];
        String email = content.getRequestParameters().get("email")[0];
        String passport = content.getRequestParameters().get("passport")[0];

        User user = new User();

        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassport(passport);

        try {
            list = userDAO.add(user);
        } catch (DAOException e) {
            LOGGER.catching(e);
        }
        content.putRequestAttribute("message", (String) list.get(0));

        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.HOME);
    }
}
