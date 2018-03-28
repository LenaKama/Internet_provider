package by.kamotskaya.epam.factory;

import by.kamotskaya.epam.command.CommandType;
import by.kamotskaya.epam.command.JspCommand;
import by.kamotskaya.epam.content.RequestContent;

/**
 * @author Lena Kamotskaya
 */
public class CommandFactory {

    private static CommandFactory instance = new CommandFactory();

    private CommandFactory() {
    }

    public static CommandFactory getInstance() {
        return instance;
    }

    public JspCommand getCommand(RequestContent content) {
        String commandName = content.getRequestParameters().get("command")[0];
        CommandType type = CommandType.valueOf(commandName.toUpperCase());
        return type.getCommand();
    }
}
