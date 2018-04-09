package by.kamotskaya.epam.command;


import by.kamotskaya.epam.controller.RequestContent;

import java.util.function.Function;


/**
 * @author Lena Kamotskays
 */
@FunctionalInterface
public interface FunctionalCommand extends Function<RequestContent, CommandResult> {
}
