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
        return calculateWithSteps(firstNumber, secondNumber).gcd();
    }

    /**
     * Computes the greatest common divisor and reports how many division steps were used.
     * @param firstNumber The first number.
     * @param secondNumber The second number.
     * @return GcdResult
     */
    @Override
    public GcdResult calculateWithSteps(int firstNumber, int secondNumber) {
        validateInput(firstNumber, secondNumber);

        long a = absoluteAsLong(firstNumber);
        long b = absoluteAsLong(secondNumber);
        int steps = 0;

        while (b != 0) {
            long remainder = a % b;
            a = b;
            b = remainder;
            steps++;
        }

        return new GcdResult(firstNumber, secondNumber, Math.toIntExact(a), steps);
    }

    private void validateInput(int firstNumber, int secondNumber) {
        if (firstNumber == 0 && secondNumber == 0) {
            throw new IllegalArgumentException("GCD is undefined for 0 and 0");
        }
    }

    private long absoluteAsLong(int number) {
        return Math.abs((long) number);
    }
}
