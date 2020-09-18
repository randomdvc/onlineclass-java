package org.rldevelopement.back.network;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.rldevelopement.back.Account;
import org.rldevelopement.back.Class;
import org.rldevelopement.front.App;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class SQLManager {

    private static Connection connection;
    private String urlbase, host, database, user, pass;

    private static ResultSet resultat;

    public static int classesCount = 0;

    public SQLManager(String urlbase, String host, String database, String user, String pass) {

        this.urlbase = urlbase;
        this.host = host;
        this.database = database;
        this.user = user;
        this.pass = pass;

    }

    public void connect() {
        if(!isConnected()) {
            try {
                connection = DriverManager.getConnection(urlbase + host + "/" + database, user, pass);
                System.out.println("Connected ok");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void createAccount(String fname, String lname, String email, String password, String type) {
        if(!hasAccount(email)) {
            try {
                PreparedStatement q  = connection.prepareStatement("INSERT INTO user(fname,lname,email,password,type) VALUES (?,?,?,?,?)");
                q.setString(1, fname);
                q.setString(2, lname);
                q.setString(3, email);
                q.setString(4, password);
                q.setString(5, type);
                q.execute();
                q.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Account getAccount(String email, String password) {
        try {
            PreparedStatement q = connection.prepareStatement("SELECT * FROM user WHERE email = ?");
            q.setString(1, email);
            resultat = q.executeQuery();
            Account account = null;
            while (resultat.next()) {
                if(password.equals(resultat.getString("password"))) {
                    account = new Account(resultat.getString("fname"), resultat.getString("lname"), resultat.getString("email"), resultat.getString("type"), resultat.getString("cycle"), resultat.getInt("level"), resultat.getString("class"), resultat.getInt("absence"));
                    App.setAccount(account);
                    return account;
                } else {
                    System.out.println("password don't match");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updateClassInfo(String whattoupdate, String value, String email) {
        try {
            PreparedStatement q = connection.prepareStatement("UPDATE " + "user" + " SET " + whattoupdate + "= ? WHERE email = ?");
            q.setString(1, value);
            q.setString(2, email);
            q.executeUpdate();
            q.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void updateClassInfo(String whattoupdate, int value, String email) {
        try {
            PreparedStatement q = connection.prepareStatement("UPDATE " + "user" + " SET " + whattoupdate + "= ? WHERE email = ?");
            q.setInt(1, value);
            q.setString(2, email);
            q.executeUpdate();
            q.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static boolean hasAccount(String email) {
        try {
            PreparedStatement q = connection.prepareStatement("SELECT email FROM user WHERE email = ?");
            q.setString(1, email);
            ResultSet resultat = q.executeQuery();
            boolean hasAccount = resultat.next();
            q.close();
            return hasAccount;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static ArrayList<Class> getClasses() throws SQLException {
        ArrayList<Class> row = new ArrayList<>();
        try {
            PreparedStatement q = connection.prepareStatement("SELECT * FROM class");
            ResultSet rs = q.executeQuery();
            Class Class = null;
            while (rs.next()) {
                classesCount++;
                row.add(new Class(rs.getInt("id"), rs.getString("subject"), rs.getString("start"), rs.getInt("duration")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    public static int getClassesCount() {
        return classesCount;
    }

    public static void createClass(String subject, String professor, String ip, int port, int students, String start, int duration, int level, String cycle, int classe) {
        try {
            PreparedStatement q  = connection.prepareStatement("INSERT INTO class(subject,professor,ip,port,students,start,duration,level,cycle,class) VALUES (?,?,?,?,?,?,?,?,?,?)");
            q.setString(1, subject);
            q.setString(2, professor);
            q.setString(3, ip);
            q.setInt(4, port);
            q.setInt(5, students);
            q.setString(6, start);
            q.setInt(7,duration);
            q.setInt(8,level);
            q.setString(9,cycle);
            q.setInt(10,classe);
            q.execute();
            q.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            if(isConnected()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isConnected() {
        return connection != null;

    }

}
