package enums;

/**
 *
 * @author bryan
 */
public enum PmtMethod {
    CASH("CASH"), DEPOSIT("DEPOSIT");

    private String method;

    PmtMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }
}
