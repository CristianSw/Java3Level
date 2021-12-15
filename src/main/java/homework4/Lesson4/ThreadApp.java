package homework4.Lesson4;

public class ThreadApp {


    private static class MyRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println("Do smth.");
        }
    }

    private static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("My Thread.");
        }
    }


    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.start();
        Thread myThread = new Thread(new MyThread());
        myThread.start();


        Thread thread3 = new Thread(()-> System.out.println("Thread 3"));

        Thread anonimClassThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonim class Thread.");
            }
        });
        try {
            thread3.join(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw  new RuntimeException("Thread sleep.");
        }
    }
}
