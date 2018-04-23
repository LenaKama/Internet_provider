package by.kamotskaya.epam.command;

import by.kamotskaya.epam.receiver.BaseReceiver;
import by.kamotskaya.epam.receiver.GoToPageReceiver;
import by.kamotskaya.epam.receiver.UserReceiver;

/**
 * @author Lena Kamotskaya
 */
public enum CommandType {

    CHANGE_LOCALE(BaseReceiver::changeLocale),
    SHOW_SIGN_IN(GoToPageReceiver::goToSignIn),
    SHOW_TARIFFS(GoToPageReceiver::goToTariffs),
    SHOW_NEWS(GoToPageReceiver::goToNews),
    SHOW_ABOUT_US(GoToPageReceiver::goToAboutUs),
  //  SHOW_SIGN_IN(GoToPageReceiver::goToSignIn),
    AUTHENTICATION(UserReceiver::authenticate),
    GO_TO_REGISTRATION(GoToPageReceiver::goToRegistration),
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
