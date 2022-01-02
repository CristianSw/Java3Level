package homework4.Lesson4;

import java.util.concurrent.atomic.AtomicBoolean;

public class Interrupt2App {
    public static void main(String[] args) throws InterruptedException {
        AtomicBoolean isAvailable = new AtomicBoolean(true);
        Thread thread = new Thread(()->{
            while (isAvailable.get()){
                System.out.println("Available");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        Thread.sleep(1000);
        isAvailable.set(false);
    }
}
