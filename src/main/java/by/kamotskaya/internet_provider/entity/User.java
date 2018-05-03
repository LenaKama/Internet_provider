package by.kamotskaya.internet_provider.entity;

/**
 * @author Lena Kamotskaya
 */
public class User extends Entity {

    private String usLogin;
    private String usPassword;
    private String usName;
    private String usSurname;
    private String usEmail;
    private String usPassport;
    private String usRole;       ////generate
    private boolean usBan;
    private int tariffId;  //???

    public String getUsLogin() {
        return usLogin;
    }

    public void setUsLogin(String usLogin) {
        this.usLogin = usLogin;
    }

    public String getUsPassword() {
        return usPassword;
    }

    public void setUsPassword(String usPassword) {
        this.usPassword = usPassword;
    }

    public String getUsEmail() {
        return usEmail;
    }

    public void setUsEmail(String usEmail) {
        this.usEmail = usEmail;
    }

    public String getUsName() {
        return usName;
    }

    public void setUsName(String usName) {
        this.usName = usName;
    }

    public String getUsSurname() {
        return usSurname;
    }

    public void setUsSurname(String usSurname) {
        this.usSurname = usSurname;
    }

    public String getUsPassport() {
        return usPassport;
    }

    public void setUsPassport(String usPassport) {
        this.usPassport = usPassport;
    }

    public String getUsRole() {
        return usRole;
    }

    public void setUsRole(String usRole) {
        this.usRole = usRole;
    }

    public boolean isUsBan() {
        return usBan;
    }

    public void setUsBan(boolean usBan) {
        this.usBan = usBan;
    }

    public int getTariffId() {
        return tariffId;
    }

    public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
    }

    @Override
    public String toString() {
        return "User{" +
                "usLogin='" + usLogin + '\'' +
                ", usPassword='" + usPassword + '\'' +
                ", usName='" + usName + '\'' +
                ", usSurname='" + usSurname + '\'' +
                ", usEmail='" + usEmail + '\'' +
                ", usPassport='" + usPassport + '\'' +
                ", usRole='" + usRole + '\'' +
                ", usBan=" + usBan +
                ", tariff=" + tariffId +
                '}';
    }
}

