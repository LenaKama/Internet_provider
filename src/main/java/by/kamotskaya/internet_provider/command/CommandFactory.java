package by.kamotskaya.internet_provider.command;

import by.kamotskaya.internet_provider.constant.ParamName;
import by.kamotskaya.internet_provider.controller.RequestContent;

/**
 * Class for
 * @author Lena Kamotskaya
 */
public class CommandFactory {

    private static CommandFactory INSTANCE = new CommandFactory();

    private CommandFactory() {
    }

    public static CommandFactory getINSTANCE() {
        return INSTANCE;
    }

    public FunctionalCommand getCommand(RequestContent content) {
        String commandName = content.getRequestParameters().get(ParamName.COMMAND)[0];
        CommandType type = CommandType.valueOf(commandName.toUpperCase());
        return type.getCommand();
    }
}
