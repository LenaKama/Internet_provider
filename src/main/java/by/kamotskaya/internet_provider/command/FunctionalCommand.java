package by.kamotskaya.internet_provider.command;

import by.kamotskaya.internet_provider.controller.RequestContent;

import java.util.function.Function;


/**
 * Functional interface which accepts {@link RequestContent} object
 * and produces {@link CommandResult} result for the main controller.
 *
 * @author Lena Kamotskays
 */
@FunctionalInterface
public interface FunctionalCommand extends Function<RequestContent, CommandResult> {
}
