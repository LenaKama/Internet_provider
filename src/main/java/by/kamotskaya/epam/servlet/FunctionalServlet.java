package by.kamotskaya.epam.servlet;

import by.kamotskaya.epam.factory.CommandFactory;
import by.kamotskaya.epam.command.CommandResult;
import by.kamotskaya.epam.command.JspCommand;
import by.kamotskaya.epam.content.RequestContent;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Lena Kamotskaya
 */
@WebServlet(name = "FunctionalServlet", urlPatterns = "/FunctionalServlet")
public class FunctionalServlet extends HttpServlet {

    private static Logger LOGGER = LogManager.getLogger(FunctionalServlet.class);

    public FunctionalServlet() {
        super();
    }

    public void init() throws ServletException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestContent content = new RequestContent();
        content.setRequestParameters(request.getParameterMap());
        CommandFactory factory = CommandFactory.getInstance();
        JspCommand command = factory.getCommand(content);
        CommandResult result = command.execute(content);
        switch (result.getResponseType()) {
            case FORWARD:
                request.getRequestDispatcher(result.getPage()).forward(request, response);
                break;
            case REDIRECT:
                response.sendRedirect(result.getPage());
        }
    }

    public void destroy() {

        LOGGER.log(Level.INFO, "FunctionalServlet is destroyed");
        super.destroy();
    }
}
