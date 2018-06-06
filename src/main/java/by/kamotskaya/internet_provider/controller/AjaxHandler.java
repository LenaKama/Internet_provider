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
        String command = request.getParameter("command");
        switch (command) {
            case "checkLogin":
                String checkLogin = request.getParameter("checkLogin");
                response.getWriter().write(ajaxReceiver.checkLogin(checkLogin));
                break;
            case "changeTariff":
                int tId = Integer.parseInt(request.getParameter("tId"));
                String usLogin = request.getParameter("usLogin");
               response.getWriter().write(ajaxReceiver.changeTariff(usLogin, tId));
                break;
        }
    }

    @Override
    public void destroy() {

        LOGGER.log(Level.INFO, "AjaxHandler is destroyed");
        super.destroy();
    }
}
