package org.technical;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.technical.StudentRegistrationService;
import org.technical.TestDatabaseUtils;

import static org.junit.jupiter.api.Assertions.*;

public class StudentRegistrationServiceTest {

    StudentRegistrationService service;

    @BeforeEach
    void setup() {
        TestDatabaseUtils.resetDatabase();
        service = new StudentRegistrationService();
    }

    @Test
    void testRegisterStudentSuccess() throws Exception {
        boolean result = service.registerStudent(1, 1);
        assertTrue(result);
    }

    @Test
    void testRegisterStudentDuplicate() throws Exception {
        service.registerStudent(1, 1);
        boolean result = service.registerStudent(1, 1);
        assertFalse(result);
    }
}
