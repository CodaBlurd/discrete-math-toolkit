package com.group.keencodelabs.numbers.dto;

public record PrimeCheckerResult(int number, boolean prime, Integer firstDivisor, int checksPerformed) { }
