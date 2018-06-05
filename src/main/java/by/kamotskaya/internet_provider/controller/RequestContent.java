package by.kamotskaya.internet_provider.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Class for keeping content of requests.
 *
 * @author Lena Kamotskaya
 */
public class RequestContent {

    private Map<String, String[]> requestParameters = new HashMap<>();
    private Map<String, Object> requestAttributes = new HashMap<>();
    private Map<String, Object> sessionAttributes = new HashMap<>();

    public RequestContent() {
    }

    public RequestContent(HttpServletRequest request) {
        requestParameters = request.getParameterMap();
        Enumeration<String> attributeNames = request.getSession().getAttributeNames();
        List<String> names = Collections.list(attributeNames);
        for (String name: names) {
            sessionAttributes.put(name, request.getSession().getAttribute(name));
        }
    }

    public void putRequestAttribute(String name, Object value) {
        requestAttributes.put(name, value);
    }

    public void putSessionAttribute(String name, Object value) {
        sessionAttributes.put(name, value);
    }

    /**
     * Updates giving request while setting attributes.
     *
     * @param request {@link HttpServletRequest}
     * @return {@link HttpServletRequest} updated request
     */
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

    public Map<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }

    public void setSessionAttributes(Map<String, Object> sessionAttributes) {
        this.sessionAttributes = sessionAttributes;
    }
}
