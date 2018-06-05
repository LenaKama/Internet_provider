package by.kamotskaya.internet_provider.controller;

import by.kamotskaya.internet_provider.command.CommandFactory;
import by.kamotskaya.internet_provider.command.CommandResult;
import by.kamotskaya.internet_provider.command.FunctionalCommand;

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
 * Main controller of the application.
 *
 * @author Lena Kamotskaya
 */
@WebServlet(name = "Controller", urlPatterns = "/Controller")
public class Controller extends HttpServlet {

    private static Logger LOGGER = LogManager.getLogger(Controller.class);

    public Controller() {
        super();
    }

    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            RequestContent content = new RequestContent(request);
            FunctionalCommand command = CommandFactory.getINSTANCE().getCommand(content);
            CommandResult result = command.apply(content);
            request = content.update(request);
            switch (result.getResponseType()) {
                case FORWARD:
                    request.getRequestDispatcher(result.getPage()).forward(request, response);
                    break;
                case REDIRECT:
                    response.sendRedirect(result.getPage());
            }
        }

    @Override
    public void destroy() {

        LOGGER.log(Level.INFO, "Controller is destroyed");
        super.destroy();
    }
}
