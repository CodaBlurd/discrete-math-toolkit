package com.group.keencodelabs.numbers;

import com.group.keencodelabs.numbers.dto.GcdResult;

public class EuclideanGcdCalculator implements GcdCalculator {

    /**
     * Computes the greatest common divisor using the Euclidean algorithm.
     * @param firstNumber The first number.
     * @param secondNumber The second number.
     * @return The greatest common divisor.
     */
    @Override
    public int calculate(int firstNumber, int secondNumber) {
        return 0;
    }

    /**
     * Computes the greatest common divisor and reports how many division steps were used.
     * @param firstNumber The first number.
     * @param secondNumber The second number.
     * @return GcdResult
     */
    @Override
    public GcdResult calculateWithSteps(int firstNumber, int secondNumber) {
        return new GcdResult(firstNumber, secondNumber, 0, 0);
    }
}
