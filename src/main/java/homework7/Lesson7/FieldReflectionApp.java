package homework7.Lesson7;

import java.lang.reflect.Field;

public class FieldReflectionApp {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Class bikeClass = BikeApp.class;


        Field[] fields = bikeClass.getDeclaredFields();

        for(Field field : fields){
            System.out.println("name = " + field.getName() + "type = " + field.getType().getName());
        }

        BikeApp bike = new BikeApp();
        System.out.println(bike);

        Field field = bikeClass.getField("model");

        field.set(bike,"TestMark");
        System.out.println(bike);

        Field yearField = bikeClass.getDeclaredField("year");
        yearField.setAccessible(true);
        yearField.set(bike,2021);
        System.out.println(bike);
    }
}
