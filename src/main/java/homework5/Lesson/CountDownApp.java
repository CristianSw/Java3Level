package homework5.Lesson;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownApp {
    private static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                final int w = i + 1;
                try {
                    //task
                    Thread.sleep(random.nextInt(5) * 1000);
                    countDownLatch.countDown();
                    System.out.println("Thread " + w + " finished.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
        countDownLatch.await();
        System.out.println("Working Completed");
    }
}
