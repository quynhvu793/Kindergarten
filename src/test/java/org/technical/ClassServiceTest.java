package org.technical;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClassServiceTest {

    ClassService service;

    @BeforeEach
    void setup() {
        TestDatabaseUtils.resetDatabase();
        service = new ClassService();
    }

    @Test
    void testCreateClassSuccess() throws Exception {
        boolean result = service.createClass(10, "2024-2025");
        assertTrue(result);
    }

    @Test
    void testCreateClassDuplicate() throws Exception {
        service.createClass(1, "2024-2025");
        boolean result = service.createClass(1, "2024-2025");
        assertFalse(result);
    }
}
