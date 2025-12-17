package org.technical;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Feature: Recording new student fee of one student")
public class AddStudentFeeTest {
    // Input: registeredStudentId, month, amount, isPaid

    @Test
    void testAddStudentFee_statementCoverage_negativeRegisteredStudentId() {
        StudentFee studentFeeObj = new StudentFee();
        int result = studentFeeObj.addStudentFee(-9, 8, 500000, true, "test");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testAddStudentFee_statementCoverage_invalidMonth() {
        StudentFee studentFeeObj = new StudentFee();
        int result = studentFeeObj.addStudentFee(3, 0, 780000, true, "test");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testAddStudentFee_statementCoverage_invalidAmount() {
        StudentFee studentFeeObj = new StudentFee();
        int result = studentFeeObj.addStudentFee(10, 11, -340000, true, "test");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testAddStudentFee_decisionTable_R1() {
        StudentFee studentFeeObj = new StudentFee();
        int result = studentFeeObj.addStudentFee(1, 10, 930000, true, "test");
        Assertions.assertEquals(1, result);
    }

    @Test
    void testAddStudentFee_decisionTable_R2() {
        StudentFee studentFeeObj = new StudentFee();
        int result = studentFeeObj.addStudentFee(2, 8, -340000, true, "test");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testAddStudentFee_decisionTable_R3() {
        StudentFee studentFeeObj = new StudentFee();
        int result = studentFeeObj.addStudentFee(4, 19, 890000, true, "test");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testAddStudentFee_decisionTable_R4() {
        StudentFee studentFeeObj = new StudentFee();
        int result = studentFeeObj.addStudentFee(3, 21, -9340000, true, "test");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testAddStudentFee_decisionTable_R5() {
        StudentFee studentFeeObj = new StudentFee();
        int result = studentFeeObj.addStudentFee(-321, 10, 987000, true, "test");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testAddStudentFee_decisionTable_R6() {
        StudentFee studentFeeObj = new StudentFee();
        int result = studentFeeObj.addStudentFee(-921, 5, 920000, true, "test");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testAddStudentFee_decisionTable_R7() {
        StudentFee studentFeeObj = new StudentFee();
        int result = studentFeeObj.addStudentFee(-391, -13, 630000, true, "test");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testAddStudentFee_decisionTable_R8() {
        StudentFee studentFeeObj = new StudentFee();
        int result = studentFeeObj.addStudentFee(-39, -13, -230000, true, "test");
        Assertions.assertEquals(0, result);
    }
}
