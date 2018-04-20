package by.kamotskaya.epam.constant;

/**
 * @author Lena Kamotskaya
 */
public class SqlQuery {


    //TariffDAO


    //SessionDAO
    public final static String ADD_NEW_SESSION = "INSERT INTO session(session_date, session_duration, traffic_in, traffic_out, speed_in, speed_out, us_login) VALUES(?, ?, ?, ?, ?)";
    public final static String UPDATE_SESSION = "ALTER TABLE session SET t_name = ? SET connection_payment = ? SET daily_fee = ? FROM user";
    public final static String DELETE_SESSION = "ALTER TABLE session DELETE FROM tariff WHERE t_name = ?";

}
