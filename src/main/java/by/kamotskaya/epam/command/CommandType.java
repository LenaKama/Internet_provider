package by.kamotskaya.epam.command;

import by.kamotskaya.epam.receiver.BaseReceiver;
import by.kamotskaya.epam.receiver.UserReceiver;

/**
 * @author Lena Kamotskaya
 */
public enum CommandType {
    LANGUAGE(BaseReceiver::selectLanguage),
    AUTHENTICATION(UserReceiver::authenticate),
    GO_TO_REGISTRATION(UserReceiver::goToRegistration),
    REGISTRATION(UserReceiver::register);

    private FunctionalCommand command;

    CommandType(FunctionalCommand command) {
        this.command = command;
    }

    public FunctionalCommand getCommand() {
        return command;
    }
}
