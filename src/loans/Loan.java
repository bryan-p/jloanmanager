package loans;

import enums.LoanStatus;
import enums.LoanType;
import java.util.Date;

/**
 * @author Bryan Payne
 * @version 0.1
 * 
 * This class is an instance of a single loan. It tracks the payment history
 * made on this loan.
 *
 */
public class Loan implements Comparable<Loan> {
    
    public static final double RET_CHECK_FEE_AMOUNT = 5.00;

    private Date origDate; // date loan was first taken out
    private Date dueDate; // contract due date
    private LoanStatus status; // paid, open, overdue
    private LoanType type; // 5th loan or personal
    private double origPrinciple; // initial principle amount
    private double origTotalFee; // initial fees
    private double principle; // principle amount loaned out
    private double totalFee; // fees on principle borrowed
    private double aprFee; // APR fee
    private double loanFee; // Loan fee
    private double verificationFee; // database verification fee
    private double returnCheckFee; // fee for returned check
    private double rate; // totalFee percentage
    private double balance; // total balance owed (principle + totalFee)
    private long loanNum; // loan number
    private long custNum; // customer number of attached customer
    private String custName; // full name of the customer attached to this loan

    /**
     * Default do nothing constructor
     */
    public Loan() {
    }

    /**
     * Default Constructor
     *
     * @param loanNum - internal loan number to track this loan
     * @param principle - amount of cash being loaned out
     * @param origDate - date loan is taken out
     * @param dueDate - date loan is due for payment
     * @param rate - rate of interest on this loan
     */
    public Loan(double principle, Date OrigDate, Date dueDate,
            double rate, LoanType type, long custNum) {

        // set loan information
        this.origPrinciple = (double) ((int) principle);
        this.principle = origPrinciple;
        this.origDate = OrigDate;
        this.dueDate = dueDate;
        this.rate = rate;
        this.custNum = custNum;
        this.type = type;

        status = LoanStatus.OPEN;

        if (type.equals(LoanType.FIFTH_LOAN)) {
            aprFee = (((principle * .36) / 360) * getDuration());
            loanFee = principle * .2;
            verificationFee = 5;
            totalFee = aprFee + loanFee + verificationFee;
            balance = principle + totalFee;
        } else {
            calculateFee();
            balance = (double) ((int) (principle + totalFee));
        }

        origTotalFee = totalFee;
    }// end constructor

    // setter methods
    // ******************************************
    public void setOrigDate(Date date) {
        origDate = date;
    }

    public void setDueDate(Date date) {
        dueDate = date;
    }

    public void setOrigPrinciple(double origPrinciple) {
        this.origPrinciple = (double) ((int) origPrinciple);
    }

    public void setOrigTotalFee(double origTotalFee) {
        this.origTotalFee = origTotalFee;
    }

    public void setPrinciple(double principle) {
        if (type.equals(LoanType.FIFTH_LOAN)) {
            this.principle = principle;
        } else {
            this.principle = (double) ((int) principle);
        }
    }

    public void setBalance(double balance) {
        if (type.equals(LoanType.FIFTH_LOAN)) {
            this.balance = balance;
        } else {
            this.balance = (double) ((int) balance);
        }
    }

    public void setAprFee(double aprFee) {
        this.aprFee = aprFee;
    }

    public void setLoanFee(double loanFee) {
        this.loanFee = loanFee;
    }

    public void setVerificationFee(double verificationFee) {
        this.verificationFee = verificationFee;
    }

    public void setTotalFee(double fee) {
        if (type.equals(LoanType.FIFTH_LOAN)) {
            this.totalFee = fee;
        } else {
            this.totalFee = (double) ((int) fee);
        }
    }

    public void setLoanNum(long loanNum) {
        this.loanNum = loanNum;
    }

    public void setReturnCheckFee(double returnCheckFee) {
        this.returnCheckFee = returnCheckFee;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public void setType(LoanType type) {
        this.type = type;
    }

    public void setRate(double rate) {

        // default rate is 15%
        if (rate < 0) {
            rate = 0.15f;
        } else {
            this.rate = rate;
        }
    }

    public void setCustNum(long custNum) {
        this.custNum = custNum;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    // getter methods
    // *****************************************
    public Date getOrigDate() {
        return origDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public final int getDuration() {
        return (int) ((dueDate.getTime() - origDate.getTime()) / 86400000);
    }

    public double getOrigPrinciple() {
        return origPrinciple;
    }

    public double getOrigTotalFee() {
        return origTotalFee;
    }

    public double getPrinciple() {
        return principle;
    }

    public long getLoanNum() {
        return loanNum;
    }

    public double getAprFee() {
        return aprFee;
    }

    public double getLoanFee() {
        return loanFee;
    }

    public double getVerificationFee() {
        return verificationFee;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public double getBalance() {
        return balance;
    }

    public double getRate() {
        return rate;
    }

    public double getReturnCheckFee() {
        return returnCheckFee;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public LoanType getType() {
        return type;
    }

    public long getCustNum() {
        return custNum;
    }

    public String getCustName() {
        return custName;
    }

    public final void calculateFee() {
        totalFee = (double) ((int) (principle * rate));
    }

    public String toString() {
        String str = "";

        str = "\n" + loanNum + "\n" + "Origination: " + origDate + "\n";
        str = "Due: " + dueDate + "\n";
        str = "Principle: " + principle + "\n";
        str = "Loan Fee: " + totalFee + "\n";
        str = "Balance: " + balance + "\n";
        str = "Rate: " + rate + "\n";
        str = "Status: " + status + "\n";

        return str;
    }

    public int compareTo(Loan anotherLoan) {

        return ((Long) anotherLoan.getLoanNum()).compareTo(this.getLoanNum());
        //return this.origDate.compareTo(anotherLoan.origDate);
    }
}
