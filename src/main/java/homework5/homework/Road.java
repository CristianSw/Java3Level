package homework5.homework;

import java.util.concurrent.Semaphore;

public class Road extends Stage {
    Semaphore semaphore = new Semaphore(1);
    private static volatile boolean isTrue = true;
    public volatile static String winnerName;

    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);

            System.out.println(c.getName() + " закончил этап: " + description);
            if (length == RaceApp.secondRoad) {
                semaphore.acquire();
                String winnerThread = Thread.currentThread().getName();
                while (isTrue) {

                    if (winnerThread.equals("Thread-0")) {
                        winnerName = "Участник #1";
                    } else if (winnerThread.equals("Thread-1")) {
                        winnerName = "Участник #2";
                    } else if (winnerThread.equals("Thread-2")) {
                        winnerName = "Участник #3";
                    } else if (winnerThread.equals("Thread-3")) {
                        winnerName = "Участник #4";
                    }
                    isTrue = false;
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
