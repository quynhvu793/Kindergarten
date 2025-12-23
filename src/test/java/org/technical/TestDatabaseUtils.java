package org.technical;

import java.sql.Connection;
import java.sql.Statement;

public class TestDatabaseUtils {

    public static void resetDatabase() {
        try (Connection conn = Database.getConnection(); Statement stmt = conn.createStatement()) {

            stmt.execute("DELETE FROM StudentByClass;");
            stmt.execute("DELETE FROM Classes;");
            stmt.execute("DELETE FROM ClassInfo;");

            // Insert ClassInfo
            stmt.execute("""
                INSERT INTO ClassInfo(id, name, maxStudentCount)
                VALUES (1, 'Class A Info', 30);
            """);

            // Insert Classes
            stmt.execute("""
                INSERT INTO Classes(
                    id,
                    classId,
                    schoolYear,
                    studentCount,
                    isActive
                )
                VALUES (1, 1, '2024-2025', 0, 1);
            """);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw new RuntimeException("Reset DB failed", e);
        }
    }
}
