package by.kamotskaya.internet_provider.command;

import by.kamotskaya.internet_provider.receiver.BaseReceiver;
import by.kamotskaya.internet_provider.receiver.GoToPageReceiver;
import by.kamotskaya.internet_provider.receiver.UserReceiver;

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
    AUTHENTICATE(UserReceiver::authenticate),
    CHECK_LOGIN(UserReceiver::checkLogin),
    GO_TO_REGISTRATION(GoToPageReceiver::goToRegistration),
    REGISTER(UserReceiver::register),
    DELETE_USER(UserReceiver::deleteUser),
    LOAD_USER_LIST(UserReceiver::loadUserList);

    private FunctionalCommand command;

    CommandType(FunctionalCommand command) {
        this.command = command;
    }

    public FunctionalCommand getCommand() {
        return command;
    }
}
