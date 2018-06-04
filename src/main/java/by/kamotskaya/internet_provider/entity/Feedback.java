package by.kamotskaya.internet_provider.entity;

/**
 * Entity class.
 *
 * @author Lena Kamotskaya
 */
public class Feedback extends Entity {

    private int fId;
    private String fName;
    private String fEmail;
    private String fMessage;
    private String fAnswer;
    private String usLogin;

    public Feedback() {}

    public int getfId() {
        return fId;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feedback feedback = (Feedback) o;

        if (fId != feedback.fId) return false;
        if (fName != null ? !fName.equals(feedback.fName) : feedback.fName != null) return false;
        if (fEmail != null ? !fEmail.equals(feedback.fEmail) : feedback.fEmail != null) return false;
        if (fMessage != null ? !fMessage.equals(feedback.fMessage) : feedback.fMessage != null) return false;
        if (fAnswer != null ? !fAnswer.equals(feedback.fAnswer) : feedback.fAnswer != null) return false;
        return usLogin != null ? usLogin.equals(feedback.usLogin) : feedback.usLogin == null;
    }

    @Override
    public int hashCode() {
        int result = fId;
        result = 31 * result + (fName != null ? fName.hashCode() : 0);
        result = 31 * result + (fEmail != null ? fEmail.hashCode() : 0);
        result = 31 * result + (fMessage != null ? fMessage.hashCode() : 0);
        result = 31 * result + (fAnswer != null ? fAnswer.hashCode() : 0);
        result = 31 * result + (usLogin != null ? usLogin.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "fId=" + fId +
                ", fName='" + fName + '\'' +
                ", fEmail='" + fEmail + '\'' +
                ", fMessage='" + fMessage + '\'' +
                ", fAnswer='" + fAnswer + '\'' +
                ", usLogin='" + usLogin + '\'' +
                '}';
    }
}

