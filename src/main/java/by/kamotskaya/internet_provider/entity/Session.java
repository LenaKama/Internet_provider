package by.kamotskaya.internet_provider.entity;

import java.util.Date;

/**
 * @author Lena Kamotskaya
 */
public class Session extends Entity {

    private Date sessionStart;
    private Date sessionEnd;
    private int trafficIn;
    private int trafficOut;
    private String usLogin;

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
}
