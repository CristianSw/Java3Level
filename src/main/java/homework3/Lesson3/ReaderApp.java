package homework3.Lesson3;

import java.io.*;

public class ReaderApp {
    public static void main(String[] args) {
        try (Reader reader = new BufferedReader(new FileReader("demo.txt"))) {
            System.out.println(reader.read());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
