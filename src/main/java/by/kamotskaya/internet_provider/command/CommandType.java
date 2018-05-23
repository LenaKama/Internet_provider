package by.kamotskaya.internet_provider.command;

import by.kamotskaya.internet_provider.receiver.BaseReceiver;
import by.kamotskaya.internet_provider.receiver.GoToPageReceiver;
import by.kamotskaya.internet_provider.receiver.BalanceReceiver;
import by.kamotskaya.internet_provider.receiver.UserReceiver;

/**
 * @author Lena Kamotskaya
 */
public enum CommandType {

    CHANGE_LOCALE(BaseReceiver::changeLocale),
    SHOW_WELCOME_PAGE(GoToPageReceiver::goToWelcomePage),
    SHOW_TARIFFS(GoToPageReceiver::goToTariffs),
    SHOW_NEWS(GoToPageReceiver::goToNews),
    SHOW_ABOUT_US(GoToPageReceiver::goToAboutUs),
    SHOW_GENERAL(GoToPageReceiver::goToGeneral),
    SHOW_MESSAGES(GoToPageReceiver::goToMessages),
    SHOW_TRAFFIC_STATUS(GoToPageReceiver::goToTrafficStatus),
    SHOW_SESSIONS(GoToPageReceiver::goToSessions),
    SHOW_TRANSACTIONS(GoToPageReceiver::goToTransactions),
    SHOW_ACCOUNT_SETTINGS(GoToPageReceiver::goToAccountSettings),
    SHOW_CLIENTS(GoToPageReceiver::goToClients),
    AUTHENTICATE(UserReceiver::authenticate),
    CHECK_LOGIN(UserReceiver::checkLogin),
    REGISTER(UserReceiver::register),
    LOG_OUT(UserReceiver::logOut),
    DELETE_USER(UserReceiver::deleteUser),
    SHOW_USER_LIST(UserReceiver::showUserList),
    RECHARGE_ACCOUNT(BalanceReceiver::rechargeAccount),
    CHANGE_TARIFF(UserReceiver::changeTariff),
    UPDATE_USER(UserReceiver::updateUser);

    private FunctionalCommand command;

    CommandType(FunctionalCommand command) {
        this.command = command;
    }

    public FunctionalCommand getCommand() {
        return command;
    }
}
