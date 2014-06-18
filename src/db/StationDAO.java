/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.sql.*;
import java.util.ArrayList;
import loans.Station;
import loans.StationBalance;

/**
 *
 * LoanDAO
 *
 * Interface for loan database objects
 *
 * @author Bryan Payne
 * @versoin 0.1
 */
public interface StationDAO {

    public int insertStation(Station station)throws ClassNotFoundException,
            SQLException;

    public int insertStationBalance(StationBalance stBalance)throws ClassNotFoundException,
            SQLException;

    public int removeStation(int stationNum)throws ClassNotFoundException,
            SQLException;

    public int removeStationBalance(int stationNum, java.util.Date date)throws ClassNotFoundException,
            SQLException;

    public Station getStation(int stationNum)throws ClassNotFoundException,
            SQLException;

    public StationBalance getStationBalance(int stationNum, java.util.Date d)throws ClassNotFoundException,
            SQLException;

    public int updateStation(Station station)throws ClassNotFoundException,
            SQLException;

    public int updateStationBalance(StationBalance stBalance) throws ClassNotFoundException,
            SQLException;

    public ArrayList<Station> getStationRS()throws ClassNotFoundException,
            SQLException;

    public ArrayList<StationBalance> getStationBalanceRS()throws ClassNotFoundException,
            SQLException;

    public ArrayList<Station> getStationRS(String query)throws ClassNotFoundException,
            SQLException;

    public ArrayList<StationBalance> getStationBalanceRS(String query)throws ClassNotFoundException,
            SQLException;
    
}
