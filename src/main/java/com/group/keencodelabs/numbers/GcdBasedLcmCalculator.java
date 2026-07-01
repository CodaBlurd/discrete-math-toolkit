package com.group.keencodelabs.numbers;

import com.group.keencodelabs.numbers.dto.LcmResult;

public class GcdBasedLcmCalculator implements LcmCalculator {

    /**
     * Computes the least common multiple using gcd(a, b).
     * @param firstNumber The first number.
     * @param secondNumber The second number.
     * @return The least common multiple.
     */
    @Override
    public int calculate(int firstNumber, int secondNumber) {
        return 0;
    }

    /**
     * Computes the least common multiple and reports the GCD used by the calculation.
     * @param firstNumber The first number.
     * @param secondNumber The second number.
     * @return LcmResult
     */
    @Override
    public LcmResult calculateWithSteps(int firstNumber, int secondNumber) {
        return new LcmResult(firstNumber, secondNumber, 0, 0, 0);
    }
}
