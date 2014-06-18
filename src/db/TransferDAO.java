/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.sql.SQLException;
import java.util.ArrayList;
import loans.Transfer;

/**
 *
 * @author bryan
 */
public interface TransferDAO {

    public int insertTransfer(Transfer newTransfer)throws ClassNotFoundException,
            SQLException;

    public int removeTransfer(int idNum)throws ClassNotFoundException,
            SQLException;

    public ArrayList<Transfer> getTransfers(java.util.Date begDate, java.util.Date endDate)throws ClassNotFoundException,
            SQLException;

    public int updateTransfer(Transfer transfer)throws ClassNotFoundException,
            SQLException;

    public ArrayList<Transfer> getTransferRS()throws ClassNotFoundException,
            SQLException;

    public ArrayList<Transfer> getTransferRS(String query)throws ClassNotFoundException,
            SQLException;

}
