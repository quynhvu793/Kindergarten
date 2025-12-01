package org.technical;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class SearchStudentTest {

    @BeforeAll
    public static void setup() throws Exception {
        DatabaseHelper.initializeDatabase();

        try (Connection conn = DatabaseHelper.getConnection();
             Statement st = conn.createStatement()) {

            st.execute("DELETE FROM students");
            st.execute("INSERT INTO students(name, age) VALUES('Alice', 5)");
            st.execute("INSERT INTO students(name, age) VALUES('Alex', 4)");
            st.execute("INSERT INTO students(name, age) VALUES('Bob', 6)");
        }
    }

    @Test
    public void testSearchFound() {
        StudentManager sm = new StudentManager();
        List<Student> list = sm.searchByName("Al");
        assertEquals(2, list.size());
    }

    @Test
    public void testSearchNotFound() {
        StudentManager sm = new StudentManager();
        List<Student> list = sm.searchByName("zzz");
        assertTrue(list.isEmpty());
    }

    @Test
    public void testNullKeyword() {
        StudentManager sm = new StudentManager();
        List<Student> list = sm.searchByName(null);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testCaseInsensitiveBranch() {
        StudentManager sm = new StudentManager();
        List<Student> list = sm.searchByName("aLi");

        assertEquals(1, list.size());
        assertEquals("Alice", list.get(0).getName());
    }
}
