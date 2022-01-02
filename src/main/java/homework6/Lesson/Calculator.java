package homework6.Lesson;

public class Calculator {

    private Battery battery;

    public Calculator(Battery battery) {
        this.battery = battery;
    }

    public Calculator() {
        battery = new Battery(100);
    }

    public int sum(int a, int b){
        return a+b;
    }
    public int multiply(int a, int b){
        return a*b;
    }

    public int division(int a, int b){
//        if (b == 0 ) return 0;
        return a/b;
    }

    public int scadere(int a, int b){
        return a-b;
    }
}
