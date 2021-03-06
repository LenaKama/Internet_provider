package by.kamotskaya.internet_provider.controller;

import by.kamotskaya.internet_provider.receiver.AjaxReceiver;
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
 * Servlet for AJAX calls.
 *
 * @author Lena Kamotskaya
 */
@WebServlet(name = "AjaxHandler", urlPatterns = "/AjaxHandler")
public class AjaxHandler extends HttpServlet {

    private static Logger LOGGER = LogManager.getLogger(AjaxHandler.class);

    public AjaxHandler() {
        super();
    }

    @Override
    public void init() throws ServletException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        AjaxReceiver ajaxReceiver = new AjaxReceiver();

        String checkLogin = request.getParameter("login");
        response.getWriter().write(ajaxReceiver.checkLogin(checkLogin));

    }

    @Override
    public void destroy() {

        LOGGER.log(Level.INFO, "AjaxHandler is destroyed");
        super.destroy();
    }
}
