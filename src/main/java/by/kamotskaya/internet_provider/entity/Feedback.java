package by.kamotskaya.internet_provider.entity;

/**
 * @author Lena Kamotskaya
 */
public class Feedback extends Entity {

    private String fName;
    private String fEmail;
    private String fMessage;
    private String fAnswer;
    private String usLogin;

    public Feedback() {}

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfEmail() {
        return fEmail;
    }

    public void setfEmail(String fEmail) {
        this.fEmail = fEmail;
    }

    public String getfMessage() {
        return fMessage;
    }

    public void setfMessage(String fMessage) {
        this.fMessage = fMessage;
    }

    public String getfAnswer() {
        return fAnswer;
    }

    public void setfAnswer(String fAnswer) {
        this.fAnswer = fAnswer;
    }

    public String getUsLogin() {
        return usLogin;
    }

    public void setUsLogin(String usLogin) {
        this.usLogin = usLogin;
    }
}
