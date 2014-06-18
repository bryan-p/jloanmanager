package enums;

/**
 *
 * @author bryan
 */
public enum LoanType {
    PERSONAL("PERSONAL"), FIFTH_LOAN("5TH LOAN");

    private String desc;

    LoanType(String desc){
        this.desc = desc;
    }

    public String getDescription(){return desc;}
}
