package homework3.Lesson3;

import java.io.*;

public class DataIOApp {
    public static void main(String[] args) {
        try(DataOutputStream outputStream = new DataOutputStream(new FileOutputStream("demo.txt"))){
            outputStream.writeUTF("We love Java code!!!" + "\n");
        }catch (IOException e){
            e.printStackTrace();
        }

        try(DataInputStream inputStream = new DataInputStream(new FileInputStream("demo.txt"))){
            inputStream.readUTF();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
