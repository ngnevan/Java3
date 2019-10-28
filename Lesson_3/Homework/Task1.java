package Lesson_3.Homework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Task1 {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("src/main/java/Lesson_3/Homework/task1.txt")) {
            int x;
            byte[] arr = new byte[512];

            while ((x = in.read(arr)) != -1) {
                System.out.print(new String(arr, 0, x, "UTF-8"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
