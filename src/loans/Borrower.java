/*
 *
 */
package loans;

import enums.PayFrequency;
import enums.States;

/**
 * @author Bryan
 * version 0.1
 * 
 * This class holds borrower information including loan history
 */
public class Borrower implements Comparable {

    private long custNum; // internal cust id within database
    private String firstName, middleName, lastName, suffix, fullName = "";
    private String street1, street2, city1, city2 = "";
    private int zip1, zip2;
    private States state;
    private PayFrequency payFreq;

    /**
     * Default, do nothing constructor
     */
    public Borrower() {
    }

    /*
     * Constructor
     */
    public Borrower(String fname, String mname, String lname, 
            PayFrequency payFreq) {

        firstName = fname;
        middleName = mname;
        lastName = lname;
        this.payFreq = payFreq;

        // full name as one field, for searching
        fullName += fname + " " + mname + " " + lname;
    }

    public String getCity1() {
        return city1;
    }

    public void setCity1(String city1) {
        this.city1 = city1;
    }

    public String getCity2() {
        return city2;
    }

    public void setCity2(String city2) {
        this.city2 = city2;
    }

    public States getState() {
        return state;
    }

    public void setState(States state) {
        this.state = state;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public int getZip1() {
        return zip1;
    }

    public void setZip1(int zip1) {
        this.zip1 = zip1;
    }

    public int getZip2() {
        return zip2;
    }

    public void setZip2(int zip2) {
        this.zip2 = zip2;
    }

    // getter methods
    // --------------
    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return lastName.toUpperCase() + ", "
                + firstName.toUpperCase() + " " + middleName.toUpperCase();
    }

    public long getCustNum() {
        return custNum;
    }

    public PayFrequency getPayFreq() {
        return payFreq;
    }

    // setter methods
    // --------------
    public void setCustNum(long custNum) {
        this.custNum = custNum;
    }

    public void setFirstName(String fname) {
        firstName = fname;
    }

    public void setMiddleName(String mname) {
        middleName = mname;
    }

    public void setLastName(String lname) {
        lastName = lname;
    }

    public void setNewName() {
        fullName += lastName + " " + firstName + " "
                + middleName;
    }

    public void setPayFreq(PayFrequency payFreq) {
        this.payFreq = payFreq;
    }

    public String nameToString() {
        return lastName + ", " + firstName + " " + lastName;
    }

    // Overide methods of class Object
    // -------------------------------
    public String toString() {
        return "\n" + custNum + "\n" + lastName + ", " + firstName + " "
                + middleName + "\n";
    }

    public int compareTo(Object o) {
        return fullName.compareTo(((Borrower) o).fullName);
    }
}
