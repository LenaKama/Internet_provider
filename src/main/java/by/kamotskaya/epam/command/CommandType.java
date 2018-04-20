package by.kamotskaya.epam.command;

import by.kamotskaya.epam.receiver.UserReceiver;

/**
 * @author Lena Kamotskaya
 */
public enum CommandType {

    AUTHENTICATION(UserReceiver::authenticate),
    GO_TO_REGISTRATION(UserReceiver::goToRegistration),
    REGISTRATION(UserReceiver::register),
    DELETE_USER(UserReceiver::deleteUser);

    private FunctionalCommand command;

    CommandType(FunctionalCommand command) {
        this.command = command;
    }

    public FunctionalCommand getCommand() {
        return command;
    }
}
