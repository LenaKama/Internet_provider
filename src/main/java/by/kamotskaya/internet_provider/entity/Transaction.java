package by.kamotskaya.internet_provider.entity;


import java.sql.Date;

/**
 * @author Lena Kamotskaya
 */
public class Transaction extends Entity {

    private Date trDate;
    private String usLogin;
    private double trSum;
    private String trInfo;

    public Date getTrDate() {
        return trDate;
    }

    public void setTrDate(Date trDate) {
        this.trDate = trDate;
    }

    public String getUsLogin() {
        return usLogin;
    }

    public void setUsLogin(String usLogin) {
        this.usLogin = usLogin;
    }

    public double getTrSum() {
        return trSum;
    }

    public void setTrSum(double trSum) {
        this.trSum = trSum;
    }

    public String getTrInfo() {
        return trInfo;
    }

    public void setTrInfo(String trInfo) {
        this.trInfo = trInfo;
    }
}
