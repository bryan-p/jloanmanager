/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package loans;

import java.util.Comparator;

/**
 *
 * @author bryan
 */
public class DueDateSort implements Comparator<Loan>{

    public int compare(Loan l1, Loan l2) {
        return l1.getDueDate().compareTo(l2.getDueDate());
    }
}
