package com.group.keencodelabs.numbers;

import com.group.keencodelabs.numbers.dto.GcdResult;
import com.group.keencodelabs.numbers.dto.LcmResult;

public class GcdBasedLcmCalculator implements LcmCalculator {
    private final GcdCalculator gcdCalculator;

    public GcdBasedLcmCalculator() {
        this(new EuclideanGcdCalculator());
    }

    public GcdBasedLcmCalculator(GcdCalculator gcdCalculator) {
        this.gcdCalculator = gcdCalculator;
    }

    /**
     * Computes the least common multiple using gcd(a, b).
     * @param firstNumber The first number.
     * @param secondNumber The second number.
     * @return The least common multiple.
     */
    @Override
    public int calculate(int firstNumber, int secondNumber) {
        return calculateWithSteps(firstNumber, secondNumber).lcm();
    }

    /**
     * Computes the least common multiple and reports the GCD used by the calculation.
     * @param firstNumber The first number.
     * @param secondNumber The second number.
     * @return LcmResult
     */
    @Override
    public LcmResult calculateWithSteps(int firstNumber, int secondNumber) {
        if (firstNumber == 0 || secondNumber == 0) {
            return new LcmResult(firstNumber, secondNumber, 0, 0, 0);
        }

        GcdResult gcdResult = gcdCalculator.calculateWithSteps(firstNumber, secondNumber);
        int gcd = gcdResult.gcd();
        long lcm = (Math.abs((long) firstNumber) / gcd) * Math.abs((long) secondNumber);

        return new LcmResult(firstNumber, secondNumber, Math.toIntExact(lcm), gcd, gcdResult.stepsPerformed() + 1);
    }
}
