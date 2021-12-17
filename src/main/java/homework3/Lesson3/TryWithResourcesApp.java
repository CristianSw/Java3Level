package homework3.Lesson3;

import java.io.Closeable;

public class TryWithResourcesApp {
    public static void main(String[] args) {
        try (MyResource resource = new MyResource("resource 1")) {

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static class MyResource implements Closeable {

        private final String NAME;

        private MyResource(String name) {
            NAME = name;
        }

        public void read(){
            System.out.println("read from resource!");
        }

        @Override
        public void close() {
            System.out.println("resource " + NAME + " closed");
        }
    }
}
