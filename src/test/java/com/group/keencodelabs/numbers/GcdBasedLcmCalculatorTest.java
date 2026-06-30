package com.group.keencodelabs.numbers;

import com.group.keencodelabs.numbers.dto.LcmResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GcdBasedLcmCalculatorTest {
    private GcdBasedLcmCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new GcdBasedLcmCalculator();
    }

    @ParameterizedTest
    @CsvSource({
            "4, 6, 12",
            "21, 6, 42",
            "8, 12, 24",
            "13, 17, 221",
            "10, 10, 10"
    })
    void calculate_withPositiveNumbers_returnsLeastCommonMultiple(int firstNumber, int secondNumber, int expectedLcm) {
        assertEquals(expectedLcm, calculator.calculate(firstNumber, secondNumber));
    }

    @ParameterizedTest
    @CsvSource({
            "0, 5",
            "12, 0",
            "0, 0"
    })
    void calculate_withZero_returnsZero(int firstNumber, int secondNumber) {
        assertEquals(0, calculator.calculate(firstNumber, secondNumber));
    }

    @ParameterizedTest
    @CsvSource({
            "-4, 6, 12",
            "4, -6, 12",
            "-4, -6, 12"
    })
    void calculate_withNegativeNumbers_returnsNonNegativeLeastCommonMultiple(int firstNumber, int secondNumber, int expectedLcm) {
        assertEquals(expectedLcm, calculator.calculate(firstNumber, secondNumber));
    }

    @Test
    void calculateWithSteps_returnsOriginalInputsAndGcdUsed() {
        LcmResult result = calculator.calculateWithSteps(21, 6);

        assertEquals(21, result.firstNumber());
        assertEquals(6, result.secondNumber());
        assertEquals(42, result.lcm());
        assertEquals(3, result.gcd());
        assertEquals(3, result.stepsPerformed());
    }
}
