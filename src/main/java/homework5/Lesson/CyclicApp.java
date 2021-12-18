package homework5.Lesson;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class CyclicApp {
    private static Random random = new Random();
    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for (int i = 0; i <5; i++) {
            final int w = i+1;
            new Thread(()->{
                try {
                    System.out.println("Preparing.");
                    Thread.sleep(random.nextInt(5) * 1000);
                    System.out.println("Thread " + w + " is ready.");
                    cyclicBarrier.await();
                    System.out.println("Thread " + w + " is running.");
                    Thread.sleep(random.nextInt(5) * 1000);
                    System.out.println("Thread " + w + " is finished.");
                    cyclicBarrier.await();
                    System.out.println("Run finished.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
