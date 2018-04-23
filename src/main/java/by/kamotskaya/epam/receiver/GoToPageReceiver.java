package by.kamotskaya.epam.receiver;

import by.kamotskaya.epam.command.CommandResult;
import by.kamotskaya.epam.constant.PagePath;
import by.kamotskaya.epam.controller.RequestContent;

/**
 * @author Lena Kamotskaya
 */
public class GoToPageReceiver {

    public static CommandResult goToSignIn(RequestContent content){
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.SIGN_IN);
    }

    public static CommandResult goToTariffs(RequestContent content){
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
