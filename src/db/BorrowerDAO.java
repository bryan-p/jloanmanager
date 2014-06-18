/*
 * Database package.
 */

package db;

import java.sql.SQLException;
import java.util.ArrayList;
import loans.Borrower;

/**
 *
 * BorrowerDAO
 * 
 * Interface for customer database objects
 *
 * @author Bryan Payne
 * @versoin 0.1
 */
public interface BorrowerDAO {

    public int insertBorrower(Borrower b)throws ClassNotFoundException,
            SQLException;

    public int removeBorrower(long custNum)throws ClassNotFoundException,
            SQLException;

    public Borrower getBorrower(long custNum)throws ClassNotFoundException,
            SQLException;

    public ArrayList<Borrower> getBorrower(String lastName)throws SQLException,
            ClassNotFoundException;

    public int updateBorrower(Borrower b)throws ClassNotFoundException,
            SQLException;

    public ArrayList<Borrower> getBorrowerRS()throws ClassNotFoundException,
            SQLException;

    public ArrayList<Borrower> getBorrowerRS(String query)throws SQLException,
            ClassNotFoundException;


}
