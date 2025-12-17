package org.technical;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Student {

    private int id;
    private String fullName;
    private int age;
    private boolean gender;

    public Student() {

    }

    public int updateStudentInfo(int id, String newFullName, int newAge, boolean newGender, String env) {
        if (id <= 0) {
            return 0;
        }

        if (newFullName.isBlank()) {
            return 0;
        }

        if (newAge < 3 || newAge > 5) {
            return 0;
        }

        String queryStr = "select * from Student where id = ?";
        String updateStr = "update Student set fullName = ?, age = ?, gender = ? where id = ?";

        try (Connection conn = TestingHelper.getConnection(env); PreparedStatement statement1 = conn.prepareStatement(queryStr);) {

            statement1.setInt(1, id);
            ResultSet result1 = statement1.executeQuery();

            if (!result1.next()) {
                return 0;
            }

            PreparedStatement statement2 = conn.prepareStatement(updateStr);
            statement2.setString(1, newFullName);
            statement2.setInt(2, newAge);
            statement2.setBoolean(3, newGender);
            statement2.setInt(4, id);
            int result2 = statement2.executeUpdate();

            return result2;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
}
