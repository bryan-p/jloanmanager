package loans;

import java.util.Date;

/**
 *
 * @author bryan Payne
 */
public class Station implements Comparable<Station>{

    private boolean isOpen;
    private double curBal;
    private int stationNum; // 0 = safe
    private String description;

    public Station(){}
    
    public Station(int stationNum, String desc) {
        this.stationNum = stationNum;
        this.description = desc;
    }


    // getters
    public boolean isIsOpen() {
        return isOpen;
    }
    public double getCurBal() {
        return curBal;
    }
    public String getDescription() {
        return description;
    }
    public int getStationNum() {
        return stationNum;
    }

    // setters
    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
    public void setCurBal(double curBal) {
        this.curBal = curBal;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setStationNum(int stationNum) {
        this.stationNum = stationNum;
    }

    @Override
    public String toString() {
        return description;
    }

    public int compareTo(Station anotherStation) {
        return anotherStation.getStationNum() - this.stationNum;
    }

}
