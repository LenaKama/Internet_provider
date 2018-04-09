package by.kamotskaya.epam.constant;

/**
 * @author Lena Kamotskaya
 */
public class SqlQuery {

    public final static String REGISTER_CLIENT = "INSERT INTO user(us_login, us_password, us_email, us_name, us_surname, us_passport, us_role, t_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    public final static String FIND_BY_LOGIN = "SELECT us_login, us_password FROM user"; // WHERE us_login = ?";
    public final static String UPDATE_USER = "ALTER TABLE user SET us_login = ? SET us_password FROM user";
}
