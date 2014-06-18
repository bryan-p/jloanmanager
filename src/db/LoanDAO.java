/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.sql.SQLException;
import java.util.ArrayList;
import loans.Loan;

/**
 *
 * LoanDAO
 *
 * Interface for loan database objects
 *
 * @author Bryan Payne
 * @versoin 0.1
 */
public interface LoanDAO {

    public int insertLoan(Loan newLoan)throws ClassNotFoundException,
            SQLException;

    public int removeLoan(long loanNum)throws ClassNotFoundException,
            SQLException;

    public Loan getLoan(long loanNum)throws ClassNotFoundException,
            SQLException;

    public int updateLoan(Loan loan)throws ClassNotFoundException,
            SQLException;

    public ArrayList<Loan> getLoansByBorrower(long borrowerNum)
            throws ClassNotFoundException, SQLException;

    public ArrayList<Loan> getLoanRS()throws ClassNotFoundException,
            SQLException;

    public ArrayList<Loan> getLoanRS(String query)throws ClassNotFoundException,
            SQLException;
    
}
