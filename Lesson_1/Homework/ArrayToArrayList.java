package Lesson_1.Homework;

import java.util.ArrayList;

public class ArrayToArrayList<T> {
    public ArrayList<T> arrayToArrayList(T[] arr) {
        ArrayList<T> result = new ArrayList<>();
        for (T element : arr) {
            result.add(element);
        }
        return result;
    }
}

class ArrayToArrayListMain {
    public static void main(String[] args) {
        String[] arrStr = {"Hello", "world", "hi", "java"};
        ArrayToArrayList<String> as = new ArrayToArrayList<>();
        ArrayList<String> alStr = as.arrayToArrayList(arrStr);
        for (String str : alStr) {
            System.out.print(str + " ");
        }
    }
}
