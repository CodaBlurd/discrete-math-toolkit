package com.group.keencodelabs.numbers;


import com.group.keencodelabs.numbers.dto.ConversionResult;

public interface NumberConverter {
    ConversionResult convert(String value, int fromBase, int toBase);
}
