package by.kamotskaya.epam.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lena Kamotskaya
 */
public class RequestContent {

    private HttpServletRequest request;
    private Map<String, String[]> requestParameters = new HashMap<>();
    private Map<String, String> requestAttributes = new HashMap<>(); // type
    private Map<String, String> sessionAttributes = new HashMap<>();

    public RequestContent() {
    }

    public RequestContent(HttpServletRequest request) {
        this.request = request;
        buildRequestParameterMap();
    }

    private void buildRequestParameterMap() {
        requestParameters = request.getParameterMap();
    }

    public void putRequestAttribute(String name, String value) {
        requestAttributes.put(name, value);
    }

    public void putSessionAttribute(String name, String value) {
        sessionAttributes.put(name, value);
    }

    public HttpServletRequest update() {
        requestAttributes.forEach((a,b) -> request.setAttribute(a, b));
        return request; // set all setting parameters
    }

    public Map<String, String[]> getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(Map<String, String[]> requestParameters) {
        this.requestParameters = requestParameters;
    }

    public void setRequestAttributes() {
    }

    public void setSessionAttributesAttributes() {
    }

  }
