/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import enums.LoanStatus;
import java.sql.*;
import java.util.ArrayList;
import loans.Loan;
import enums.LoanType;


/**
 *
 * @author bryan
 */
public class MySQLLoanDAO implements LoanDAO{

    public int insertLoan(Loan newLoan) throws ClassNotFoundException,
            SQLException {

       Statement stmt = null;
       Connection conn = LoanManagerDAOFactory.createConnection();
       stmt = conn.createStatement();

        // add code here to add loan to database
        String query = "INSERT INTO loan (origDate, dueDate, orig_principle, orig_fee,"
                + "principle, loan_fee, apr_fee, verification_fee, return_check_fee, "
                + "fee, balance, rate, status, type, custNum, cust_name) "
                + "VALUES('" +
                new Date(newLoan.getOrigDate().getTime()) + "','" +
                new Date(newLoan.getDueDate().getTime()) + "','" +
                newLoan.getOrigPrinciple() + "','" +
                newLoan.getOrigTotalFee() + "','" +
                newLoan.getPrinciple() + "','" +
                newLoan.getLoanFee() + "','" +
                newLoan.getAprFee() + "','" +
                newLoan.getVerificationFee() + "','" +
                newLoan.getReturnCheckFee() + "','" +
                newLoan.getTotalFee() + "','" +
                newLoan.getBalance() + "','" +
                newLoan.getRate() + "','" +
                newLoan.getStatus().name() + "','" +
                newLoan.getType().name() + "','" +
                newLoan.getCustNum() + "','" +
                newLoan.getCustName() +
                "');";

       int result = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
       ResultSet rs = stmt.getGeneratedKeys();

       if(rs.next())
           newLoan.setLoanNum(rs.getLong(1));
       
       stmt.close();

       return result;
    }

    public int removeLoan(long loanNum) throws ClassNotFoundException,
            SQLException {
        Statement stmt = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        stmt = conn.createStatement();

        String query = "DELETE FROM loan WHERE loanNum = '" + loanNum + "';";

        int result = stmt.executeUpdate(query);
        stmt.close();

        return result;
    }

    public Loan getLoan(long loanNum) throws ClassNotFoundException,
            SQLException {
        Loan loan = null;
        Statement stmt = null;
    	ResultSet rs = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        String query = "SELECT * FROM loan WHERE loanNum = '" + loanNum + "';";

    	stmt = conn.createStatement();
        rs = stmt.executeQuery(query);

        if (rs.next() != false) {
            loan = new Loan();
            loan.setLoanNum(rs.getLong(1));
            loan.setOrigDate(new Date(rs.getDate(2).getTime()));
            loan.setDueDate(new Date(rs.getDate(3).getTime()));
            loan.setOrigPrinciple(rs.getDouble(4));
            loan.setOrigTotalFee(rs.getDouble(5));
            loan.setType(LoanType.valueOf(rs.getString(15)));
            loan.setPrinciple(rs.getDouble(6));
            loan.setLoanFee(rs.getDouble(7));
            loan.setAprFee(rs.getDouble(8));
            loan.setVerificationFee(rs.getDouble(9));
            loan.setReturnCheckFee(rs.getDouble(10));
            loan.setTotalFee(rs.getDouble(11));
            loan.setBalance(rs.getDouble(12));
            loan.setRate(rs.getDouble(13));
            loan.setStatus(LoanStatus.valueOf(rs.getString(14)));
            loan.setCustNum(rs.getLong(16));
            loan.setCustName(rs.getString(17));
        }

        rs.close();

        stmt.close();
        
    	return loan;
    }

    public ArrayList<Loan> getLoansByBorrower(long custNum)
            throws ClassNotFoundException, SQLException{
        ArrayList<Loan> loanList = new ArrayList<Loan>();
        Loan loan = null;
        Statement stmt = null;
    	ResultSet rs = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        String query = "SELECT * FROM loan WHERE custNum = '" + custNum + "';";

    	stmt = conn.createStatement();
        rs = stmt.executeQuery(query);

        while (rs.next()) {
            loan = new Loan();
            loan.setLoanNum(rs.getLong(1));
            loan.setOrigDate(new Date(rs.getDate(2).getTime()));
            loan.setDueDate(new Date(rs.getDate(3).getTime()));
            loan.setOrigPrinciple(rs.getDouble(4));
            loan.setOrigTotalFee(rs.getDouble(5));
            loan.setType(LoanType.valueOf(rs.getString(15)));
            loan.setPrinciple(rs.getDouble(6));
            loan.setLoanFee(rs.getDouble(7));
            loan.setAprFee(rs.getDouble(8));
            loan.setVerificationFee(rs.getDouble(9));
            loan.setReturnCheckFee(rs.getDouble(10));
            loan.setTotalFee(rs.getDouble(11));
            loan.setBalance(rs.getDouble(12));
            loan.setRate(rs.getDouble(13));
            loan.setStatus(LoanStatus.valueOf(rs.getString(14)));
            loan.setCustNum(rs.getLong(16));
            loan.setCustName(rs.getString(17));

            loanList.add(loan);
        }

        rs.close();

        stmt.close();
        
    	return loanList;
    }

