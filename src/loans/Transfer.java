package loans;

import java.util.Date;

/**
 *
 * @author bryan
 */
public class Transfer {

    private int id;
    private Date transferDate;
    private int fromStation;
    private int toStation;
    private double amount;

    
    // getters
    public double getAmount() {
        return amount;
    }
    public int getId() {
        return id;
    }
    public int getFromStation() {
        return fromStation;
    }
    public int getToStation() {
        return toStation;
    }
    public Date getTransferDate() {
        return transferDate;
    }

  
    // setters
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setFromStation(int fromStation) {
        this.fromStation = fromStation;
    }
    public void setToStation(int toStation) {
        this.toStation = toStation;
    }
    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }
}
