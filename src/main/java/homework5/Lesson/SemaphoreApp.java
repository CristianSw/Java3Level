package homework5.Lesson;

import java.util.concurrent.Semaphore;

public class SemaphoreApp {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(4);
        for (int i = 0; i < 10; i++) {
            final int w = i + 1;
            new Thread(() -> {
                try {
                    System.out.println("Thread  " + w + ": before semaphore.");
                    semaphore.acquire();
                    System.out.println("Thread " + w + ": get access to resource.");
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Thread " + w + ": release access.");
                    semaphore.release();
                }
            }).start();
        }
    }
}
