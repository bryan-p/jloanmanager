package db;

import java.sql.*;
import java.util.ArrayList;
import loans.Transfer;

/**
 *
 * @author bryan
 */
public class MySQLTransferDAO implements TransferDAO{

    public int insertTransfer(Transfer newTransfer) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        stmt = conn.createStatement();

        String query = "INSERT INTO transfers (date, from_station, to_station, amount) "
                + "VALUES ('" +
                new Date(newTransfer.getTransferDate().getTime()) + "', '" +
                newTransfer.getFromStation() + "', '" +
                newTransfer.getToStation() + "', '" +
                newTransfer.getAmount() +
                "');";

        int result = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.getGeneratedKeys();

        if(rs.next())
            newTransfer.setId(rs.getInt(1));

        stmt.close();

        return result;
    }

    public int removeTransfer(int idNum) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        stmt = conn.createStatement();

        String query = "DELETE FROM transfers WHERE id = '" + idNum +
                "';";

        int result = stmt.executeUpdate(query);
        stmt.close();

        return result;
    }

    public ArrayList<Transfer> getTransfers(java.util.Date begDate, java.util.Date endDate) throws ClassNotFoundException, SQLException {
        ArrayList<Transfer> trList = new ArrayList<Transfer>();
        Transfer tr = null;
        Statement stmt = null;
    	ResultSet rs = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        String query = "SELECT * FROM transfers WHERE date BETWEEN " + new Date(begDate.getTime()) +
                " AND " + new Date(endDate.getTime()) + ";";

    	stmt = conn.createStatement();
        rs = stmt.executeQuery(query);

        while (rs.next()) {
            tr = new Transfer();
            tr.setId(rs.getInt(1));
            tr.setTransferDate(new Date(rs.getDate(2).getTime()));
            tr.setFromStation(rs.getInt(3));
            tr.setToStation(rs.getInt(4));
            tr.setAmount(rs.getDouble(5));

            trList.add(tr);
        }

        rs.close();
        stmt.close();

    	return trList;
    }

    public int updateTransfer(Transfer transfer) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        stmt = conn.createStatement();

        String query = "UPDATE transfers SET " +
                "date = '" + new Date(transfer.getTransferDate().getTime()) + "', '" +
                "from_station = '" + transfer.getFromStation() + "', '" +
                "to_station = '" + transfer.getToStation() + "', '" +
                "amount = '" + transfer.getAmount() +
                "';";

        int result = stmt.executeUpdate(query);
        stmt.close();

        return result;
    }

    public ArrayList<Transfer> getTransferRS() throws ClassNotFoundException, SQLException {
        ArrayList<Transfer> trList = new ArrayList<Transfer>();
        Transfer tr = null;
        Statement stmt = null;
    	ResultSet rs = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        String query = "SELECT * FROM transfers;";

    	stmt = conn.createStatement();
        rs = stmt.executeQuery(query);

        while (rs.next()) {
            tr = new Transfer();
            tr.setId(rs.getInt(1));
            tr.setTransferDate(new Date(rs.getDate(2).getTime()));
            tr.setFromStation(rs.getInt(3));
            tr.setToStation(rs.getInt(4));
            tr.setAmount(rs.getDouble(5));

            trList.add(tr);
        }

        rs.close();
        stmt.close();

        return trList;
    }

    public ArrayList<Transfer> getTransferRS(String query) throws ClassNotFoundException, SQLException {
        ArrayList<Transfer> trList = new ArrayList<Transfer>();
        Transfer tr = null;
        Statement stmt = null;
    	ResultSet rs = null;
        Connection conn = LoanManagerDAOFactory.createConnection();

    	stmt = conn.createStatement();
        rs = stmt.executeQuery(query);

        while (rs.next()) {
            tr = new Transfer();
            tr.setId(rs.getInt(1));
            tr.setTransferDate(new Date(rs.getDate(2).getTime()));
            tr.setFromStation(rs.getInt(3));
            tr.setToStation(rs.getInt(4));
            tr.setAmount(rs.getDouble(5));

            trList.add(tr);
        }

        rs.close();
        stmt.close();

        return trList;
    }


}
