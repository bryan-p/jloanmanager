package db;

import enums.PmtMethod;
import java.sql.*;
import java.util.ArrayList;
import loans.Payment;

/**
 *
 * @author bryan
 */
public class MySQLPaymentDAO implements PaymentDAO{

    public int insertPayment(Payment pmt) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        stmt = conn.createStatement();

        String query = "INSERT INTO payments (principle_amount, "
                + "fee_amount, misc_amount, paymentDate, loanNum, pmt_method) VALUES ('" +
                pmt.getPrincipleAmount() + "', '" +
                pmt.getFeeAmount() + "', '" +
                pmt.getMiscAmount() + "', '" +
                new Date(pmt.getDate().getTime()) + "', '" + 
                pmt.getLoanNum() + "', '" +
                pmt.getPmtMthd().toString() + 
                "');";

        int result = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.getGeneratedKeys();

        if(rs.next())
            pmt.setPaymentID(rs.getLong(1));

        stmt.close();

        return result;
    }

    public int removePayment(long loanNum, java.util.Date d)
            throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        stmt = conn.createStatement();

        String query = "DELETE FROM payments WHERE loanNum = '" + loanNum +
                "' AND paymentDate = '" + new Date(d.getTime()) +
                "';";

        int result = stmt.executeUpdate(query);
        stmt.close();

        return result;
    }

     public int removePayment(long paymentID) throws ClassNotFoundException,
             SQLException {
        Statement stmt = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        stmt = conn.createStatement();

        String query = "DELETE FROM payments WHERE id = '" + paymentID + "';";

        int result = stmt.executeUpdate(query);
        stmt.close();

        return result;
    }

    public Payment getPayment(long loanNum, java.util.Date d)
            throws ClassNotFoundException, SQLException {
        Payment pmt = null;
        Statement stmt = null;
    	ResultSet rs = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        String query = "SELECT * FROM payments WHERE loanNum = '" + loanNum +
                "' AND paymentDate = '" + new Date(d.getTime()) +
                "';";

    	stmt = conn.createStatement();
        rs = stmt.executeQuery(query);

         if (rs.next() != false) {
            pmt = new Payment();
            pmt.setPaymentID(rs.getLong(1));
            pmt.setPrincipleAmount(rs.getDouble(2));
            pmt.setFeeAmount(rs.getDouble(3));
            pmt.setMiscAmount(rs.getDouble(4));
            pmt.setDate(new Date(rs.getDate(5).getTime()));
            pmt.setLoanNum(rs.getLong(6));
            pmt.setPmtMthd(PmtMethod.valueOf(rs.getString(7)));
        }

        rs.close();
        stmt.close();

    	return pmt;
    }

    public Payment getPayment(long paymentID)
            throws ClassNotFoundException, SQLException {
        Payment pmt = null;
        Statement stmt = null;
    	ResultSet rs = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        String query = "SELECT * FROM payments WHERE id = '" + paymentID + "';";

    	stmt = conn.createStatement();
        rs = stmt.executeQuery(query);

         if (rs.next() != false) {
            pmt = new Payment();
            pmt.setPaymentID(rs.getLong(1));
            pmt.setPrincipleAmount(rs.getDouble(2));
            pmt.setFeeAmount(rs.getDouble(3));
            pmt.setMiscAmount(rs.getDouble(4));
            pmt.setDate(new Date(rs.getDate(5).getTime()));
            pmt.setLoanNum(rs.getLong(6));
            pmt.setPmtMthd(PmtMethod.valueOf(rs.getString(7)));
        }

        rs.close();
        stmt.close();

    	return pmt;
    }

    public ArrayList<Payment> getPaymentsByLoan(long loanNum)
            throws ClassNotFoundException, SQLException{
        ArrayList<Payment> pmtList = new ArrayList<Payment>();
        Payment pmt = null;
        Statement stmt = null;
    	ResultSet rs = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        String query = "SELECT * FROM payments WHERE loanNum = '" + loanNum +
                "';";

    	stmt = conn.createStatement();
        rs = stmt.executeQuery(query);

         while (rs.next() != false) {
            pmt = new Payment();
            pmt.setPaymentID(rs.getLong(1));
            pmt.setPrincipleAmount(rs.getDouble(2));
            pmt.setFeeAmount(rs.getDouble(3));
            pmt.setMiscAmount(rs.getDouble(4));
            pmt.setDate(new Date(rs.getDate(5).getTime()));
            pmt.setLoanNum(rs.getLong(6));
            pmt.setPmtMthd(PmtMethod.valueOf(rs.getString(7)));

            pmtList.add(pmt);
        }

        rs.close();
        stmt.close();

    	return pmtList;
    }

    public int updatePayment(Payment pmt)
            throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        stmt = conn.createStatement();

        String query = "UPDATE payments SET " +
                "principle_amount = '" + pmt.getPrincipleAmount() + "', '" +
                "fee_amount = '" + pmt.getFeeAmount() + "', '" +
                "misc_amount = '" + pmt.getMiscAmount() + "', '" +
                "pmt_method = '" + pmt.getPmtMthd() + "' " +
                "WHERE " +
                "loanNum = '" + pmt.getLoanNum() + "' " +
                "AND " +
                "paymentDate = '" + new Date(pmt.getDate().getTime()) +
                "';";

        int result = stmt.executeUpdate(query);
        stmt.close();
        
        return result;
    }

    public ArrayList<Payment> getPaymentRS() throws ClassNotFoundException,
            SQLException {
        ArrayList<Payment> pmtList = new ArrayList<Payment>();
        Payment pmt = null;
        Statement stmt = null;
    	ResultSet rs = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        String query = "SELECT * FROM payments;";

    	stmt = conn.createStatement();
        rs = stmt.executeQuery(query);

        while(rs.next()) {
            pmt = new Payment();
            pmt.setPaymentID(rs.getLong(1));
            pmt.setPrincipleAmount(rs.getDouble(2));
            pmt.setFeeAmount(rs.getDouble(3));
            pmt.setMiscAmount(rs.getDouble(4));
            pmt.setDate(new Date(rs.getDate(5).getTime()));
            pmt.setLoanNum(rs.getLong(6));
            pmt.setPmtMthd(PmtMethod.valueOf(rs.getString(7)));

            pmtList.add(pmt);
        }

        rs.close();
        stmt.close();

    	return pmtList;
    }

    public ArrayList<Payment> getPaymentRS(String query)
            throws ClassNotFoundException, SQLException {
        ArrayList<Payment> pmtList = new ArrayList<Payment>();
        Payment pmt = null;
        Statement stmt = null;
    	ResultSet rs = null;
        Connection conn = LoanManagerDAOFactory.createConnection();

    	stmt = conn.createStatement();
        rs = stmt.executeQuery(query);

        while(rs.next()) {
            pmt = new Payment();
            pmt.setPaymentID(rs.getLong(1));
            pmt.setPrincipleAmount(rs.getDouble(2));
            pmt.setFeeAmount(rs.getDouble(3));
            pmt.setMiscAmount(rs.getDouble(4));
            pmt.setDate(new Date(rs.getDate(5).getTime()));
            pmt.setLoanNum(rs.getLong(6));
            pmt.setPmtMthd(PmtMethod.valueOf(rs.getString(7)));

            pmtList.add(pmt);
        }

        rs.close();
        stmt.close();
        
    	return pmtList;
    }

}
