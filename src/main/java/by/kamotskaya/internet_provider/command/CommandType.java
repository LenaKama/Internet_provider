package by.kamotskaya.internet_provider.command;

import by.kamotskaya.internet_provider.receiver.*;

/**
 * Enum that contains commands names and the relevant methods for each command.
 *
 * @author Lena Kamotskaya
 */
public enum CommandType {

    CHANGE_LOCALE(UserReceiver::changeLocale),
    SHOW_WELCOME_PAGE(GoToPageReceiver::goToWelcomePage),
    SHOW_TARIFFS(GoToPageReceiver::goToTariffs),
    SHOW_HELP(GoToPageReceiver::goToHelp),
    SHOW_ABOUT_US(GoToPageReceiver::goToAboutUs),
    SHOW_GENERAL(GoToPageReceiver::goToGeneral),
    SHOW_MESSAGES(GoToPageReceiver::goToMessages),
    SHOW_TRAFFIC_STATUS(GoToPageReceiver::goToTrafficStatus),
    SHOW_SESSIONS(GoToPageReceiver::goToSessions),
    SHOW_TRANSACTIONS(GoToPageReceiver::goToTransactions),
    SHOW_ACCOUNT_SETTINGS(GoToPageReceiver::goToAccountSettings),
    SHOW_CLIENTS(GoToPageReceiver::goToClients),
    AUTHENTICATE(UserReceiver::authenticate),
    REGISTER(UserReceiver::register),
    LOG_OUT(UserReceiver::logOut),
    DELETE_USER(UserReceiver::deleteUser),
    RECHARGE_ACCOUNT(BalanceReceiver::rechargeAccount),
    ADD_NEW_TARIFF(TariffReceiver::addNewTariff),
    CHANGE_TARIFF(UserReceiver::changeTariff),
    DELETE_TARIFF(TariffReceiver::deleteTariff),
    UPDATE_TARIFF(TariffReceiver::updateTariff),
    UPDATE_USER(UserReceiver::updateUser),
    ADD_SALE(TariffReceiver::addSale),
    ADD_FEEDBACK(FeedbackReceiver::addFeedback),
    REPLY_ON_FEEDBACK(FeedbackReceiver::replyOnFeedback);

    private FunctionalCommand command;

    CommandType(FunctionalCommand command) {
        this.command = command;
    }

    public FunctionalCommand getCommand() {
        return command;
    }
}
