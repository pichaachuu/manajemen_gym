package com.manajemengym.kel1.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {

    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/gym-management"; 
            String user = "root";
            String pass = "";

            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
