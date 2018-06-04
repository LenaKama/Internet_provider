package by.kamotskaya.internet_provider.entity;

import java.sql.Date;

/**
 * Entity class.
 *
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OpeningBalance that = (OpeningBalance) o;

        if (Double.compare(that.obSum, obSum) != 0) return false;
        if (obDate != null ? !obDate.equals(that.obDate) : that.obDate != null) return false;
        return usLogin != null ? usLogin.equals(that.usLogin) : that.usLogin == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = obDate != null ? obDate.hashCode() : 0;
        temp = Double.doubleToLongBits(obSum);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (usLogin != null ? usLogin.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OpeningBalance{" +
                "obDate=" + obDate +
                ", obSum=" + obSum +
                ", usLogin='" + usLogin + '\'' +
                '}';
    }
}
