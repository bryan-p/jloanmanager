package db;

import java.sql.*;
import java.util.ArrayList;
import loans.Station;
import loans.StationBalance;

/**
 *
 * @author bryan
 */
public class MySQLStationDAO implements StationDAO {

    public int insertStation(Station st) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        stmt = conn.createStatement();

        String query = "INSERT INTO stations (station_number, description, current_balance, open) VALUES ('"
                + st.getStationNum() + "', '"
                + st.getDescription() + "', '"
                + st.getCurBal() + "', '"
                + ((st.isIsOpen()) ? 1 : 0)
                + "');";

        int result = stmt.executeUpdate(query);

        stmt.close();

        return result;
    }

    public int insertStationBalance(StationBalance stBal) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        stmt = conn.createStatement();

        String query = "INSERT INTO station_balances (open_date, beg_bal, end_bal, station_number) "
                + "VALUES ('"
                + new Date(stBal.getOpenDate().getTime()) + "', '"
                + stBal.getBegBal() + "', '"
                + stBal.getEndBal() + "', '"
                + stBal.getStationNum()
                + "');";

        int result = stmt.executeUpdate(query);

        stmt.close();

        return result;
    }

    public int removeStation(int stationNum)
            throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        stmt = conn.createStatement();

        String query = "DELETE FROM stations WHERE station_number = '" + stationNum
                + "';";

        int result = stmt.executeUpdate(query);
        stmt.close();

        return result;
    }

    public int removeStationBalance(int stationNum, java.util.Date d) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        stmt = conn.createStatement();

        String query = "DELETE FROM station_balances WHERE station_number = '" + stationNum + "' "
                + "AND open_date = '" + new Date(d.getTime())
                + "';";

        int result = stmt.executeUpdate(query);
        stmt.close();

        return result;
    }

    public Station getStation(int stationNum) throws ClassNotFoundException,
            SQLException {
        Station st = null;
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        String query = "SELECT * FROM stations WHERE station_number = '" + stationNum + "';";

        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);

        if (rs.next() != false) {
            st = new Station();
            st.setStationNum(rs.getInt(1));
            st.setDescription(rs.getString(2));
            st.setCurBal(rs.getDouble(3));
            st.setIsOpen(rs.getBoolean(4));
        }

        rs.close();
        stmt.close();



        return st;
    }

    public StationBalance getStationBalance(int stationNum, java.util.Date d) throws ClassNotFoundException,
            SQLException {
        StationBalance stBal = null;
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        String query = "SELECT * FROM station_balances WHERE station_number = '" + stationNum + "' "
                + "AND open_date = '" + new Date(d.getTime())
                + "';";

        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);

        if (rs.next() != false) {
            stBal = new StationBalance();
            stBal.setOpenDate(new java.util.Date(rs.getDate(1).getTime()));

            if (rs.getDate(2) != null) {
                stBal.setCloseDate(new java.util.Date(rs.getDate(2).getTime()));
            }

            stBal.setBegBal(rs.getDouble(3));
            stBal.setEndBal(rs.getDouble(4));
            stBal.setStationNum(rs.getInt(5));
        }

        rs.close();
        stmt.close();

        return stBal;
    }

    public int updateStation(Station st)
            throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        stmt = conn.createStatement();

        String query = "UPDATE stations SET "
                + "description = '" + st.getDescription() + "', "
                + "current_balance = '" + st.getCurBal() + "', "
                + "open = '" + ((st.isIsOpen()) ? 1 : 0)
                + "' "
                + " WHERE station_number = '" + st.getStationNum()
                + "';";

        int result = stmt.executeUpdate(query);
        stmt.close();

        return result;
    }

    public int updateStationBalance(StationBalance stBal)
            throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        stmt = conn.createStatement();

        String query = "UPDATE station_balances SET "
                + "beg_bal = '" + stBal.getBegBal() + "', "
                + "end_bal = '" + stBal.getEndBal() + "' "
                + "WHERE open_date = '" + new Date(stBal.getOpenDate().getTime()) + "' "
                + "AND station_number = '" + stBal.getStationNum()
                + "';";

        int result = stmt.executeUpdate(query);
        stmt.close();

        return result;
    }

    public ArrayList<Station> getStationRS() throws ClassNotFoundException,
            SQLException {
        ArrayList<Station> stList = new ArrayList<Station>();
        Station st = null;
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        String query = "SELECT * FROM stations;";

        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);

        while (rs.next()) {
            st = new Station();
            st.setStationNum(rs.getInt(1));
            st.setDescription(rs.getString(2));
            st.setCurBal(rs.getDouble(3));
            st.setIsOpen(rs.getBoolean(4));

            stList.add(st);
        }

        rs.close();
        stmt.close();

        return stList;
    }

    public ArrayList<StationBalance> getStationBalanceRS() throws ClassNotFoundException,
            SQLException {
        ArrayList<StationBalance> stList = new ArrayList<StationBalance>();
        StationBalance stBal = null;
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = LoanManagerDAOFactory.createConnection();
        String query = "SELECT * FROM station_balances;";

        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);

        while (rs.next()) {
            stBal = new StationBalance();
            stBal.setOpenDate(new java.util.Date(rs.getDate(1).getTime()));

            if (rs.getDate(2) != null) {
                stBal.setCloseDate(new java.util.Date(rs.getDate(2).getTime()));
            }
            
            stBal.setBegBal(rs.getDouble(3));
            stBal.setEndBal(rs.getDouble(4));
            stBal.setStationNum(rs.getInt(5));

            stList.add(stBal);
        }

        rs.close();
        stmt.close();

        return stList;
    }

    public ArrayList<Station> getStationRS(String query)
            throws ClassNotFoundException, SQLException {
        ArrayList<Station> stList = new ArrayList<Station>();
        Station st = null;
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = LoanManagerDAOFactory.createConnection();

        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);

        while (rs.next()) {
            st = new Station();
            st.setStationNum(rs.getInt(1));
            st.setDescription(rs.getString(2));
            st.setCurBal(rs.getDouble(3));
            st.setIsOpen(rs.getBoolean(4));

            stList.add(st);
        }

        rs.close();
        stmt.close();

        return stList;
    }

    public ArrayList<StationBalance> getStationBalanceRS(String query)
            throws ClassNotFoundException, SQLException {
        ArrayList<StationBalance> stList = new ArrayList<StationBalance>();
        StationBalance stBal = null;
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = LoanManagerDAOFactory.createConnection();

        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);

        while (rs.next()) {
            stBal = new StationBalance();
            stBal.setOpenDate(new java.util.Date(rs.getDate(1).getTime()));

            if (rs.getDate(2) != null) {
                stBal.setCloseDate(new java.util.Date(rs.getDate(2).getTime()));
            }

            stBal.setBegBal(rs.getDouble(3));
            stBal.setEndBal(rs.getDouble(4));
            stBal.setStationNum(rs.getInt(5));

            stList.add(stBal);
        }

        rs.close();
        stmt.close();

        return stList;
    }
}
