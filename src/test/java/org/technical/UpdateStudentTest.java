package org.technical;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Feature: Updating student information")
public class UpdateStudentTest {
    // Input: id, newFullName, newAge, newGender

    @Test
    void testUpdateStudent_statementCoverage_negativeId() {
        Student studentObj = new Student();
        int result = studentObj.updateStudentInfo(-83, "Trần Bảo Ngọc", 4, false, "test");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testUpdateStudent_statementCoverage_blankFullName() {
        Student studentObj = new Student();
        int result = studentObj.updateStudentInfo(3, "", 3, true, "test");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testUpdateStudent_statementCoverage_invalidAge() {
        Student studentObj = new Student();
        int result = studentObj.updateStudentInfo(5, "Nguyễn Trung Đức", 2, true, "test");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testUpdateStudent_decisionTable_R1() {
        Student studentObj = new Student();
        int result = studentObj.updateStudentInfo(2, "Nguyễn Trung Đức", 5, true, "test");
        Assertions.assertEquals(1, result);
    }

    @Test
    void testUpdateStudent_decisionTable_R2() {
        Student studentObj = new Student();
        int result = studentObj.updateStudentInfo(2, "Nguyễn Trung Đức", 7, true, "test");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testUpdateStudent_decisionTable_R3() {
        Student studentObj = new Student();
        int result = studentObj.updateStudentInfo(2, "", 5, true, "test");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testUpdateStudent_decisionTable_R4() {
        Student studentObj = new Student();
        int result = studentObj.updateStudentInfo(2, "", 8, true, "test");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testUpdateStudent_decisionTable_R5() {
        Student studentObj = new Student();
        int result = studentObj.updateStudentInfo(-832, "Nguyễn Trung Đức", 5, true, "test");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testUpdateStudent_decisionTable_R6() {
        Student studentObj = new Student();
        int result = studentObj.updateStudentInfo(-322, "Nguyễn Trung Đức", 9, true, "test");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testUpdateStudent_decisionTable_R7() {
        Student studentObj = new Student();
        int result = studentObj.updateStudentInfo(-392, "", 5, true, "test");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testUpdateStudent_decisionTable_R8() {
        Student studentObj = new Student();
        int result = studentObj.updateStudentInfo(-922, "", 10, true, "test");
        Assertions.assertEquals(0, result);
    }
}
