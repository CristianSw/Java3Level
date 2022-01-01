package homework3.Lesson3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class WriterApp {
    public static void main(String[] args) {
        try(Writer writer = new BufferedWriter(new FileWriter("demo.txt"))){
            writer.write("Something to write!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
