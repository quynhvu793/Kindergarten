package org.technical;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentRegistrationService {

    public boolean registerStudent(int studentId, int classId) {

        String checkDuplicateSql = """
            SELECT 1
            FROM StudentByClass
            WHERE studentId = ? AND registeredClassId = ?
        """;

        String capacitySql = """
            SELECT c.studentCount, ci.maxStudentCount
            FROM Classes c
            JOIN ClassInfo ci ON c.classId = ci.id
            WHERE c.id = ? AND c.isActive = 1
        """;

        String insertSql = """
            INSERT INTO StudentByClass(studentId, registeredClassId)
            VALUES (?, ?)
        """;

        String updateCountSql = """
            UPDATE Classes
            SET studentCount = studentCount + 1
            WHERE id = ?
        """;

        try (Connection conn = Database.getConnection()) {

            /* 1️⃣ Check duplicate */
            try (PreparedStatement ps = conn.prepareStatement(checkDuplicateSql)) {
                ps.setInt(1, studentId);
                ps.setInt(2, classId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return false;
                }
            }

            /* 2️⃣ Check capacity */
            int currentCount;
            int maxCount;

            try (PreparedStatement ps = conn.prepareStatement(capacitySql)) {
                ps.setInt(1, classId);
                ResultSet rs = ps.executeQuery();

                if (!rs.next()) {
                    return false; // class not found or inactive
                }

                currentCount = rs.getInt("studentCount");
                maxCount = rs.getInt("maxStudentCount");

                if (currentCount >= maxCount) {
                    return false;
                }
            }

            /* 3️⃣ Insert StudentByClass */
            try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                ps.setInt(1, studentId);
                ps.setInt(2, classId);
                ps.executeUpdate();
            }

            /* 4️⃣ Update studentCount */
            try (PreparedStatement ps = conn.prepareStatement(updateCountSql)) {
                ps.setInt(1, classId);
                ps.executeUpdate();
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
