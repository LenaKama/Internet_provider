package by.kamotskaya.epam.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lena Kamotskaya
 */
public class RequestContent {
private Map<String, String[]> requestParameters = new HashMap<>();
private Map<String, String[]> requestAttributes = new HashMap<>();
private Map<String, String[]> sessionAttributes = new HashMap<>();

    public Map<String, String[]> getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(Map<String, String[]> requestParameters) {
        this.requestParameters = requestParameters;
    }

    public void setRequestAttributes() {}

    public void setSessionAttributesAttributes() {}

    public HttpServletRequest update() {
        return request;
    }
}
