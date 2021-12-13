package homework7.Lesson7;

import java.lang.reflect.Modifier;

public class ReflectionApp {
    public static void main(String[] args) {
        Class clazz = "Java".getClass();
        Class strClass = String.class;
        Class intClass = Integer.class;
        Class smallIntClass = int.class;
        Class smallIntArrayClass = int[][].class;

        System.out.println(smallIntArrayClass.getName() + " " + smallIntArrayClass.getSimpleName());

        int modifiers = clazz.getModifiers();
        if (Modifier.isFinal(modifiers)) {
            System.out.println(clazz.getName() + " is final");
        }

        if (Modifier.isAbstract(modifiers)) {
            System.out.println(clazz.getName() + " is abstract");
        }
        if (Modifier.isPublic(modifiers)) {
            System.out.println(clazz.getName() + " is publiccode -> ");
        }


        Class[] interfaces = clazz.getInterfaces();
        for (Class anInterface : interfaces) {
            System.out.println(anInterface.getName());
        }

        Class superclass = clazz.getSuperclass();
        System.out.println(superclass.getName());
    }

}
