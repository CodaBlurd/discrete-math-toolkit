package com.group.keencodelabs.numbers;

import com.group.keencodelabs.numbers.dto.GcdResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class EuclideanGcdCalculatorTest {
    private EuclideanGcdCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new EuclideanGcdCalculator();
    }

    @ParameterizedTest
    @CsvSource({
            "54, 24, 6",
            "48, 18, 6",
            "17, 13, 1",
            "100, 10, 10",
            "81, 27, 27"
    })
    void calculate_withPositiveNumbers_returnsGreatestCommonDivisor(int firstNumber, int secondNumber, int expectedGcd) {
        assertEquals(expectedGcd, calculator.calculate(firstNumber, secondNumber));
    }

    @ParameterizedTest
    @CsvSource({
            "0, 5, 5",
            "12, 0, 12",
            "-54, 24, 6",
            "54, -24, 6",
            "-54, -24, 6"
    })
    void calculate_withZeroOrNegativeNumbers_returnsNonNegativeGreatestCommonDivisor(int firstNumber, int secondNumber, int expectedGcd) {
        assertEquals(expectedGcd, calculator.calculate(firstNumber, secondNumber));
    }

    @Test
    void calculateWithSteps_returnsOriginalInputsAndStepCount() {
        GcdResult result = calculator.calculateWithSteps(48, 18);

        assertEquals(48, result.firstNumber());
        assertEquals(18, result.secondNumber());
        assertEquals(6, result.gcd());
        assertEquals(3, result.stepsPerformed());
    }

    @Test
    void calculate_withBothNumbersZero_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(0, 0));
    }
}
