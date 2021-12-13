package homework7.Lesson7;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class MethodReflectionApp {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class bikeClass = BikeApp.class;

        Method[] declaredMethods = bikeClass.getDeclaredMethods();

        for (Method declaredMethod : declaredMethods) {

            if (declaredMethod.getAnnotation(SimpleAnnotation.class) != null) {
                System.out.println("method with annotation");
            }
            Annotation[] declaredAnnotations = declaredMethod.getDeclaredAnnotations();
            for (Annotation declaredAnnotation : declaredAnnotations) {
                System.out.println(declaredAnnotation.annotationType().getName());
            }

            System.out.println("name = " + declaredMethod.getName() + " returnType = " + declaredMethod.getReturnType().getName() +
                    "parameters " + Arrays.toString(declaredMethod.getParameterTypes()));
        }

        Method method = bikeClass.getDeclaredMethod("setYearAndModel", String.class, int.class);

        NotSimpleAnnotation annotation = method.getDeclaredAnnotation(NotSimpleAnnotation.class);
        System.out.println(annotation.name() + " " + annotation.value());

        BikeApp bike = new BikeApp();
        System.out.println(bike);

        method.setAccessible(true);
        method.invoke(bike, "Pinarella", 2021);

        System.out.println(bike);
    }
}
