package homework6.Lesson;



public class Battery {
    private int percent;

    public Battery(int percent) {
        this.percent = percent;
    }

    public Battery() {
        this.percent = 50;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
