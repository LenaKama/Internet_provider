package by.kamotskaya.internet_provider.command;

/**
 * Class for creating result for main controller of application,
 * contains a page's name and a type of response.
 *
 * @author Lena Kamotskaya
 */
public class CommandResult {
    public enum ResponseType {
        FORWARD, REDIRECT
    }
    private ResponseType responseType;
    private String page;

    public CommandResult() {}

    public CommandResult(ResponseType responseType, String page) {
        this.responseType = responseType;
        this.page = page;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
