package by.kamotskaya.internet_provider.controller.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.kamotskaya.internet_provider.constant.ParamName;
import by.kamotskaya.internet_provider.dao.OpeningBalanceDAO;
import by.kamotskaya.internet_provider.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Filters requests to the main servlet-Controller, not allowing
 * access to resources that must be available only to authorized users.
 *
 * @author Pavel Sorokoletov
 */
//@WebFilter
public class AuthorizationFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(AuthorizationFilter.class);
    private final static String ERROR_DESTINATION_INIT_PARAMETER = "goto-auth-error-page";
    private final static String INDEX_PAGE = "index.jsp";
    private final static String AUTHORIZATION_ERROR_PAGE = "auth-error.jsp";
    private final static String COMMAND_ATTRIBUTE = "command";

    /**
     * Contains {@code enum} commands-name constants, which are allowed for
     * unauthorized users (guest mode).
     */
    private enum ValidGuestCommand {
        AUTHENTICATE, REGISTER, SHOW_WELCOME_PAGE, SHOW_TARIFFS, SHOW_HELP, SHOW_ABOUT_US
    }

    /**
     * Contains {@code enum} commands-name constants, which are allowed for
     * admins (admin mode).
     */
    private enum AdminCommand {
        SHOW_MESSAGES, SHOW_CLIENTS, DELETE_USER, DELETE_TARIFF, UPDATE_TARIFF, ADD_SALE, REPLY_ON_FEEDBACK
    }

    /**
     * Contains {@code enum} commands-name constants, which are allowed for clients (client mode).
     */
    private enum ClientCommand {
    }

    /**
     * Contains {@code enum} commands-name constants, which are allowed for
     * both clients and admins.
     */
    private enum AuthenticatedUserCommand {
        SHOW_TRAFFIC_STATUS, SHOW_SESSIONS, SHOW_TRANSACTIONS, SHOW_ACCOUNT_SETTINGS, RECHARGE_ACCOUNT, CHANGE_TARIFF, UPDATE_USER, LOG_OUT
    }

    /**
     * Contains {@code enum} commands-name constants, which are allowed for
     * all users of the site.
     */
    private enum BaseUserCommand {
        SHOW_WELCOME_PAGE, SHOW_TARIFFS, SHOW_HELP, SHOW_ABOUT_US, ADD_FEEDBACK
    }

    private static String redirect_page;
    private String gotoDestination;

    /**
     * Reads initialization value from WEB.XML file. If value is "yes", bad request
     * will be redirected to specific error page. If value not "no" or missing,
     * request redirects to the main page.
     */
    @Override
    public void init(FilterConfig fConfig) throws ServletException {

        gotoDestination = fConfig.getInitParameter(ERROR_DESTINATION_INIT_PARAMETER);
        redirect_page = ("yes".equals(gotoDestination)) ? AUTHORIZATION_ERROR_PAGE : INDEX_PAGE;
    }

    /**
     * Performs filtering of commands
     *
     * @param request  {@link ServletRequest}
     * @param response {@link ServletResponse}
     * @param chain    {@link FilterChain}
     * @throws IOException, ServletException
     */

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        String command;
        boolean isValidCommand = false;

        if (httpRequest.getParameter(COMMAND_ATTRIBUTE) != null) {
            command = httpRequest.getParameter(COMMAND_ATTRIBUTE);

            if (session.getAttribute(ParamName.US_ROLE) != null) {
                String usRole = (String) session.getAttribute(ParamName.US_ROLE);

                try {
                    if (usRole.equals(ParamName.ADMIN)) {
                        AdminCommand.valueOf(command.toUpperCase());
                    } else {
                        ClientCommand.valueOf(command.toUpperCase());
                    }
                    isValidCommand = true;
                } catch (IllegalArgumentException e) {
                    LOGGER.log(Level.DEBUG,
                            "Invalid user command:" + command + " in AuthorizationFilter");
                }

            } else
                try {
                    ValidGuestCommand.valueOf(command.toUpperCase());
                    isValidCommand = true;
                } catch (IllegalArgumentException e) {
                    LOGGER.log(Level.DEBUG,
                            "Invalid guest command:" + command + " in AuthorizationFilter");
                }

        }

        if (isValidCommand) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(redirect_page);
        }
    }

    public void destroy() {
        gotoDestination = null;
    }

}
