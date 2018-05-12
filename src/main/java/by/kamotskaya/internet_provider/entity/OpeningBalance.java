package by.kamotskaya.internet_provider.entity;

import java.sql.Date;

/**
 * @author Lena Kamotskaya
 */
public class OpeningBalance extends Entity {

    private Date obDate;
    private double obSum;
    private String usLogin;

    public Date getObDate() {
        return obDate;
    }

    public void setObDate(Date obDate) {
        this.obDate = obDate;
    }

    public double getObSum() {
        return obSum;
    }

    public void setObSum(double obSum) {
        this.obSum = obSum;
    }

    public String getUsLogin() {
        return usLogin;
    }

    public void setUsLogin(String usLogin) {
        this.usLogin = usLogin;
    }
}
