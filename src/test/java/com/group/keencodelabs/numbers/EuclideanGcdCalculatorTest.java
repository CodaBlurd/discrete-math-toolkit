package com.group.keencodelabs.numbers;

import com.group.keencodelabs.numbers.dto.GcdResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EuclideanGcdCalculatorTest {
    private EuclideanGcdCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new EuclideanGcdCalculator();
    }

    @Test
    void calculate_scaffoldReturnsDefaultValue() {
        assertEquals(0, calculator.calculate(48, 18));
    }

    @Test
    void calculateWithSteps_scaffoldReturnsResultShape() {
        GcdResult result = calculator.calculateWithSteps(48, 18);

        assertNotNull(result);
        assertEquals(48, result.firstNumber());
        assertEquals(18, result.secondNumber());
        assertEquals(0, result.gcd());
        assertEquals(0, result.stepsPerformed());
    }
}
