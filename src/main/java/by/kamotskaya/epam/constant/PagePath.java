package by.kamotskaya.epam.constant;

/**
 * @author Lena Kamotskaya
 */
public enum PagePath {
    INDEX("index.jsp"),
    LOGIN("jsp/login.jsp"),
    HOME("jsp/home.jsp"),
    ERROR("jsp/error.jsp");

    private String value;

    PagePath(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
