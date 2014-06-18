package db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author bryan
 */
public interface GOPDAO {

    public int insertGOP(Date d, double principle)throws ClassNotFoundException,
            SQLException;

    public int removeGOP(Date d)throws ClassNotFoundException,
            SQLException;

    public Double getGOP(Date d)throws ClassNotFoundException,
            SQLException;

    public int updateGOP(Date d, double principle)
            throws ClassNotFoundException, SQLException;

    public ArrayList<Double> getGOPRS()throws ClassNotFoundException, SQLException;

    public ArrayList<Double> getGOPRS(String query)throws ClassNotFoundException,
            SQLException;

}
