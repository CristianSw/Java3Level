package homework3.Lesson3;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class BufferedIOApp {
    public static void main(String[] args) {

        try (
                FileOutputStream fous = new FileOutputStream("demo.xtx");
                BufferedOutputStream out = new BufferedOutputStream(fous,100_100)
        ){
            long start = System.currentTimeMillis();
            for (int i = 0; i < 100_000; i++) {
                out.write(65);
            }
            System.out.println("t1 = " + (System.currentTimeMillis() - start));
        }catch (IOException e){
            e.printStackTrace();
        }

        try(BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File("demo.txt")))){

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
