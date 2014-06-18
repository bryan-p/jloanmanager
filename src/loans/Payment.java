package loans;

import enums.PmtMethod;
import java.util.Date;

/**
 *
 * @author bryan
 */
public class Payment implements Comparable {

    private Date date;
    private long paymentID;
    private long loanNum;
    private double principleAmount;
    private double feeAmount;
    private double miscAmount;
    private PmtMethod pmtMthd;


    public Payment() {}
    public Payment(double principleAmount, double feeAmount, Date date,
            long loanNum, PmtMethod pmtMthd) {
        this.principleAmount = principleAmount;
        this.feeAmount = feeAmount;
        this.date = date;
        this.loanNum = loanNum;
        this.pmtMthd = pmtMthd;
    }





    // getter methods
    // --------------
    public double getFeeAmount() {
        return feeAmount;
    }
    public double getMiscAmount() {
        return miscAmount;
    }
    public PmtMethod getPmtMthd() {
        return pmtMthd;
    }
    public double getPrincipleAmount() {
        return principleAmount;
    }
    public long getLoanNum() {
        return loanNum;
    }
    public long getPaymentID() {
        return paymentID;
    }
    public Date getDate() {
        return date;
    }

    // setter methods
    // --------------
    public void setFeeAmount(double feeAmount) {
        this.feeAmount = feeAmount;
    }
    public void setMiscAmount(double miscAmount) {
        this.miscAmount = miscAmount;
    }
    public void setPmtMthd(PmtMethod pmtMthd) {
        this.pmtMthd = pmtMthd;
    }
    public void setPrincipleAmount(double principleAmount) {
        this.principleAmount = principleAmount;
    }
    public void setLoanNum(long loanNum) {
        this.loanNum = loanNum;
    }
    public void setPaymentID(long paymentID) {
        this.paymentID = paymentID;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public int compareTo(Object anotherPayment) throws ClassCastException {
        if (!(anotherPayment instanceof Payment)) {
            throw new ClassCastException();
        }

        return this.date.compareTo(((Payment) anotherPayment).getDate());

    }
}
