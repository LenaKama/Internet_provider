package by.kamotskaya.epam.receiver;

import by.kamotskaya.epam.command.CommandResult;
import by.kamotskaya.epam.constant.PagePath;
import by.kamotskaya.epam.controller.RequestContent;
import by.kamotskaya.epam.entity.User;
import by.kamotskaya.epam.pool.ConnectionPool;
import by.kamotskaya.epam.pool.ProxyConnection;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Lena Kamotskaya
 */
public class BaseReceiver {

    private static final Logger LOGGER = LogManager.getLogger(BaseReceiver.class);

    public BaseReceiver() {
    }

    public static CommandResult selectLanguage(RequestContent content) {

        String language;
        String country;
        String lang = content.getRequestParameters().get("Language")[0];///??????

        if ("Russian".equals(lang)) {
            language = "ru";
            country = "RU";
        } else {
            language = "en";
            country = "US";
        }
        content.getRequestParameters().put("value", new String[]{language + "_" + country});

        LOGGER.log(Level.INFO, "Got a language");

        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.HOME.getValue());
    }

    public static CommandResult authorize(RequestContent content) {

        String login = content.getRequestParameters().get("login")[0];
        String password = content.getRequestParameters().get("password")[0];
        User user = new User();

        user.setLogin(login);
        user.setPassword(password);

try {
    ProxyConnection connection = ConnectionPool.getConnection();
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("select us_login, us_password from user");
    String loginDB;
    String passwordDB;
    while (resultSet.next()) {
        loginDB = resultSet.getString("us_login");
        passwordDB = resultSet.getString("us_password");

        if (login.equals(loginDB) && password.equals(passwordDB)) {
            content.putRequestAttribute("login", login);
            content.putRequestAttribute("password", password);
            content.putRequestAttribute("message", "it's success.");
        } else {
            content.putRequestAttribute("message", "it's fail.");
        }
    }
} catch (SQLException e) {
    //LOGGER
}
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.HOME.getValue());
    }
}
