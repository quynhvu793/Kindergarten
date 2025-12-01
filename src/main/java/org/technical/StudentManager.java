package org.technical;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    public Student addStudent(String name, int age) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty");
        if (age < 3 || age > 6)
            throw new IllegalArgumentException("Age must be between 3 and 6");

        String sql = "INSERT INTO students(name, age) VALUES(?, ?)";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, name.trim());
            ps.setInt(2, age);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            int id = rs.next() ? rs.getInt(1) : -1;

            return new Student(id, name.trim(), age);

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting student", e);
        }
    }

    public List<Student> searchByName(String keyword) {
        List<Student> list = new ArrayList<>();

        if (keyword == null) return list;

        String sql = "SELECT * FROM students WHERE LOWER(name) LIKE ?";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword.toLowerCase() + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error searching students", e);
        }

        return list;
    }
}
