package by.kamotskaya.internet_provider.entity;

import java.util.Date;

/**
 * @author Lena Kamotskaya
 */
public class Session extends Entity {

    private Date sessionStartDate;
    private Date sessionEndDate;
    private int trafficIn;
    private int trafficOut;
    private String usLogin;

    public Date getSessionStartDate() {
        return sessionStartDate;
    }

    public void setSessionStartDate(Date sessionStartDate) {
        this.sessionStartDate = sessionStartDate;
    }

    public Date getSessionEndDate() {
        return sessionEndDate;
    }

    public void setSessionEndDate(Date sessionEndDate) {
        this.sessionEndDate = sessionEndDate;
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
