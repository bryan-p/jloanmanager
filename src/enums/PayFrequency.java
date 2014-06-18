package enums;

/**
 *
 * @author bryan
 */
public enum PayFrequency {
    WEEKLY(14), BI_WEEKLY(14), SEMI_MONTHLY(15), MONTHLY(30);

    private int duration;
    private int loanPeriod;

    PayFrequency(int duration){
        this.duration = duration;

        switch(this.ordinal()){
            case 0:
            case 1:
                loanPeriod = duration * 2;
                break;
            case 2:
                loanPeriod = duration * 2 + 1;
                break;
            case 3:
                loanPeriod = duration * 2 + 2;
                break;
        }
    }

    public int getDuration(){return duration;}

    public int getLoanPeriod(){return loanPeriod;}

}
