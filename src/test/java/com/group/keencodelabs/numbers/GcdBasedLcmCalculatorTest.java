package com.group.keencodelabs.numbers;

import com.group.keencodelabs.numbers.dto.LcmResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GcdBasedLcmCalculatorTest {
    private GcdBasedLcmCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new GcdBasedLcmCalculator();
    }

    @Test
    void calculate_scaffoldReturnsDefaultValue() {
        assertEquals(0, calculator.calculate(21, 6));
    }

    @Test
    void calculateWithSteps_scaffoldReturnsResultShape() {
        LcmResult result = calculator.calculateWithSteps(21, 6);

        assertNotNull(result);
        assertEquals(21, result.firstNumber());
        assertEquals(6, result.secondNumber());
        assertEquals(0, result.lcm());
        assertEquals(0, result.gcd());
        assertEquals(0, result.stepsPerformed());
    }
}
