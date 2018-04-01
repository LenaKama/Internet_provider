package by.kamotskaya.epam.command;

import by.kamotskaya.epam.receiver.BaseReceiver;

/**
 * @author Lena Kamotskaya
 */
public enum CommandType {
    LANGUAGE(BaseReceiver::selectLanguage),
    AUTHORIZATION(BaseReceiver::authorize);

    private FunctionalCommand command;

    CommandType(FunctionalCommand command) {
        this.command = command;
    }

    public FunctionalCommand getCommand() {
        return command;
    }
}
