package homework7.Lesson7;

public class BikeApp {
    public String model;
    public String serialNo;
    private int year;

    public BikeApp(String model, String serialNo, int year) {
        this.model = model;
        this.serialNo = serialNo;
        this.year = year;
    }

    public BikeApp() {
    }

    @SimpleAnnotation
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @SimpleAnnotation
    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    @SimpleAnnotation
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @NotSimpleAnnotation(name = "myName", value = 11)
    private void setYearAndModel(String model, int year) {
        this.model = model;
        this.year = year;
    }

    @Override
    public String toString() {
        return "BikeApp{" +
                "model='" + model + '\'' +
                ", serialNo='" + serialNo + '\'' +
                ", year=" + year +
                '}';
    }
}
