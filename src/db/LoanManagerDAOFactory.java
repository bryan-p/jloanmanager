
/*
 * LoanManagerDAOFactory.java
 *
 * Created on June 26, 2010
 * 
 * @author: Bryan Payne
 */
package db;

import java.util.*;
import java.sql.*;

public class LoanManagerDAOFactory {

    private static Connection conn = null;
    private static final String url = "jdbc:mysql://localhost/loans",
            userId = "root",
            password = "test";

    private LoanManagerDAOFactory() {
    }

    /**
     * createConnection
     *
     * Opens a connection to the Loan Manager database.
     *
     * @return The Connection to the database
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection createConnection() throws ClassNotFoundException,
            SQLException {

        if (conn == null) {
            // initialize the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, userId, password);
            conn.setAutoCommit(false);
        }

        return conn;
    }

    public static BorrowerDAO getBorrowerDAO() {
        return new MySQLBorrowerDAO();
    }

    public static LoanDAO getLoanDAO() {
        return new MySQLLoanDAO();
    }

    public static PaymentDAO getPaymentDAO() {
        return new MySQLPaymentDAO();
    }

    public static GOPDAO getGOPDAO() {
        return new MySQLGOPDAO();
    }

    public static StationDAO getStationDAO() {
        return new MySQLStationDAO();
    }

    public static TransferDAO getTransferDAO() {
        return new MySQLTransferDAO();
    }

    public static void closeConnection() throws SQLException {
        conn.close();
    }

    public static void commit() throws SQLException {
        conn.commit();
    }

    public static void rollBack() throws SQLException {
        conn.rollback();
    }

    public ArrayList<String> getTable(String table) throws SQLException {
        /*
        Statement stmt = null;
        ResultSetMetaData rsmd = null;
        ResultSet rs = null;      
        String query = "select * from " + table;
        ArrayList<String> result = new ArrayList<String>();

        // run the query to get the result set from the table
        // this returns all rows/cells of the table. 
        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);
        rsmd = rs.getMetaData();

        int numberOfColumns = rsmd.getColumnCount();

        while (rs.next()) {
        for (int i = 1; i <= numberOfColumns; i++) {
        result.add( rs.getString(i) );
        }
        }

        stmt.close();
        conn.close();

        // return the result
        return result;

         */
        return new ArrayList();
    }
}
