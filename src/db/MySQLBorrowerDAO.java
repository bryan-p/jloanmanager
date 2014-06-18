/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.*;
import java.util.ArrayList;
import loans.Borrower;
import enums.PayFrequency;

/**
 *
 * @author Bryan Payne
 * @version 0.1
 */
public class MySQLBorrowerDAO implements BorrowerDAO {

    public int insertBorrower(Borrower b) throws ClassNotFoundException,
            SQLException {
        Statement stmt = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        stmt = conn.createStatement();

        String query = "INSERT INTO borrower (fname, mname, lname, "
                + "pay_frequency) VALUES ('"
                + b.getFirstName() + "', '"
                + b.getMiddleName() + "', '"
                + b.getLastName() + "', '"
                + b.getPayFreq().name() +  "'"
                + ");";

        int result = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.getGeneratedKeys();

        if(rs.next())
            b.setCustNum(rs.getLong(1));

        stmt.close();

        return result;
    }

    public int removeBorrower(long borrowerNum) throws ClassNotFoundException,
            SQLException {
        Statement stmt = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        stmt = conn.createStatement();

        String query = "DELETE FROM borrower WHERE custNum = '" + borrowerNum +
                "';";

        int result = stmt.executeUpdate(query);
        stmt.close();

        return result;
    }

    public Borrower getBorrower(long borrowerNum)
            throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        Borrower cust = null;
        Connection conn = LoanManagerDAOFactory.createConnection();

        String query = "SELECT * FROM borrower WHERE custNum = '" +
                borrowerNum + "';";

        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);

        if (rs.next() != false) {
            cust = new Borrower();
            cust.setCustNum(rs.getLong(1));
            cust.setFirstName(rs.getString(2));
            cust.setMiddleName(rs.getString(3));
            cust.setLastName(rs.getString(4));
            cust.setPayFreq(PayFrequency.valueOf(rs.getString(5)));
        }

        rs.close();
        stmt.close();

        return cust;
    }

    public ArrayList<Borrower> getBorrower(String lastName)
            throws ClassNotFoundException, SQLException {
        ArrayList<Borrower> borrowerList = new ArrayList<Borrower>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = LoanManagerDAOFactory.createConnection();

        String query = "SELECT * FROM borrower WHERE lname RLIKE '" + lastName +
                "';";
                
        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);

        while (rs.next()) {
            Borrower cust = new Borrower();
            cust.setCustNum(rs.getLong(1));
            cust.setFirstName(rs.getString(2));
            cust.setMiddleName(rs.getString(3));
            cust.setLastName(rs.getString(4));
            cust.setPayFreq(PayFrequency.valueOf(rs.getString(5)));
            
            borrowerList.add(cust);
        }

        rs.close();
        stmt.close();

        return borrowerList;
    }

    public int updateBorrower(Borrower b) throws ClassNotFoundException,
            SQLException {
        Statement stmt = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        stmt = conn.createStatement();

        String query = "UPDATE borrower SET "
                + "fname = '" + b.getFirstName() + "', "
                + "mname = '" + b.getMiddleName() + "', "
                + "lname = '" + b.getLastName() + "', "
                + "pay_frequency = '" + b.getPayFreq().name() + "' "
                + "WHERE custNum = '" + b.getCustNum()
                + "';";

        int result = stmt.executeUpdate(query);
        stmt.close();
        

        return result;
    }

    public ArrayList<Borrower> getBorrowerRS() throws ClassNotFoundException,
            SQLException {

        ArrayList<Borrower> borrowerList = new ArrayList<Borrower>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        String query = "SELECT * FROM borrower;";

        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);

        while (rs.next()) {
            Borrower cust = new Borrower();
            cust.setCustNum(rs.getLong(1));
            cust.setFirstName(rs.getString(2));
            cust.setMiddleName(rs.getString(3));
            cust.setLastName(rs.getString(4));
            cust.setPayFreq(PayFrequency.valueOf(rs.getString(5)));
            
            borrowerList.add(cust);
        }

        rs.close();

        stmt.close();

        return borrowerList;
    }

    public ArrayList<Borrower> getBorrowerRS(String query)
            throws ClassNotFoundException, SQLException {
        ArrayList<Borrower> borrowerList = new ArrayList<Borrower>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = LoanManagerDAOFactory.createConnection();

        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);

        while (rs.next()) {
            Borrower cust = new Borrower();
            cust.setCustNum(rs.getLong(1));
            cust.setFirstName(rs.getString(2));
            cust.setMiddleName(rs.getString(3));
            cust.setLastName(rs.getString(4));
            cust.setPayFreq(PayFrequency.valueOf(rs.getString(5)));
            
            borrowerList.add(cust);
        }

        rs.close();
        stmt.close();

        return borrowerList;
    }
}
