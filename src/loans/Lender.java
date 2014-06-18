package loans;

import db.*;
import enums.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;
import java.io.*;
import java.io.IOException;

/*
 * Author: Bryan Payne
 * Version 0.1
 * 
 * Lender.java
 * 
 * This class is the driver class.  All loans are processed in this class. 
 * PaydayLoanGUI makes use of this class in order to process events
 */
public class Lender {

    // constants used for updateGOP method calls
    private static final boolean ADD = true;
    private static final boolean SUB = false;
    private DateFormat df;
    private Date curDate;
    private ArrayList<Borrower> borrowerList;
    private ArrayList<Loan> loanList;
    private ArrayList<Payment> paymentList;
    private ArrayList<Station> stationList;
    private ArrayList<StationBalance> stationBalanceList;
    private ArrayList<Transfer> transferList;
    private Loan curLoan;
    private Borrower curBorrower;
    private Payment curPayment;
    private BorrowerDAO dbBorrower;
    private LoanDAO dbLoan;
    private PaymentDAO dbPayment;
    private GOPDAO dbGOP;
    private StationDAO dbStation;
    private TransferDAO dbTransfer;
    private Station curStation;
    private StationBalance curStationBalance;

    /**
     * Default constructor
     * @throws ParseException 
     */
    public Lender() throws ParseException {

        df = DateFormat.getDateInstance(DateFormat.SHORT);
        String curDateStr = df.format(new Date());
        curDate = df.parse(curDateStr);

        dbBorrower = LoanManagerDAOFactory.getBorrowerDAO();
        dbLoan = LoanManagerDAOFactory.getLoanDAO();
        dbPayment = LoanManagerDAOFactory.getPaymentDAO();
        dbGOP = LoanManagerDAOFactory.getGOPDAO();
        dbStation = LoanManagerDAOFactory.getStationDAO();
        dbTransfer = LoanManagerDAOFactory.getTransferDAO();
    }

