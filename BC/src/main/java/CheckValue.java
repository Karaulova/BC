public class CheckValue {
    private int bulls;
    private int cows;
    private String enterValue;


    public CheckValue(int bulls, int cows, String enterValue) {
        this.bulls = bulls;
        this.cows = cows;
        this.enterValue = enterValue;
    }

    public int getBulls() {
        return bulls;
    }

    public void setBulls(int bulls) {
        this.bulls = bulls;
    }

    public int getCows() {
        return cows;
    }

    public void setCows(int cows) {
        this.cows = cows;
    }

    public String getEnterValue() {
        return enterValue;
    }

    public void setEnterValue(String enterValue) {
        this.enterValue = enterValue;
    }
}
