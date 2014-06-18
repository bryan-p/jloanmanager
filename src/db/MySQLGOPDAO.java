package db;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author bryan
 */
public class MySQLGOPDAO implements GOPDAO{

    public int insertGOP(java.util.Date d, double gop)
            throws ClassNotFoundException, SQLException {
        PreparedStatement pstmt = null;
        Statement stmt = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        stmt = conn.createStatement();

        String query = "INSERT INTO gop (date, principle) VALUES ('" +
                new Date(d.getTime()) + "', '" +
                gop +
                "');";

        int result = stmt.executeUpdate(query);

        stmt.close();

        return result;
    }

     public int removeGOP(java.util.Date d) throws ClassNotFoundException,
             SQLException {
        Statement stmt = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        stmt = conn.createStatement();

        String query = "DELETE FROM gop WHERE date = '" + new Date(d.getTime()) + "';";

        int result = stmt.executeUpdate(query);
        stmt.close();

        return result;
    }

    public Double getGOP(java.util.Date d)
            throws ClassNotFoundException, SQLException {
        Double gop = null;
        Statement stmt = null;
    	ResultSet rs = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        String query = "SELECT * FROM gop WHERE date = '" + new Date(d.getTime()) +
                "';";

    	stmt = conn.createStatement();
        rs = stmt.executeQuery(query);

         if (rs.next() != false) 
            gop = new Double(rs.getDouble(2));

        rs.close();
        stmt.close();

    	return gop;
    }

    public int updateGOP(java.util.Date d, double gop)
            throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        stmt = conn.createStatement();

        String query = "UPDATE gop SET " +
                "principle = '" + gop + "' " +
                "WHERE " +
                "date = '" + new Date(d.getTime()) +
                "';";

        int result = stmt.executeUpdate(query);
        stmt.close();
        
        return result;
    }

    public ArrayList<Double> getGOPRS() throws ClassNotFoundException,
            SQLException {
        ArrayList<Double> principleList = new ArrayList<Double>();
        Double gop;
        Statement stmt = null;
    	ResultSet rs = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        String query = "SELECT * FROM gop;";

    	stmt = conn.createStatement();
        rs = stmt.executeQuery(query);

        while(rs.next()) {
            gop = new Double(rs.getDouble(1));
           
            principleList.add(gop);
        }

        rs.close();
        stmt.close();

    	return principleList;
    }

    public ArrayList<Double> getGOPRS(String query)
            throws ClassNotFoundException, SQLException {
        ArrayList<Double> gopList = new ArrayList<Double>();
        Double gop;
        Statement stmt = null;
    	ResultSet rs = null;
        Connection conn = LoanManagerDAOFactory.createConnection();

    	stmt = conn.createStatement();
        rs = stmt.executeQuery(query);

        while(rs.next()) {
            gop = new Double(rs.getDouble(1));

            gopList.add(gop);
        }

        rs.close();
        stmt.close();

    	return gopList;
    }

}
