package by.kamotskaya.epam.command;


import by.kamotskaya.epam.content.RequestContent;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lena Kamotskays
 */
public interface JspCommand {
    CommandResult execute(RequestContent content);
}
