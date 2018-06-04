package by.kamotskaya.internet_provider.entity;

/**
 * Entity class.
 *
 * @author Lena Kamotskaya
 */
public class User extends Entity {

    private String usLogin;
    private String usPassword;
    private String usName;
    private String usSurname;
    private String usEmail;
    private String usPassport;
    private String usRole;
    private boolean usBan;
    private int tId;

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

    public int getTId() {
        return tId;
    }

    public void setTId(int tId) {
        this.tId = tId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (usBan != user.usBan) return false;
        if (tId != user.tId) return false;
        if (usLogin != null ? !usLogin.equals(user.usLogin) : user.usLogin != null) return false;
        if (usPassword != null ? !usPassword.equals(user.usPassword) : user.usPassword != null) return false;
        if (usName != null ? !usName.equals(user.usName) : user.usName != null) return false;
        if (usSurname != null ? !usSurname.equals(user.usSurname) : user.usSurname != null) return false;
        if (usEmail != null ? !usEmail.equals(user.usEmail) : user.usEmail != null) return false;
        if (usPassport != null ? !usPassport.equals(user.usPassport) : user.usPassport != null) return false;
        return usRole != null ? usRole.equals(user.usRole) : user.usRole == null;
    }

    @Override
    public int hashCode() {
        int result = usLogin != null ? usLogin.hashCode() : 0;
        result = 31 * result + (usPassword != null ? usPassword.hashCode() : 0);
        result = 31 * result + (usName != null ? usName.hashCode() : 0);
        result = 31 * result + (usSurname != null ? usSurname.hashCode() : 0);
        result = 31 * result + (usEmail != null ? usEmail.hashCode() : 0);
        result = 31 * result + (usPassport != null ? usPassport.hashCode() : 0);
        result = 31 * result + (usRole != null ? usRole.hashCode() : 0);
        result = 31 * result + (usBan ? 1 : 0);
        result = 31 * result + tId;
        return result;
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
                ", tariff=" + tId +
                '}';
    }
}

