package org.technical;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentTest {

    @BeforeAll
    static void setupDatabase() {
        try {
            Connection conn = Database.getConnection("test");
            Statement stmt = conn.createStatement();

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS Student (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    fullName TEXT NOT NULL,
                    age INTEGER,
                    gender BOOLEAN
                );
            """);

            stmt.execute("DELETE FROM Student;");
        } catch (Exception e) {
            fail("Database setup failed: " + e.getMessage());
        }
    }

    @Test
    @Order(1)
    void testAddStudent_statementCoverage() {
        Student s = new Student("Alice", 5, true);
        int id = s.addStudent("test");

        assertTrue(id > 0);
    }

    @Test
    @Order(2)
    void testAddStudent_equivalencePartitions() {
        Student valid = new Student("Bob", 10, false);
        assertTrue(valid.addStudent("test") > 0);

        Student invalid = new Student("", 5, true);
        assertEquals(-1, (Integer) invalid.addStudent("test"));
    }

    @Test
    @Order(3)
    void testAddStudent_boundaryValues() {
        Student s = new Student("Boundary", 0, true);
        int id = s.addStudent("test");

        assertTrue(id > 0);
    }

    @Test
    @Order(4)
    void testSearch_statementCoverage() {
        Student s = new Student("SearchTarget", 11, true);
        s.addStudent("test");

        List<Student> results = s.searchForStudents("search", "test");

        assertFalse(results.isEmpty());
    }

    @Test
    @Order(5)
    void testSearch_path_nullName() {
        Student s = new Student("Example", 7, false);
        assertTrue(s.searchForStudents(null, "test").isEmpty());
    }

    @Test
    @Order(6)
    void testSearch_equivalenceClasses() {
        Student s = new Student("Mark Twain", 14, true);
        s.addStudent("test");

        assertEquals(1, s.searchForStudents("Mark", "test").size());
        assertEquals(0, (Integer) s.searchForStudents("XYZ", "test").size());
    }

    @Test
    @Order(7)
    void testUpdate_statementCoverage() {
        Student s = new Student("BeforeUpdate", 20, true);
        int id = s.addStudent("test");

        boolean result = s.updateStudentInfo(id, "AfterUpdate", 22, false, "test");

        assertTrue(result);
    }

    @Test
    @Order(8)
    void testUpdate_boundaryValues() {
        Student s = new Student("Dummy", 8, true);

        assertFalse(s.updateStudentInfo(0, "X", 1, false, "test"));
        assertFalse(s.updateStudentInfo(-1, "Y", 2, true, "test"));
    }

    @Test
    @Order(9)
    void testUpdate_decisionTable() {
        Student s = new Student("DT Before", 15, true);
        int id = s.addStudent("test");

        assertTrue(s.updateStudentInfo(id, "DT After", 16, false, "test"));
    }

    @Test
    @Order(10)
    void testDelete_statementCoverage() {
        Student s = new Student("DeleteMe", 13, false);
        int id = s.addStudent("test");

        assertTrue(s.deleteStudent(id, "test"));
    }

    @Test
    @Order(11)
    void testDelete_equivalenceClasses() {
        Student s = new Student("EP", 12, true);
        int id = s.addStudent("test");

        assertTrue(s.deleteStudent(id, "test"));
        assertFalse(s.deleteStudent(99999, "test")); 
    }

    @Test
    @Order(12)
    void testDelete_boundary() {
        Student s = new Student("Boundary", 12, true);

        assertFalse(s.deleteStudent(0, "test"));
        assertFalse(s.deleteStudent(-10, "test"));
    }
}
