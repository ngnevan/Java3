package Lesson_3.Homework;

import Lesson_3.Book;
import Lesson_3.Student;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Task4_client {
    final String IP_ADDRESS = "localhost";
    final int PORT = 8189;

    public Task4_client() {
        try {
            Socket socket = new Socket(IP_ADDRESS, PORT);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Student s = new Student(1, "Bob1");

            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(s);
            oos.close();


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Task4_client_Main {
    public static void main(String[] args) {
        Task4_client client = new Task4_client();
    }
}