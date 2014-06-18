package enums;

/**
 *
 * @author bryan
 */
public enum LoanStatus {
    BANKRUPTCY("BANKRUPTCY"), CHARGED_OFF("CHARGED OFF"), COLLECTIONS("COLLECTIONS"),
    DEPOSITED("DEPOSITED"), OPEN("OPEN"), PAID("PAID");

    private String status;

    LoanStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
    
}
