package homework1.fruits;

import java.util.ArrayList;
import java.util.Arrays;

public class Box<T extends Fruits> {
    public ArrayList<T> fruits;

    public Box(T... fruits) {
        this.fruits = new ArrayList<>(Arrays.asList(fruits));
    }

    public float getWeight() {
        float weight = 0;

        for (T e : fruits) {
            weight += e.getWeight();
        }
        return weight;
    }

    public boolean compare(Box<? extends Fruits> someBoxFruit) {
        return Math.abs(getWeight() - someBoxFruit.getWeight()) < 0.0001f;
    }

    public void pourOverBoxesFruits(Box<T> box1, Box<T> box2, int count) {
        for (int i = 0; i < count; i++) {
            box2.fruits.add(box1.fruits.get(i));
            box1.fruits.remove(i);
        }
    }

    public void putFruitsToBox(T... fruits) {
        this.fruits.addAll(Arrays.asList(fruits));
    }
}
