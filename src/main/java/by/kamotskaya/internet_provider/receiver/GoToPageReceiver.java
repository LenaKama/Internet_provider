package by.kamotskaya.internet_provider.receiver;

import by.kamotskaya.internet_provider.command.CommandResult;
import by.kamotskaya.internet_provider.constant.PagePath;
import by.kamotskaya.internet_provider.controller.RequestContent;
import by.kamotskaya.internet_provider.dao.impl.TariffDAO;
import by.kamotskaya.internet_provider.exception.DAOException;

/**
 * @author Lena Kamotskaya
 */
public class GoToPageReceiver {

    public static CommandResult goToSignIn(RequestContent content){
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.SIGN_IN);
    }

    public static CommandResult goToTariffs(RequestContent content){
        TariffDAO tariffDAO = new TariffDAO();
        try {
            content.putRequestAttribute("tariffList", tariffDAO.findAllTariffs());
        } catch (DAOException e) {
            //content.putRequestAttr
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.TARIFFS);
    }

    public static CommandResult goToNews(RequestContent content){
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.NEWS);
    }

    public static CommandResult goToAboutUs(RequestContent content) {
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ABOUT_US);
    }

    public static CommandResult goToRegistration(RequestContent content) {
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.REGISTRATION);
    }
}
