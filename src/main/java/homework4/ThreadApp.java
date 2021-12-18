package homework4;

public class ThreadApp {
    private final Object monitor = new Object();
    private  char currentLetter = 'A';

    public static void main(String[] args) {
        ThreadApp threadApp = new ThreadApp();

        Thread thread1 = new Thread(threadApp::printA);
        Thread thread2 = new Thread(threadApp::printB);
        Thread thread3 = new Thread(threadApp::printC);

        thread1.start();
        thread2.start();
        thread3.start();


    }

    private void printA(){
        synchronized (monitor){
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'A'){
                        monitor.wait();
                    }
                    System.out.print("A");
                    currentLetter = 'B';
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void printB(){
        synchronized (monitor){
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'B'){
                        monitor.wait();
                    }
                    System.out.print("B");
                    currentLetter = 'C';
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void printC(){
        synchronized (monitor){
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'C'){
                        monitor.wait();
                    }
                    System.out.print("C");
                    currentLetter = 'A';
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
