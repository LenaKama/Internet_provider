package by.kamotskaya.internet_provider.receiver;

import by.kamotskaya.internet_provider.command.CommandResult;
import by.kamotskaya.internet_provider.constant.PagePath;
import by.kamotskaya.internet_provider.constant.ParamName;
import by.kamotskaya.internet_provider.controller.RequestContent;
import by.kamotskaya.internet_provider.dao.impl.FeedbackDAO;
import by.kamotskaya.internet_provider.entity.Feedback;
import by.kamotskaya.internet_provider.exception.ConnectionPoolException;
import by.kamotskaya.internet_provider.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Lena Kamotskaya
 */
public class FeedbackReceiver {

    private static final Logger LOGGER = LogManager.getLogger(FeedbackReceiver.class);

    public static CommandResult addFeedback(RequestContent content) {
        String fName = content.getRequestParameters().get("fName")[0];
        String fEmail = content.getRequestParameters().get("fEmail")[0];
        String fMessage = content.getRequestParameters().get("fMessage")[0];

        Feedback feedback = new Feedback();
        feedback.setfName(fName);
        feedback.setfEmail(fEmail);
        feedback.setfMessage(fMessage);
        try {
            FeedbackDAO feedbackDAO = new FeedbackDAO();
            feedbackDAO.add(feedback);

        } catch (ConnectionPoolException | DAOException e) {
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error while adding a feedback.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return GoToPageReceiver.goToWelcomePage(content);
    }
}
