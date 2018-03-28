package by.kamotskaya.epam.content;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lena Kamotskaya
 */
public class RequestContent {
private Map<String, String[]> requestParameters = new HashMap<>();

    public Map<String, String[]> getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(Map<String, String[]> requestParameters) {
        this.requestParameters = requestParameters;
    }
}
