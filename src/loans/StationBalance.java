package loans;

import java.util.Date;

/**
 *
 * @author bryan Payne
 */
public class StationBalance implements Comparable<StationBalance>{

    private double begBal;
    private double endBal;
    private Date openDate;
    private Date closeDate;
    private int stationNum; // 0 = safe

    public StationBalance(){}
    
    public StationBalance(int stationNum, String desc) {
        this.stationNum = stationNum;
    }


    // getters
    public double getBegBal() {
        return begBal;
    }
    public Date getCloseDate() {
        return closeDate;
    }
    public double getEndBal() {
        return endBal;
    }
    public Date getOpenDate() {
        return openDate;
    }
    public int getStationNum() {
        return stationNum;
    }

    // setters
    public void setBegBal(double begBal) {
        this.begBal = begBal;
    }
    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }
    public void setEndBal(double endBal) {
        this.endBal = endBal;
    }
    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }
    public void setStationNum(int stationNum) {
        this.stationNum = stationNum;
    }

    public int compareTo(StationBalance anotherStation) {
        return anotherStation.getStationNum() - this.stationNum;
    }

}
