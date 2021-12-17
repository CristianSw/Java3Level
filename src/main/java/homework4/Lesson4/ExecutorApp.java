package homework4.Lesson4;

import java.io.Flushable;
import java.io.IOException;
import java.util.concurrent.*;

public class ExecutorApp {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorTasks(executorService);
        executorService.shutdown();

        ExecutorService executorService1 = Executors.newCachedThreadPool();
        executorTasks(executorService1);
        executorTasks(executorService1);

        Future<?> future = executorService1.submit(() -> System.out.println("smthng"));

        Future<String> myString = executorService1.submit(new MyCallable());
        System.out.println("\n\n" + myString.get());


        executorService1.shutdown(); // finish working
        executorService1.shutdownNow(); // finish working now and not accept new tasks & send interrupt to threads


    }

    public static void executorTasks(ExecutorService executorService) {
        executorService.execute(()-> System.out.println(Thread.currentThread().getName()));
        executorService.execute(()-> System.out.println(Thread.currentThread().getName()));
        executorService.execute(()-> System.out.println(Thread.currentThread().getName()));
        executorService.execute(()-> System.out.println(Thread.currentThread().getName()));
        executorService.execute(()-> System.out.println(Thread.currentThread().getName()));
        executorService.execute(()-> System.out.println(Thread.currentThread().getName()));
        executorService.execute(()-> System.out.println(Thread.currentThread().getName()));
        executorService.execute(()-> System.out.println(Thread.currentThread().getName()));
        executorService.execute(()-> System.out.println(Thread.currentThread().getName()));
        executorService.execute(()-> System.out.println(Thread.currentThread().getName()));
        executorService.execute(()-> System.out.println(Thread.currentThread().getName()));
        executorService.execute(()-> System.out.println(Thread.currentThread().getName()));
        executorService.execute(()-> System.out.println(Thread.currentThread().getName()));
        executorService.execute(()-> System.out.println(Thread.currentThread().getName()));
        executorService.execute(()-> System.out.println(Thread.currentThread().getName()));
        executorService.execute(()-> System.out.println(Thread.currentThread().getName()));
        executorService.execute(()-> System.out.println(Thread.currentThread().getName()));
        executorService.execute(()-> System.out.println(Thread.currentThread().getName()));
    }

    private static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "From callable";
        }
    }
}
