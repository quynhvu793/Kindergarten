package org.technical;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection getConnection() {
        String connectionString = "jdbc:sqlite:Kindergarten.db";

        try {
            Connection conn = DriverManager.getConnection(connectionString);
            System.out.println("Kết nối DB thành công!");
            return conn;
        } catch (SQLException e) {
            System.out.println("Kết nối DB thất bại: " + e.getMessage());
            return null;
        }
    }
}
