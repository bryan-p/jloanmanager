package db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import loans.Payment;

/**
 *
 * @author bryan
 */
public interface PaymentDAO {

    public int insertPayment(Payment pmt)throws ClassNotFoundException,
            SQLException;

    public int removePayment(long loanNum, Date d)throws ClassNotFoundException,
            SQLException;

    public int removePayment(long paymentID) throws ClassNotFoundException,
            SQLException;

    public Payment getPayment(long loanNum, Date d)throws ClassNotFoundException,
            SQLException;

    public Payment getPayment(long paymentID)
            throws ClassNotFoundException, SQLException;

    public ArrayList<Payment> getPaymentsByLoan(long loanNum)
            throws ClassNotFoundException, SQLException;

    public int updatePayment(Payment pmt)
            throws ClassNotFoundException, SQLException;

    public ArrayList<Payment> getPaymentRS()throws ClassNotFoundException, SQLException;

    public ArrayList<Payment> getPaymentRS(String query)throws ClassNotFoundException,
            SQLException;

}
