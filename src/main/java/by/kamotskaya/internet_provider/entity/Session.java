package by.kamotskaya.internet_provider.entity;

import java.util.Date;

/**
 * Entity class.
 *
 * @author Lena Kamotskaya
 */
public class Session extends Entity {

    private int sessionId;
    private Date sessionStart;
    private Date sessionEnd;
    private int trafficIn;
    private int trafficOut;
    private String usLogin;

    public Session() {}

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public Date getSessionStart() {
        return sessionStart;
    }

    public void setSessionStart(Date sessionStart) {
        this.sessionStart = sessionStart;
    }

    public Date getSessionEnd() {
        return sessionEnd;
    }

    public void setSessionEnd(Date sessionEnd) {
        this.sessionEnd = sessionEnd;
    }

    public int getTrafficIn() {
        return trafficIn;
    }

    public void setTrafficIn(int trafficIn) {
        this.trafficIn = trafficIn;
    }

    public int getTrafficOut() {
        return trafficOut;
    }

    public void setTrafficOut(int trafficOut) {
        this.trafficOut = trafficOut;
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

        Session session = (Session) o;

        if (sessionId != session.sessionId) return false;
        if (trafficIn != session.trafficIn) return false;
        if (trafficOut != session.trafficOut) return false;
        if (sessionStart != null ? !sessionStart.equals(session.sessionStart) : session.sessionStart != null)
            return false;
        if (sessionEnd != null ? !sessionEnd.equals(session.sessionEnd) : session.sessionEnd != null) return false;
        return usLogin != null ? usLogin.equals(session.usLogin) : session.usLogin == null;
    }

    @Override
    public int hashCode() {
        int result = sessionId;
        result = 31 * result + (sessionStart != null ? sessionStart.hashCode() : 0);
        result = 31 * result + (sessionEnd != null ? sessionEnd.hashCode() : 0);
        result = 31 * result + trafficIn;
        result = 31 * result + trafficOut;
        result = 31 * result + (usLogin != null ? usLogin.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionId=" + sessionId +
                ", sessionStart=" + sessionStart +
                ", sessionEnd=" + sessionEnd +
                ", trafficIn=" + trafficIn +
                ", trafficOut=" + trafficOut +
                ", usLogin='" + usLogin + '\'' +
                '}';
    }
}