    public int updateLoan(Loan loan) throws ClassNotFoundException,
            SQLException {
        Statement stmt = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        stmt = conn.createStatement();

        String query = "UPDATE loan SET " +
                "origDate = '" + new Date(loan.getOrigDate().getTime()) + "', " +
                "dueDate = '" + new Date(loan.getDueDate().getTime()) + "', " +
                "orig_principle = '" + loan.getOrigPrinciple() + "', " +
                "orig_fee = '" + loan.getOrigTotalFee() + "', " +
                "principle = '" + loan.getPrinciple() + "', " +
                "loan_fee = '" + loan.getLoanFee() + "', " +
                "apr_fee = '" + loan.getAprFee() + "', " +
                "verification_fee = '" + loan.getVerificationFee() + "', " +
                "return_check_fee = '" + loan.getReturnCheckFee() + "', " +
                "fee = '" + loan.getTotalFee() + "', " +
                "balance = '" + loan.getBalance() + "', " +
                "rate = '" + loan.getRate() + "', " +
                "status = '" + loan.getStatus() + "', " +
                "type = '" + loan.getType().name() + "', " +
                "custNum = '" + loan.getCustNum() + "', " +
                "cust_name = '" + loan.getCustName() + "' " +
                "WHERE loanNum = '" + loan.getLoanNum() +
                "';";

        int result = stmt.executeUpdate(query);
        
        stmt.close();

        return result;
    }

    public ArrayList<Loan> getLoanRS() throws ClassNotFoundException, 
            SQLException {
        ArrayList<Loan> loanList = new ArrayList<Loan>();
        Loan loan = null;
        Statement stmt = null;
    	ResultSet rs = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        String query = "SELECT * FROM loan;";

    	stmt = conn.createStatement();
        rs = stmt.executeQuery(query);

        while (rs.next()) {
            loan = new Loan();
            loan.setLoanNum(rs.getLong(1));
            loan.setOrigDate(new Date(rs.getDate(2).getTime()));
            loan.setDueDate(new Date(rs.getDate(3).getTime()));
            loan.setOrigPrinciple(rs.getDouble(4));
            loan.setOrigTotalFee(rs.getDouble(5));
            loan.setType(LoanType.valueOf(rs.getString(15)));
            loan.setPrinciple(rs.getDouble(6));
            loan.setLoanFee(rs.getDouble(7));
            loan.setAprFee(rs.getDouble(8));
            loan.setVerificationFee(rs.getDouble(9));
            loan.setReturnCheckFee(rs.getDouble(10));
            loan.setTotalFee(rs.getDouble(11));
            loan.setBalance(rs.getDouble(12));
            loan.setRate(rs.getDouble(13));
            loan.setStatus(LoanStatus.valueOf(rs.getString(14)));
            loan.setCustNum(rs.getLong(16));
            loan.setCustName(rs.getString(17));

            loanList.add(loan);
        }

        rs.close();
        stmt.close();

    	return loanList;
    }

    public ArrayList<Loan> getLoanRS(String query) throws ClassNotFoundException,
            SQLException {
        ArrayList<Loan> loanList = new ArrayList<Loan>();
        Loan loan = null;
        Statement stmt = null;
    	ResultSet rs = null;
        Connection conn = LoanManagerDAOFactory.createConnection();

    	stmt = conn.createStatement();
        rs = stmt.executeQuery(query);

        while (rs.next()) {
            loan = new Loan();
            loan.setLoanNum(rs.getLong(1));
            loan.setOrigDate(new Date(rs.getDate(2).getTime()));
            loan.setDueDate(new Date(rs.getDate(3).getTime()));
            loan.setOrigPrinciple(rs.getDouble(4));
            loan.setOrigTotalFee(rs.getDouble(5));
            loan.setType(LoanType.valueOf(rs.getString(15)));
            loan.setPrinciple(rs.getDouble(6));
            loan.setLoanFee(rs.getDouble(7));
            loan.setAprFee(rs.getDouble(8));
            loan.setVerificationFee(rs.getDouble(9));
            loan.setReturnCheckFee(rs.getDouble(10));
            loan.setTotalFee(rs.getDouble(11));
            loan.setBalance(rs.getDouble(12));
            loan.setRate(rs.getDouble(13));
            loan.setStatus(LoanStatus.valueOf(rs.getString(14)));
            loan.setCustNum(rs.getLong(16));
            loan.setCustName(rs.getString(17));

            loanList.add(loan);
        }

        rs.close();
        stmt.close();

    	return loanList;
    }

}
