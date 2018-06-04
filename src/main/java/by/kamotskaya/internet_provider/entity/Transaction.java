package by.kamotskaya.internet_provider.entity;


import java.sql.Date;

/**
 * Entity class.
 *
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (Double.compare(that.trSum, trSum) != 0) return false;
        if (trDate != null ? !trDate.equals(that.trDate) : that.trDate != null) return false;
        if (usLogin != null ? !usLogin.equals(that.usLogin) : that.usLogin != null) return false;
        return trInfo != null ? trInfo.equals(that.trInfo) : that.trInfo == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = trDate != null ? trDate.hashCode() : 0;
        result = 31 * result + (usLogin != null ? usLogin.hashCode() : 0);
        temp = Double.doubleToLongBits(trSum);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (trInfo != null ? trInfo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trDate=" + trDate +
                ", usLogin='" + usLogin + '\'' +
                ", trSum=" + trSum +
                ", trInfo='" + trInfo + '\'' +
                '}';
    }
}
