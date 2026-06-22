package com.group.keencodelabs.numbers;

public class TrialDivisionPrimeChecker implements PrimeChecker {

    public static void main(String[] args) {
        TrialDivisionPrimeChecker baseOperation = new TrialDivisionPrimeChecker();
        System.out.println(baseOperation.isPrime(13));
    }


    /**
     * Checks if a number is prime.
     * @param number The number to check.
     * @return True if the number is prime, false otherwise.
     */
    @Override
    public boolean isPrime(int number) {
        if (number <= 1) { return false; }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) { return false; }
        }
        return true;
    }
}
