package org.technical;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaxCalculatorTest {
    @Test
    void testIncomeBelow5M() {
        assertEquals(0.2, TaxCalculator.calculateTax(4), 0.001);
    }

    @Test
    void testIncomeBetween5And10M() {
        assertEquals(0.7, TaxCalculator.calculateTax(7), 0.001);
    }

    @Test
    void testIncomeBetween10And18M() {
        assertEquals(2.25, TaxCalculator.calculateTax(15), 0.001);
    }

    @Test
    void testIncomeBetween18And32M() {
        assertEquals(5.0, TaxCalculator.calculateTax(25), 0.001);
    }

    @Test
    void testIncomeBetween52And80M() {
        assertEquals(21.0, TaxCalculator.calculateTax(70), 0.001);
    }

    @Test
    void testNegativeIncomeThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TaxCalculator.calculateTax(-10);
        });
        assertEquals("Thu nhập không hợp lệ!", exception.getMessage());
    }
}
