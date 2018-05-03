package by.kamotskaya.internet_provider.command;


import by.kamotskaya.internet_provider.controller.RequestContent;

import java.util.function.Function;


/**
 * @author Lena Kamotskays
 */
@FunctionalInterface
public interface FunctionalCommand extends Function<RequestContent, CommandResult> {
}
