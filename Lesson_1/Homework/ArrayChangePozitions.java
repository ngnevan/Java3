package Lesson_1.Homework;

public class ArrayChangePozitions<T> {
    public void arrayChangePositions(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

class ArrayChangePozitionsMain {
    public static void main(String[] args) {
        String[] arrStr = {"Hello", "world", "hi", "java"};
        ArrayChangePozitions<String> arrS  = new ArrayChangePozitions<>();
        arrS.arrayChangePositions(arrStr, 0, 1);
        for (String str : arrStr) {
            System.out.print(str + " ");
        }
        System.out.println();

        Integer[] arrInt = {0, 1, 2, 3, 4, 5};
        ArrayChangePozitions<Integer> arrI = new ArrayChangePozitions<>();
        arrI.arrayChangePositions(arrInt, 2, 3);
        for (Integer i : arrInt) {
            System.out.print(i + " ");
        }

    }
}