    public void fixGOPHistory() {
        File fd = new File("c:\\java_programs\\loanmanager\\gop.csv");
        String line = null;
        df.setLenient(true);
        Date date = null;
        double amount = 0.0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(fd));
            while ((line = br.readLine()) != null) {
                System.out.println(line);

                String tokens[] = line.split(",");
                System.out.println(tokens.length);

                date = df.parse(tokens[0]);
                amount = Double.parseDouble(tokens[1]);


                System.out.println(date);
                System.out.println(amount);

                System.out.println(dbGOP.updateGOP(date, amount));
            }
            LoanManagerDAOFactory.commit();
        } catch (FileNotFoundException ex) {
            System.out.println("fnfe");
        } catch (IOException ioe) {
            System.out.println("ioe");
        } catch (ParseException pe) {
            System.out.println("pe");
        } catch (ClassNotFoundException cnfe) {
        } catch (SQLException sqle) {
        }

    }

    public void changeStatus(LoanStatus status) throws SQLException,
            ClassNotFoundException {

        curLoan.setStatus(status);

        if (status.equals(LoanStatus.OPEN)) {
            this.updateGOP(curDate, curLoan.getPrinciple(), ADD);
        } else {
            this.updateGOP(curDate, curLoan.getPrinciple(), SUB);
        }

        dbLoan.updateLoan(curLoan);
    }

    public void clearBorrower() {
        curBorrower = null;
        curLoan = null;
        loanList = null;
    }

    public void closeStation() throws SQLException,
            ClassNotFoundException {
        curStation.setIsOpen(false);
        curStationBalance.setEndBal(curStation.getCurBal());
        curStationBalance.setCloseDate(curDate);

        dbStation.updateStationBalance(curStationBalance);
        dbStation.updateStation(curStation);
        curStation = null;
    }

    public void deletePayment(long paymentID)
            throws SQLException, ClassNotFoundException {
        Payment pmt = dbPayment.getPayment(paymentID);

        if (pmt != null) {
            double prinAmount = pmt.getPrincipleAmount();
            double feeAmount = pmt.getFeeAmount();
            double totalAmount = feeAmount + prinAmount;

            if (prinAmount > 0) 
                curLoan.setPrinciple(prinAmount + curLoan.getPrinciple());
            
            if(feeAmount > 0)
                curLoan.setTotalFee(feeAmount + curLoan.getTotalFee());

            curLoan.setBalance(curLoan.getPrinciple() + curLoan.getTotalFee());
            
            dbLoan.updateLoan(curLoan);
            curStation.setCurBal(curStation.getCurBal() - totalAmount);
            dbStation.updateStation(curStation);
            updateGOP(curDate, prinAmount, true);
            dbPayment.removePayment(paymentID);
        }
    }

    public void depositPDLCheck(Date payDate) throws SQLException, ClassNotFoundException {
        double prinPayment = curLoan.getPrinciple();
        double feePayment = curLoan.getTotalFee();
        curLoan.setStatus(LoanStatus.DEPOSITED);

        Payment pmt = new Payment(prinPayment, feePayment, payDate,
                curLoan.getLoanNum(), PmtMethod.DEPOSIT);

        updateGOP(payDate, prinPayment, false);
        curStation.setCurBal(curStation.getCurBal() + prinPayment + feePayment);

        curLoan.setPrinciple(0);
        curLoan.setTotalFee(0);
        curLoan.setBalance(0);

        dbLoan.updateLoan(curLoan);
        dbPayment.insertPayment(pmt);
        dbStation.updateStation(curStation);

    }

    /**
     * Returns a Borrower object specified by borrowerNum.
     *
     * @param borrowerNum customer number
     * @return Borrower with given customer number or null if no Borrower exists
     * with that number
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Borrower getBorrower(long borrowerNum)
            throws SQLException, ClassNotFoundException {
        return dbBorrower.getBorrower(borrowerNum);
    }// end getBorrower

    /**
     * getBorrowerList
     *
     * Get an array list of all borrowers in the system
     *
     * returns an ArrayList of all borrowers or empty list if no Borrowers exist
     * @return ArrayList of Borrower objects
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Borrower> getBorrowerList()
            throws SQLException, ClassNotFoundException {
        borrowerList = dbBorrower.getBorrowerRS();
        Collections.sort(borrowerList);

        return borrowerList;
    }

    /**
     * Returns all borrowers whose last name matches lastName.
     *
     * @param lastName borrowers last lastName
     * @return list of all Borrowers, if any, whose last lastName matches
     * lastName.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Borrower> getBorrowerList(String lastName)
            throws SQLException, ClassNotFoundException {
        borrowerList = dbBorrower.getBorrower(lastName);
        Collections.sort(borrowerList);

        return borrowerList;
    }// end getBorrower

    public Borrower getCurBorrower() {
        return curBorrower;
    }

    public Date getCurDate() {
        return curDate;
    }

    public Loan getCurLoan() {
        return curLoan;
    }

    public Payment getCurPayment() {
        return curPayment;
    }

    public Station getCurStation() {
        return curStation;
    }

    public StationBalance getCurStationBalance() {
        return curStationBalance;
    }

    public Double getGOPByDate(Date gopDate)
            throws SQLException, ClassNotFoundException {
        return dbGOP.getGOP(gopDate);
    }

    /**
     * Returns the Loan object that has the given loan number.
     *
     * @param loanNum loanList number
     * @return Loan object with loanNum or null if no match is found
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Loan getLoan(long loanNum) throws SQLException,
            ClassNotFoundException {
        return dbLoan.getLoan(loanNum);
    }

    /**
     * returns the list of loans currently loaded
     *
     * @return List of all loans or empty list if none exist
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Loan> getLoanList() throws SQLException,
            ClassNotFoundException {
        loanList = dbLoan.getLoanRS();
        Collections.sort(loanList);

        return loanList;
    }

    /**
     * Returns the list of Loans that belong to the borrower with the given
     * loan number.
     *
     * @param borrowerNum customer number of the borrower
     * @return List of all loans made by Borrower with borrowerNum
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Loan> getLoanList(long borrowerNum) throws SQLException,
            ClassNotFoundException {
        loanList = dbLoan.getLoansByBorrower(borrowerNum);
        Collections.sort(loanList);

        return loanList;
    }

    public ArrayList<Loan> getLoansByDateRange(Date fromDate, Date toDate) throws SQLException,
            ClassNotFoundException {
        java.sql.Date begDate = new java.sql.Date(fromDate.getTime());
        java.sql.Date endDate = new java.sql.Date(toDate.getTime());

        String query = "SELECT * FROM loan WHERE origDate BETWEEN '" + begDate
                + "' AND '" + endDate + "' ORDER BY origDate;";

        ArrayList<Loan> loanList = dbLoan.getLoanRS(query);

        return loanList;
    }

    public ArrayList<Loan> getOpenLoanList() throws SQLException,
            ClassNotFoundException {
        ArrayList<Loan> loans = dbLoan.getLoanRS();
        ArrayList<Loan> openLoanList = new ArrayList<Loan>();

        for (Loan loan : loans) {
            if (loan.getStatus().equals(LoanStatus.OPEN)) {
                openLoanList.add(loan);
            }
        }
        return openLoanList;
    }

    public ArrayList<Loan> getPastDueLoanList() throws SQLException,
            ClassNotFoundException {
        ArrayList<Loan> loans = dbLoan.getLoanRS();
        ArrayList<Loan> pastDueLoanList = new ArrayList<Loan>();

        for (Loan loan : loans) {
            if (curDate.after(loan.getDueDate())
                    && loan.getStatus().equals(LoanStatus.OPEN)) {
                pastDueLoanList.add(loan);
            }
        }

        return pastDueLoanList;
    }

    /**
     * Returns the list of payments made on the Loan with the given loan number.
     *
     * @param loanNum Loan number of the loan
     * @return List of Payments on Loan with loanNum
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Payment> getPaymentList(long loanNum) throws SQLException,
            ClassNotFoundException {
        paymentList = dbPayment.getPaymentsByLoan(loanNum);
        Collections.sort(paymentList);

        return paymentList;
    }

    public ArrayList<Payment> getPaymentsByDateRange(Date fromDate, Date toDate) throws SQLException,
            ClassNotFoundException {
        java.sql.Date begDate = new java.sql.Date(fromDate.getTime());
        java.sql.Date endDate = new java.sql.Date(toDate.getTime());

        String query = "SELECT * FROM payments WHERE paymentDate BETWEEN '" + begDate
                + "' AND '" + endDate + "' ORDER BY paymentDate;";

        ArrayList<Payment> pmtList = dbPayment.getPaymentRS(query);

        return pmtList;
    }

    public StationBalance getStationBalance(int stationNum, Date date) throws SQLException,
            ClassNotFoundException {
        return dbStation.getStationBalance(stationNum, date);
    }

    public Station getStation(int stationNum) throws SQLException,
            ClassNotFoundException {
        return dbStation.getStation(stationNum);
    }

    public ArrayList<Station> getStationList() throws SQLException,
            ClassNotFoundException {
        stationList = dbStation.getStationRS();
        Collections.sort(stationList);

        return stationList;
    }

    public ArrayList<Station> getStationBalanceList() throws SQLException,
            ClassNotFoundException {
        stationBalanceList = dbStation.getStationBalanceRS();
        Collections.sort(stationBalanceList);

        return stationList;
    }

    /**
     * Updates the Good Outstanding Principle (GOP) for the merchant. This value
     * is the sum of the principle on all loans in the database.
     *
     * @return updated value of good outstanding principle
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public double loadGOP() throws SQLException, ClassNotFoundException {
        ArrayList<Loan> loans = getLoanList();
        double gop = 0.0;

        for (Loan l : loans) {
            if (l.getStatus().equals(LoanStatus.OPEN)) {
                gop += l.getPrinciple();
            }
        }

        return gop;
    }

    public void logInOnStation(int stationNum, boolean isOpen) throws SQLException,
            ClassNotFoundException {
        curStation = dbStation.getStation(stationNum);

        if (isOpen) {
            curStationBalance = dbStation.getStationBalance(stationNum, curDate);
        } else {
            curStationBalance = new StationBalance();

            curStationBalance.setStationNum(stationNum);
            curStationBalance.setOpenDate(curDate);
            curStationBalance.setBegBal(curStation.getCurBal());
            curStationBalance.setEndBal(curStation.getCurBal());

            curStation.setIsOpen(true);

            dbStation.updateStation(curStation);
            dbStation.insertStationBalance(curStationBalance);
        }
    }

    /**
     * Applies a payment to a loan
     *
     * @param payment payment amount
     * @param loan
     * @param curCust
     * @param payDate current date
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void makePayment(double payment, Date payDate, boolean isEarlyPIF)
            throws SQLException, ClassNotFoundException {

        double balance;

        if (curLoan != null && payDate != null) {
            Date origDate = curLoan.getOrigDate();
            Date dueDate = curLoan.getDueDate();
            double principle = curLoan.getPrinciple();
            double fee = curLoan.getTotalFee();
            balance = curLoan.getBalance();
            double rate = curLoan.getRate();
            LoanStatus status = curLoan.getStatus();
            LoanType type = curLoan.getType();

            // these two values hold principle and fee payment breakdown
            double prinPayment = 0;
            double feePayment = fee;

            if (type.equals(LoanType.FIFTH_LOAN)) {
                if (isEarlyPIF) {
                    //pro-rate the fee first
                    int numDaysOut = (int) ((payDate.getTime() - origDate.getTime()) / 86400000);
                    int duration = curLoan.getDuration();
                    double proRatedFee = (fee / duration) * numDaysOut;

                    System.out.println("principle: " + principle
                            + "fee: " + fee
                            + "# days out: " + numDaysOut + "\n"
                            + "duration: " + duration + "\n"
                            + "pro-rated fee: " + proRatedFee);

                    feePayment = proRatedFee;
                    prinPayment = principle;

                    curLoan.setPrinciple(0.0);
                    curLoan.setTotalFee(0);
                    curLoan.setBalance(0);

                    updateGOP(payDate, prinPayment, false);

                } else {
                    if (payment > fee) {
                        prinPayment = payment - fee;

                        principle -= prinPayment;
                        fee = 0.0;

                        updateGOP(payDate, prinPayment, false);
                        curLoan.setPrinciple(principle);
                    } else {
                        feePayment = payment;
                        fee -= payment;
                    }

                    balance = principle + fee;
                    curLoan.setTotalFee(fee);
                    curLoan.setBalance(balance);
                }
            } else {
                if (payment != fee) {
                    if (payment > fee) {
                        prinPayment = payment - fee;

                        principle -= prinPayment;
                        fee = principle * rate;

                        updateGOP(payDate, prinPayment, false);
                        curLoan.setPrinciple(principle);
                    } else {
                        feePayment = payment;

                        // real fee doesn't include misc. fees that are added,
                        double realFee = principle * rate;

                        if (fee > realFee) {
                            fee = (fee - payment) + realFee;
                        } else {
                            fee -= payment;
                        }
                    }

                    balance = principle + fee;
                    curLoan.setTotalFee(fee);
                    curLoan.setBalance(balance);
                } else {
                    curLoan.setTotalFee(principle * rate);
                    curLoan.setBalance(principle += principle * rate);
                }

                if (curLoan.getBalance() > 0) {
                    Calendar newDueDateCal = Calendar.getInstance();
                    newDueDateCal.setTime(dueDate);
                    newDueDateCal.add(Calendar.DAY_OF_MONTH,
                            curBorrower.getPayFreq().getDuration());
                    dueDate.setTime(newDueDateCal.getTimeInMillis());
                    curLoan.setDueDate(dueDate);
                }
            }

            if (curLoan.getBalance() < 1) {
                curLoan.setStatus(LoanStatus.PAID);
            }

            Payment pmt = new Payment(prinPayment, feePayment, payDate,
                    curLoan.getLoanNum(), PmtMethod.CASH);

            curStation.setCurBal(curStation.getCurBal() + prinPayment + feePayment);

            dbLoan.updateLoan(curLoan);
            dbPayment.insertPayment(pmt);
            dbStation.updateStation(curStation);

        }
    }

    public void markCheckReturned() throws SQLException, ClassNotFoundException {
        double returnChkFee = curLoan.getReturnCheckFee();
        double origPrin = curLoan.getOrigPrinciple();
        double origFee = curLoan.getOrigTotalFee();

        curLoan.setReturnCheckFee(returnChkFee + Loan.RET_CHECK_FEE_AMOUNT);
        curLoan.setStatus(LoanStatus.OPEN);

        // restore balance and fee based on previous payments
        double totalPmts = 0.0;
        double prinPmts = 0.0;
        ArrayList<Payment> pmtList = dbPayment.getPaymentsByLoan(curLoan.getLoanNum());
        for (Payment pmt : pmtList) {
            if (pmt.getPmtMthd().equals(PmtMethod.CASH)) {
                totalPmts += pmt.getFeeAmount() + pmt.getMiscAmount() + pmt.getPrincipleAmount();
                prinPmts += pmt.getPrincipleAmount();
            }
        }

        curLoan.setPrinciple(origPrin - prinPmts);
        curLoan.setBalance((origPrin + origFee + returnChkFee) - totalPmts);

        updateGOP(curDate, curLoan.getPrinciple(), true);
        dbLoan.updateLoan(curLoan);
    }

    /**
     * Adds a new customer to the database and sets the current borrower to
     * the new borrower;
     *
     * @param fName fist name
     * @param mName middle name
     * @param lName last name
     * @param suffix suffix
     * @param street1
     * @param street2
     * @param city
     * @param state
     * @param zip
     * @param licenseNum
     * @param payFreq
     * @param bType
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Borrower newBorrower(String fName, String mName, String lName,
            PayFrequency payFreq) throws SQLException,
            ClassNotFoundException {
        curBorrower = new Borrower(fName, mName, lName, payFreq);

        dbBorrower.insertBorrower(curBorrower);

        return curBorrower;
    }

    /**
     * newLoan
     *
     * creates a new Loan and adds it to the database.  The new loan is set as
     * the current loan.
     *
     * @param custNum id of customer with which this loanList will be attached
     * to.
     * @param principle amount borrowed
     * @param origDate origination date of the loan
     * @param dueDate date loan is due to be paid in full
     * @param rate percentage of fee on principle
     * @return the new Loan object.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Loan newLoan(long custNum, double principle, Date origDate,
            Date dueDate, double rate, LoanType type) throws SQLException,
            ClassNotFoundException {
        curLoan = new Loan(principle, origDate, dueDate, rate, type, custNum);
        curLoan.setCustName(curBorrower.getFullName());
        curLoan.setStatus(LoanStatus.OPEN);

        dbLoan.insertLoan(curLoan);

        curStation.setCurBal(curStation.getCurBal() - principle);
        dbStation.updateStation(curStation);

        Double gop = dbGOP.getGOP(origDate);
        if (gop == null) {
            gop = new Double(loadGOP());
            dbGOP.insertGOP(origDate, gop);
        } else {
            gop += principle;
            dbGOP.updateGOP(origDate, gop);
        }

        return curLoan;
    }

    public void reUpLoan(double principle, Date reUpDate) throws ClassNotFoundException, SQLException {
        if (curLoan.getType().equals(LoanType.PERSONAL)) {
            curLoan.setPrinciple(curLoan.getPrinciple() + principle);
            curLoan.calculateFee();

            curStation.setCurBal(curStation.getCurBal() - principle);

            dbLoan.updateLoan(curLoan);
            dbStation.updateStation(curStation);
            this.updateGOP(reUpDate, principle, true);
        }
    }

    public void setCurBorrower(Borrower curBorrower) {
        this.curBorrower = curBorrower;
    }

    public void setCurDate(Date curDate) {
        this.curDate = curDate;
    }

    public void setCurLoan(Loan curLoan) {
        this.curLoan = curLoan;
    }

    public void setCurPayment(Payment curPayment) {
        this.curPayment = curPayment;
    }

    public void setCurStation(Station station) {
        this.curStation = station;
    }

    public void setCurStationBalance(StationBalance stBal) {
        curStationBalance = stBal;
    }

    public int updateBorrower(String fName, String mName, String lName, PayFrequency payFreq)
            throws SQLException, ClassNotFoundException {
        curBorrower.setFirstName(fName);
        curBorrower.setMiddleName(mName);
        curBorrower.setLastName(lName);
        curBorrower.setPayFreq(payFreq);

        return dbBorrower.updateBorrower(curBorrower);
    }

    public void updateDueDate(Date newDate) {
        curLoan.setDueDate(newDate);
    }

    /**
     * Updates the fee and balance of the given loan.
     *
     * @param newFee the new fee to set the loan to
     * @param loan the loan to update the fee on
     */
    public void updateFee(double newFee) {
        curLoan.setTotalFee(newFee);
        curLoan.setBalance(curLoan.getPrinciple() + newFee);
    }

    private int updateGOP(Date d, double amount, boolean isAdded) throws SQLException,
            ClassNotFoundException {
        int result = -1;

        Double gop = dbGOP.getGOP(d);

        if (gop != null) {
            if (isAdded) {
                gop += amount;
            } else {
                gop -= amount;
            }

            result = dbGOP.updateGOP(d, gop);
        } else {

            gop = new Double(loadGOP());

            if (isAdded) {
                gop += amount;
            } else {
                gop -= amount;
            }

            result = dbGOP.insertGOP(d, gop);
        }

        return result;
    }

    /**
     * Updates all the values of the given loan.
     *
     * @param loan the loan to update
     * @return result of the SQL operation
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int updateLoan() throws SQLException,
            ClassNotFoundException {
        return dbLoan.updateLoan(curLoan);
    }

    /**
     * Updates the Net In of the given loan.  This is the dollar amount of
     * payments made greater than the original principle of the loan.
     *
     * @param loanNum number of the loan
     * @return amount of net profit made on this loan.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public double updateNetIn() throws SQLException,
            ClassNotFoundException {
        double net = 0;

        ArrayList<Payment> pmtList = getPaymentList(curLoan.getLoanNum());

        double total = 0;
        for (Payment pmt : pmtList) {
            total += pmt.getPrincipleAmount() + pmt.getFeeAmount();
        }

        if ((net = total - curLoan.getOrigPrinciple()) <= 0) {
            return 0;
        }

        return net;
    }

    /**
     * Updates the principle on a given loan.
     *
     * @param newPrinciple the new principle of the loan.
     * @param loan the loan to update the principle on.
     */
    public void updatePrinciple(double newPrinciple) {
        curLoan.setPrinciple(newPrinciple);
        curLoan.calculateFee();
        curLoan.setBalance(newPrinciple + curLoan.getTotalFee());
    }

    /**
     * Updates the station number in the database.
     *
     * @param stationNum station number.
     * @return result of db operation
     */
    public int updateStationNum(int stationNum) throws ClassNotFoundException,
            SQLException {
        Station st = dbStation.getStation(stationNum);

        st.setStationNum(stationNum);

        return dbStation.updateStation(st);
    }

    /**
     * Updates the station description in the database.
     *
     * @param desc description of the station
     * @return result of db operation
     */
    public int updateStationNum(int stationNum, String desc) throws ClassNotFoundException,
            SQLException {
        Station st = dbStation.getStation(stationNum);

        st.setDescription(desc);

        return dbStation.updateStation(st);
    }
}// end class Lender

