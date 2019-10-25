package Lesson_3.Homework;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Task5 {
    public static void main(String[] args) {
        FileReader fr = null;
        try {
            fr = new FileReader("src/main/java/Lesson_3/Homework/task5.txt");
            BufferedReader br = new BufferedReader(fr);
            String s;
            ArrayList<String> tmp = new ArrayList<>();

            do {
                s = br.readLine();
                if (s != null)
                    tmp.add(s);
            } while (s != null);

            Collections.reverse(tmp);
            System.out.print(tmp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
