package com.group.keencodelabs.numbers;

import com.group.keencodelabs.numbers.dto.PrimeCheckerResult;

public interface PrimeChecker {
    boolean isPrime(int number);
    PrimeCheckerResult isPrimeOptimized(int number);
}
