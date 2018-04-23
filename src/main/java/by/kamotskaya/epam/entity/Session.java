package by.kamotskaya.epam.entity;

import java.util.Date;

/**
 * @author Lena Kamotskaya
 */
public class Session {

    private Date sessionDate;
    private String SessionDuration;
    private int trafficIn;
    private int trafficOut;
    private int usLogin;//??

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    public String getSessionDuration() {
        return SessionDuration;
    }

    public void setSessionDuration(String sessionDuration) {
        SessionDuration = sessionDuration;
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

    public int getUsLogin() {
        return usLogin;
    }

    public void setUsLogin(int usLogin) {
        this.usLogin = usLogin;
    }
}
