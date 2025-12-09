package org.technical;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection getConnection(String env) {
        String connectionString = "jdbc:sqlite:";

        if (env == "prod") {
            connectionString += "kindergarten.db";
        } else if (env == "test") {
            connectionString += "test_kindergarten.db";
        }

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
