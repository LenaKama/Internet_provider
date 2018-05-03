package by.kamotskaya.internet_provider.entity;

import java.util.Date;

/**
 * @author Lena Kamotskaya
 */
public class Tariff extends Entity{

    private String id;//?
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
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


}
