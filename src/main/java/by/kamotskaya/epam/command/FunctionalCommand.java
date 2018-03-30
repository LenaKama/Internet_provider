package by.kamotskaya.epam.command;


import by.kamotskaya.epam.controller.RequestContent;


/**
 * @author Lena Kamotskays
 */
public interface FunctionalCommand {
    CommandResult execute(RequestContent content);
}
