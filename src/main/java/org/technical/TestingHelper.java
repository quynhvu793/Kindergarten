package org.technical;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestingHelper {

    public static Connection getConnection(String env) {
        String connectionString = "jdbc:sqlite:";

        if (env == "prod") {
            connectionString += "kindergarten.db";
        } else if (env == "test") {
            connectionString += "test_kindergarten.db";
        }

        try {
            Connection conn = DriverManager.getConnection(connectionString);
            System.out.println("Connected to DB successfully");

            return conn;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB: " + e.getMessage());
            return null;
        }
    }
}
