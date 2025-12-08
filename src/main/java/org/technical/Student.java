package org.technical;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Student {

	private int id;
	private String fullName;
	private int age;
	private boolean gender;

	public Student(int id, String fullName, int age, boolean gender) {
		this.id = id;
		this.fullName = fullName;
		this.age = age;
		this.gender = gender;
	}

	public Student(String fullName, int age, boolean gender) {
		this.fullName = fullName;
		this.age = age;
		this.gender = gender;
	}

	public int addStudent() {
		try {
			Connection conn = Database.getConnection();

			if (conn == null) {
				return -1;
			}

			String sql = "INSERT INTO Student(fullName, age, gender) VALUES(?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, this.fullName);
			pstmt.setInt(2, this.age);
			pstmt.setBoolean(3, this.gender);

			pstmt.executeUpdate();
			ResultSet recordSet = pstmt.getGeneratedKeys();
			this.id = recordSet.next() ? recordSet.getInt(1) : 0;
			return this.id;
		} catch (Exception e) {
			return -1;
		}
	}

	public List<Student> searchForStudents(String name) {
		List<Student> studentList = new ArrayList<>();

		if (name == null) {
			return studentList;
		}

		String sql = "SELECT * FROM students WHERE LOWER(fullName) LIKE ?";

		try {
			Connection conn = Database.getConnection();

			if (conn == null) {
				return null;
			}

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + name.toLowerCase() + "%");

			ResultSet returnedList = ps.executeQuery();
			while (returnedList.next()) {
				studentList.add(new Student(
					returnedList.getInt("id"),
					returnedList.getString("fullName"),
					returnedList.getInt("age"),
					returnedList.getBoolean("gender")
				));
			}

			return studentList;
		} catch (Exception e) {
			return new ArrayList<Student>();
		}
	}

	public boolean updateStudentInfo(int studentId, String fullName, int age, boolean gender) {
		String sql = "UPDATE Student SET fullName = ?, age = ?, gender = ? WHERE id = ?";

		try {
			Connection conn = Database.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, fullName);
			stmt.setInt(2, age);
			stmt.setBoolean(3, gender);
			stmt.setInt(4, studentId);

			int returnedResult = stmt.executeUpdate();

			return returnedResult > 0;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteStudent(int studentId) {
		String sql = "DELETE FROM Student WHERE id = ?";

		try {
			Connection conn = Database.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);

			int returnedResult = stmt.executeUpdate();

			return returnedResult > 0;
		} catch (Exception e) {
			return false;
		}
	}
}
