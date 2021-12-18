package homework5.Lesson;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class DemoApp {
    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>();
        List<Integer> list2 = Collections.synchronizedList(new ArrayList<>());

        Map<String, String> map = new ConcurrentHashMap<>();

    }
}
