package Lesson_3.Homework;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Task2 {
    public static void main(String[] args) {
        ArrayList<InputStream> ali = new ArrayList<>();
        try {
            ali.add(new FileInputStream("src/main/java/Lesson_3/Homework/task2_1.txt"));
            ali.add(new FileInputStream("src/main/java/Lesson_3/Homework/task2_2.txt"));
            ali.add(new FileInputStream("src/main/java/Lesson_3/Homework/task2_3.txt"));
            ali.add(new FileInputStream("src/main/java/Lesson_3/Homework/task2_4.txt"));
            ali.add(new FileInputStream("src/main/java/Lesson_3/Homework/task2_5.txt"));

            SequenceInputStream in = new SequenceInputStream(Collections.enumeration(ali));

            int x;
            while ((x = in.read()) != -1){
                System.out.print((char)x);
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
