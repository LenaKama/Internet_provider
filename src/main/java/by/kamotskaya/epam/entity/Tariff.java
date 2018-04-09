package by.kamotskaya.epam.entity;

/**
 * @author Lena Kamotskaya
 */
public class Tariff extends Entity{

    private String t_id;
    private String t_name;
    private String connection_payment;
    private String daily_fee;
    private String traffic_limit;
    private String speed_in;
    private String speed_out;
    private String overrun_fee;
    private String sale_percent;
    private String sale_expiration_date;

    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getConnection_payment() {
        return connection_payment;
    }

    public void setConnection_payment(String connection_payment) {
        this.connection_payment = connection_payment;
    }

    public String getDaily_fee() {
        return daily_fee;
    }

    public void setDaily_fee(String daily_fee) {
        this.daily_fee = daily_fee;
    }

    public String getTraffic_limit() {
        return traffic_limit;
    }

    public void setTraffic_limit(String traffic_limit) {
        this.traffic_limit = traffic_limit;
    }

    public String getSpeed_in() {
        return speed_in;
    }

    public void setSpeed_in(String speed_in) {
        this.speed_in = speed_in;
    }

    public String getSpeed_out() {
        return speed_out;
    }

    public void setSpeed_out(String speed_out) {
        this.speed_out = speed_out;
    }

    public String getOverrun_fee() {
        return overrun_fee;
    }

    public void setOverrun_fee(String overrun_fee) {
        this.overrun_fee = overrun_fee;
    }

    public String getSale_percent() {
        return sale_percent;
    }

    public void setSale_percent(String sale_percent) {
        this.sale_percent = sale_percent;
    }

    public String getSale_expiration_date() {
        return sale_expiration_date;
    }

    public void setSale_expiration_date(String sale_expiration_date) {
        this.sale_expiration_date = sale_expiration_date;
    }
}
