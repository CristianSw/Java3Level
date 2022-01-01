package homework7.Lesson7;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ConstructorReflectionApp {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class bikeClass = BikeApp.class;

        Constructor[] declaredConstructors = bikeClass.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            System.out.println(Arrays.toString(declaredConstructor.getParameterTypes()));
        }


        bikeClass.newInstance();

        Constructor constructor = bikeClass.getDeclaredConstructor(String.class, String.class, int.class);

        BikeApp bike = (BikeApp) constructor.newInstance("Canyon", "12355", 2020);
        System.out.println(bike);


    }
}
