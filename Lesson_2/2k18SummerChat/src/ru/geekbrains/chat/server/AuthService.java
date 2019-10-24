package ru.geekbrains.chat.server;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class AuthService {
    private static Connection connection;
    private static Statement stmt;

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:users.db");
            stmt = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addUser(String login, String pass, String nick) {
        try {
            String query = "INSERT INTO main (login, password, nickname) VALUES (?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, login);
            ps.setInt(2, pass.hashCode());
            ps.setString(3, nick);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getNickByLoginAndPass(String login, String pass) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT nickname, password FROM main WHERE login = '" + login + "'");
            int myHash = pass.hashCode();
            if (rs.next()) {
                String nick = rs.getString(1);
                int dbHash = rs.getInt(2);
                if (myHash == dbHash) {
                    return nick;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addUserToBlackList(String nickBlocking, String nickBlocked) {
        int idBlocking = 0;
        int idBlocked = 0;
        int idBlocked_new = 0;
        boolean isAlreadyAdded = false;

        idBlocking = getIdByNick(nickBlocking);

        idBlocked = getIdByNick(nickBlocked);

        // Проверка есть ли такая запись в БД
        try {
            ResultSet rs = stmt.executeQuery("SELECT id_block_user FROM blacklist where id_user = '" + idBlocking +
                    "' and id_block_user = '" + idBlocked + "'");
            if (rs.next()) {
                idBlocked_new = rs.getInt(1);
            }
            if (idBlocked == idBlocked_new) {
                isAlreadyAdded = true;
                System.out.println("Данная запись уже есть в черном листе");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!isAlreadyAdded) {
            String query = "INSERT INTO blacklist (id_user, id_block_user) VALUES (" + idBlocking + "," + idBlocked + ")";
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(query);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public static int getIdByNick(String nick) {
        int id = 0;
        try {
            ResultSet rs = stmt.executeQuery("SELECT id FROM main where nickname = '" + nick + "'");
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static boolean isBlackListed(String nickBlocking, String nickBlocked) {
        int idBlocking = 0;
        int idBlocked = 0;
        int idBlocked_new = 0;
        boolean isBlocked = false;

        idBlocking = getIdByNick(nickBlocking);

        idBlocked = getIdByNick(nickBlocked);

        // Проверка есть ли такая запись в БД
        try {
            ResultSet rs = stmt.executeQuery("SELECT id_block_user FROM blacklist where id_user = '" + idBlocking +
                    "' and id_block_user = '" + idBlocked + "'");
            if (rs.next()) {
                idBlocked_new = rs.getInt(1);
            }
            if (idBlocked == idBlocked_new) {
                isBlocked = true;
                System.out.println("Пользователь находится в чёрном листе");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isBlocked;
    }

    public static void addMessageToArchive(String dt, String nick, String message) {
        // String sql = "INSERT INTO MessagesArchive (date_time, from_nick, message) VALUES (" +
        //         dt + "," + nick + "," + message + ")";
        String sql = String.format("INSERT INTO MessagesArchive (date_time, from_nick, message)\n" +
                "VALUES ('%s', '%s', '%s')", dt, nick, message);
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<String> getMessagesFromArchive(String nick_receiving) {
        ArrayList<String> result = new ArrayList<>();
        String sql = "SELECT * FROM messagesarchive";
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String nick_sending = rs.getString(3);
                String messageWithDateTimeAndNick = rs.getString(2) + " " +
                        nick_sending + ": " + rs.getString(4);
                if (!isBlackListed(nick_receiving, nick_sending))
                    result.add(messageWithDateTimeAndNick);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.sort(result);
        return result;
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
