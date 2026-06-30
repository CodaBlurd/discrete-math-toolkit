package com.group.keencodelabs.numbers;

import com.group.keencodelabs.numbers.dto.GcdResult;

public interface GcdCalculator {
    int calculate(int firstNumber, int secondNumber);
    GcdResult calculateWithSteps(int firstNumber, int secondNumber);
}
