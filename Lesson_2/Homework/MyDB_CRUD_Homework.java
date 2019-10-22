package Lesson_2.Homework;

import java.sql.*;

public class MyDB_CRUD_Homework {
    private static Connection connection;
    private static Statement stmt;

    public static void main(String[] args) {
        try {
            connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            crudDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void crudDB() throws SQLException {
        // Delete table
        String sql = "DROP TABLE students";
        stmt.executeUpdate(sql);

        // Create table
        sql = "CREATE TABLE students (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "score TEXT)";
        stmt.executeUpdate(sql);

        // Insert records
        for (int i = 0; i < 10; i++) {
            String str = "Bob" + i;
            sql = String.format("INSERT INTO students (name, score)\n" + "VALUES ('%s', %d)", str, i);
            stmt.executeUpdate(sql);
        }

        sql = "SELECT * FROM students";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "; " + rs.getString(2) + "; " +
                    rs.getString(3));
        }

    }

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        stmt = connection.createStatement();
    }

    public static void close() throws SQLException {
        connection.close();
    }
}
