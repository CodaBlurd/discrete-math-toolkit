package com.group.keencodelabs.numbers;

import com.group.keencodelabs.numbers.dto.LcmResult;

public interface LcmCalculator {
    int calculate(int firstNumber, int secondNumber);
    LcmResult calculateWithSteps(int firstNumber, int secondNumber);
}
