package by.kamotskaya.epam.command;

import by.kamotskaya.epam.controller.RequestContent;

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

    public FunctionalCommand getCommand(RequestContent content) {
        String commandName = content.getRequestParameters().get("command")[0];
        CommandType type = CommandType.valueOf(commandName.toUpperCase());
        return type.getCommand();
    }
}