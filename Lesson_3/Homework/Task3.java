package Lesson_3.Homework;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);
        int pageNumber = 0;
        int SIZE = 1800;
        try (RandomAccessFile raf = new RandomAccessFile(("src/main/java/Lesson_3/Homework/task3.txt"),"r")) {
            long t2 = System.currentTimeMillis();
            System.out.println("Время загрузки: " + (t2 - t1));

            while (pageNumber != -1) {
                System.out.println("Enter page number: ");
                pageNumber = sc.nextInt();
                t1 = System.currentTimeMillis();
                raf.seek(SIZE * pageNumber);
                int x;
                byte[] arr = new byte[SIZE];
                while ((x = raf.read(arr)) != -1) {
                    System.out.print(new String(arr, 0, SIZE, "UTF-8"));
                }
                t2 = System.currentTimeMillis();
                System.out.println("Время чтения: " + (t2 - t1));
            }
        } catch (IOException e) {


        }


    }
}
