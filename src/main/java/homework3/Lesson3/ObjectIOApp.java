package homework3.Lesson3;

import java.io.*;

public class ObjectIOApp {
    public static void main(String[] args) {
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("demo.txt"))){
            User user = new User("Test", 55);
            outputStream.writeObject(user);
        }catch (IOException e){
            e.printStackTrace();
        }


        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("demo.txt"))){
            User user = (User) inputStream.readObject();

        }catch(IOException  e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private static class User implements Serializable {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
