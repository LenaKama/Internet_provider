package by.kamotskaya.internet_provider.entity;

import java.sql.Date;

/**
 * Entity class.
 *
 * @author Lena Kamotskaya
 */
public class Tariff extends Entity{

    private int tId;
    private String tName;
    private double connectionPayment;
    private double dailyFee;
    private int trafficLimit;
    private String speedIn;
    private String speedOut;
    private double overrunFee;
    private int salePercent;
    private Date saleExpirationDate;

    public Tariff() {}

    public Tariff(String tName, double connectionPayment, double dailyFee, int trafficLimit, String speedIn, String speedOut, double overrunFee) {
        this.tName = tName;
        this.connectionPayment = connectionPayment;
        this.dailyFee = dailyFee;
        this.trafficLimit = trafficLimit;
        this.speedIn = speedIn;
        this.speedOut = speedOut;
        this.overrunFee = overrunFee;
    }

    public int getTId() {
        return tId;
    }

    public void setTId(int tId) {
        this.tId = tId;
    }

    public String getTName() {
        return tName;
    }

    public void setTName(String tName) {
        this.tName = tName;
    }

    public double getConnectionPayment() {
        return connectionPayment;
    }

    public void setConnectionPayment(double connectionPayment) {
        this.connectionPayment = connectionPayment;
    }

    public double getDailyFee() {
        return dailyFee;
    }

    public void setDailyFee(double dailyFee) {
        this.dailyFee = dailyFee;
    }

    public int getTrafficLimit() {
        return trafficLimit;
    }

    public void setTrafficLimit(int trafficLimit) {
        this.trafficLimit = trafficLimit;
    }

    public double getOverrunFee() {
        return this.overrunFee;
    }

    public void setOverrunFee(double overrunFee) {
        this.overrunFee = overrunFee;
    }

    public void setSalePercent(int salePercent) {
        this.salePercent = salePercent;
    }

    public String getSpeedIn() {
        return speedIn;
    }

    public void setSpeedIn(String speedIn) {
        this.speedIn = speedIn;
    }

    public String getSpeedOut() {
        return speedOut;
    }

    public void setSpeedOut(String speedOut) {
        this.speedOut = speedOut;
    }

    public Date getSaleExpirationDate() {
        return saleExpirationDate;
    }

    public void setSaleExpirationDate(Date saleExpirationDate) {
        this.saleExpirationDate = saleExpirationDate;
    }

    public int getSalePercent() {
        return salePercent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tariff tariff = (Tariff) o;

        if (tId != tariff.tId) return false;
        if (Double.compare(tariff.connectionPayment, connectionPayment) != 0) return false;
        if (Double.compare(tariff.dailyFee, dailyFee) != 0) return false;
        if (trafficLimit != tariff.trafficLimit) return false;
        if (Double.compare(tariff.overrunFee, overrunFee) != 0) return false;
        if (salePercent != tariff.salePercent) return false;
        if (tName != null ? !tName.equals(tariff.tName) : tariff.tName != null) return false;
        if (speedIn != null ? !speedIn.equals(tariff.speedIn) : tariff.speedIn != null) return false;
        if (speedOut != null ? !speedOut.equals(tariff.speedOut) : tariff.speedOut != null) return false;
        return saleExpirationDate != null ? saleExpirationDate.equals(tariff.saleExpirationDate) : tariff.saleExpirationDate == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = tId;
        result = 31 * result + (tName != null ? tName.hashCode() : 0);
        temp = Double.doubleToLongBits(connectionPayment);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(dailyFee);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + trafficLimit;
        result = 31 * result + (speedIn != null ? speedIn.hashCode() : 0);
        result = 31 * result + (speedOut != null ? speedOut.hashCode() : 0);
        temp = Double.doubleToLongBits(overrunFee);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + salePercent;
        result = 31 * result + (saleExpirationDate != null ? saleExpirationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "tId=" + tId +
                ", tName='" + tName + '\'' +
                ", connectionPayment=" + connectionPayment +
                ", dailyFee=" + dailyFee +
                ", trafficLimit=" + trafficLimit +
                ", speedIn='" + speedIn + '\'' +
                ", speedOut='" + speedOut + '\'' +
                ", overrunFee=" + overrunFee +
                ", salePercent=" + salePercent +
                ", saleExpirationDate=" + saleExpirationDate +
                '}';
    }
}
