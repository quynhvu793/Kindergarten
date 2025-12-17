package org.technical;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Feature: Recording attendance of one student in a class")
public class AddAttendanceTest {
    // Input: registeredStudentId, isAbsent, date

    @Test
    void testAddAttendance_statementCoverage_negative_studentId() {
        Attendance attendanceObj = new Attendance();
        int result = attendanceObj.addAttendance(-3, false, "2024-12-18", "test");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testAddAttendance_statementCoverage_negative_invalidDateFormat() {
        Attendance attendanceObj = new Attendance();
        int result = attendanceObj.addAttendance(4, true, "202-09-19", "test");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testAddAttendance_decisionTable_R1() {
        Attendance attendanceObj = new Attendance();

        int result = attendanceObj.addAttendance(1, true, "2022-10-21", "test");
        Assertions.assertEquals(1, result);
    }

    @Test
    void testAddAttendance_decisionTable_R2() {
        Attendance attendanceObj = new Attendance();
        int result = attendanceObj.addAttendance(2, true, "202-07-22", "test");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testAddAttendance_decisionTable_R3() {
        Attendance attendanceObj = new Attendance();
        int result = attendanceObj.addAttendance(-7, true, "2022-10-21", "test");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testAddAttendance_decisionTable_R4() {
        Attendance attendanceObj = new Attendance();
        int result = attendanceObj.addAttendance(-9, true, "2022-100-21", "test");
        Assertions.assertEquals(0, result);
    }
}
