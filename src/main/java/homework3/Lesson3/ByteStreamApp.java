package homework3.Lesson3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class ByteStreamApp {
    public static void main(String[] args) {
        byte[] arr = {65, 66, 67};
        ByteArrayInputStream in = new ByteArrayInputStream(arr);
        int i = 0;
        while(in.available() > 0){
            i = in.read();
            System.out.println(i);
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(65);
        out.write(66);
        out.write(67);
        System.out.println(Arrays.toString(out.toByteArray()));
    }
}
