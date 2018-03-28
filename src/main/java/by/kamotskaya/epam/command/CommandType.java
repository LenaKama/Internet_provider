package by.kamotskaya.epam.command;

import by.kamotskaya.epam.command.JspCommand;
import by.kamotskaya.epam.receiver.BaseReceiver;

/**
 * @author Lena Kamotskaya
 */
public enum CommandType {
    LANGUAGE(BaseReceiver::selectLanguage);

    private JspCommand command;

    CommandType(JspCommand command) {
        this.command = command;
    }

    public JspCommand getCommand() {
        return command;
    }
}
