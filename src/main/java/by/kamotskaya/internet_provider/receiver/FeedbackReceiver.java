package by.kamotskaya.internet_provider.receiver;

import by.kamotskaya.internet_provider.command.CommandResult;
import by.kamotskaya.internet_provider.constant.PagePath;
import by.kamotskaya.internet_provider.constant.ParamName;
import by.kamotskaya.internet_provider.controller.RequestContent;
import by.kamotskaya.internet_provider.dao.FeedbackDAO;
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
        String fName = content.getRequestParameters().get(ParamName.F_NAME)[0];
        String fEmail = content.getRequestParameters().get(ParamName.F_EMAIL)[0];
        String fMessage = content.getRequestParameters().get(ParamName.F_MESSAGE)[0];

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

    public static CommandResult replyOnFeedback(RequestContent content) {
        int fId = Integer.parseInt(content.getRequestParameters().get("fId")[0]);
        String fAnswer = content.getRequestParameters().get("fAnswer")[0];
        String usLogin = String.valueOf(content.getSessionAttributes().get(ParamName.US_LOGIN));

        try {
            FeedbackDAO feedbackDAO = new FeedbackDAO();
            Feedback feedback = feedbackDAO.findUnrepliedFeedback(fId);
            feedback.setfAnswer(fAnswer);
            feedback.setUsLogin(usLogin);
            feedbackDAO.update(feedback);
        } catch (ConnectionPoolException | DAOException e) {
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error while replying on a feedback.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return GoToPageReceiver.goToMessages(content);
    }
}
