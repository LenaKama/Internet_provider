package by.kamotskaya.internet_provider.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lena Kamotskaya
 */
public class RequestContent {

    private Map<String, String[]> requestParameters = new HashMap<>();
    private Map<String, Object> requestAttributes = new HashMap<>();
    private static Map<String, Object> sessionAttributes = new HashMap<>();

    public RequestContent() {
    }

    public RequestContent(HttpServletRequest request) {
        requestParameters = request.getParameterMap();
    }

    public void putRequestAttribute(String name, Object value) {
        requestAttributes.put(name, value);
    }

    public void putSessionAttribute(String name, Object value) {
        sessionAttributes.put(name, value);
    }

    HttpServletRequest update(HttpServletRequest request) {
        requestAttributes.forEach((name, value) -> request.setAttribute(name, value));
        sessionAttributes.forEach((name, value) -> request.getSession().setAttribute(name, value));
        return request;
    }

    public Map<String, String[]> getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(Map<String, String[]> requestParameters) {
        this.requestParameters = requestParameters;
    }

    public void setRequestAttributes() {
    }

    public static Map<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }

}
