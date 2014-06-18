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
public class BorrowerNameSort implements Comparator<Borrower>{

    public int compare(Borrower b1, Borrower b2) {
        return b1.getFullName().compareTo(b2.getFullName());
    }
}
