package com.group.keencodelabs.numbers;

import com.group.keencodelabs.numbers.dto.PrimeCheckerResult;

public class TrialDivisionPrimeChecker implements PrimeChecker {
    int stepCount = 0;

    public static void main(String[] args) {
        TrialDivisionPrimeChecker checker = new TrialDivisionPrimeChecker();

        int[] testNumbers = {17, 1000, 10007, 100003, 1000003};

        for (int number : testNumbers){
            long startTime = System.nanoTime();
            boolean result = checker.isPrime(number);
            long endTime = System.nanoTime();

            System.out.printf("Number: %d | Prime: %s | Steps: %d | Time: %.3f ms%n",
                    number, result, checker.getStepCount(), (endTime - startTime) / 1_000_000.0);
        }
    }

    public int getStepCount() {
        return stepCount;
    }


    /**
     * Checks if a number is prime.
     * @param number The number to check.
     * @return True if the number is prime, false otherwise.
     */
    @Override
    public boolean isPrime(int number) {
        stepCount = 0;
        if (number <= 1) { return false; }
        for (int i = 2; i < number; i++) {
            stepCount++;
            if (number % i == 0) { return false; }
        }
        return true;
    }

    /**
     * Optimized version of isPrime that returns the first factor that divides the number.
     * @param number The number to check.
     * @return PrimeCheckerResult
     */
    @Override
    public PrimeCheckerResult isPrimeOptimized(int number) {
        stepCount = 0;
        if (number <= 1) return new PrimeCheckerResult(number, false, null, 0);
        if (number == 2) return new PrimeCheckerResult(number, true, null, 0);
        if (number % 2 == 0) return new PrimeCheckerResult(number, false, 2, 1);


        int sqrt = (int) Math.sqrt(number);

        for (int i = 3; i <= sqrt; i++) {
            if (number % i == 0) return new PrimeCheckerResult(number, false, i, stepCount);
            stepCount++;
        }
        return new PrimeCheckerResult(number, true, null, stepCount);
    }
}
