package Lesson_1.Homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ArrayListToHashMap {
    public static void main(String[] args) {
        ArrayList<SomeClass> al = new ArrayList<>();
        al.add(new SomeClass(1, "Test1"));
        al.add(new SomeClass(2, "Test1"));
        al.add(new SomeClass(3, "Test1"));
        al.add(new SomeClass(4, "Test2"));
        al.add(new SomeClass(5, "Test2"));
        al.add(new SomeClass(6, "Test3"));
        al.add(new SomeClass(7, "Test3"));
        al.add(new SomeClass(8, "Test4"));
        HashMap<String, ArrayList<Integer>> hm = new HashMap<>();
        al.forEach(s ->  {
            ArrayList<Integer> ali = hm.get(s.name);
            if (ali == null) {
                ali = new ArrayList<>();
            }
            ali.add(s.id);
            hm.put(s.name, ali);

        });
//        for (SomeClass s : al) {
//            ArrayList<Integer> ali = hm.get(s.name);
//            if (ali == null) {
//                ali = new ArrayList<>();
//            }
//            ali.add(s.id);
//            hm.put(s.name, ali);
//        }

        for (Map.Entry<String, ArrayList<Integer>> entry : hm.entrySet()) {
            String key = entry.getKey();
            ArrayList<Integer> value = entry.getValue();
            System.out.println(key + " " + value);
        }
    }
}

class SomeClass {
    int id;
    String name;

    public SomeClass(int id, String name) {
        this.id = id;
        this.name = name;
    }
}