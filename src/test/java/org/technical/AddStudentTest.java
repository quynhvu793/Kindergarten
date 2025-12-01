package org.technical;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Statement;

public class AddStudentTest {

    @BeforeAll
    public static void setup() throws Exception {
        DatabaseHelper.initializeDatabase();

        try (Connection conn = DatabaseHelper.getConnection();
             Statement st = conn.createStatement()) {
            st.execute("DELETE FROM students");
        }
    }

    @Test
    public void testValidAddStudent() {
        StudentManager sm = new StudentManager();
        Student s = sm.addStudent("Alice", 5);

        assertEquals("Alice", s.getName());
        assertEquals(5, s.getAge());
        assertTrue(s.getId() > 0);
    }

    @Test
    public void testInvalidName() {
        StudentManager sm = new StudentManager();
        assertThrows(IllegalArgumentException.class, () -> {
            sm.addStudent("", 4);
        });
    }

    @Test
    public void testInvalidAgeLow() {
        StudentManager sm = new StudentManager();
        assertThrows(IllegalArgumentException.class, () -> {
            sm.addStudent("Bob", 2);
        });
    }

    @Test
    public void testTrimNameBranch() {
        StudentManager sm = new StudentManager();
        Student s = sm.addStudent("   Tom   ", 6);
        assertEquals("Tom", s.getName());
    }
}
