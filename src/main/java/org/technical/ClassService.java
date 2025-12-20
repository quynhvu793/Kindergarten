package org.technical;

import java.sql.*;

public class ClassService {

    public boolean createClass(int classInfoId, String schoolYear) throws SQLException {

        try (Connection conn = Database.getConnection()) {

            String checkSql = """
            SELECT COUNT(*) FROM Classes
            WHERE classId = ? AND schoolYear = ?
        """;

            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setInt(1, classInfoId);
                checkStmt.setString(2, schoolYear);

                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    return false;
                }
            }

            String insertSql = """
            INSERT INTO Classes(classId, schoolYear, studentCount, isActive)
            VALUES (?, ?, 0, 1)
        """;

            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                insertStmt.setInt(1, classInfoId);
                insertStmt.setString(2, schoolYear);
                insertStmt.executeUpdate();
            }

            return true;
        }
    }
}
