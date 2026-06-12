package com.group.keencodelabs.numbers;

import com.group.keencodelabs.numbers.dto.ConversionResult;

public class BaseConverter implements NumberConverter {

    /**
     * Converts a number from one base to another.
     * @param value The number to convert.
     * @param fromBase The base of the number to convert.
     * @param toBase The base to convert the number to.
     * @return The converted number.
     */
    @Override
    public ConversionResult convert(String value, int fromBase, int toBase) {
        validateBase(fromBase);
        validateBase(toBase);
        int decimalValue = toDecimal(value, fromBase); // Convert to decimal

        String result = fromDecimal(decimalValue, toBase); // Convert back to the desired base


        return new ConversionResult(result, toBase);
    }


    //=====================================================================//
    // <--- Utility PRIVATE Methods --->
    //=====================================================================//

    private int toDecimal(String value, int fromBase) {

        int sum = 0; // sum of the digits, starting from the rightmost digit.

        int power = 0; // power of the base.

        String normalizedValue = value.toUpperCase(); // normalize to uppercase

        for (int i = normalizedValue.length() - 1; i >=0; i--){ // <-- iterate from the rightmost digit to the leftmost digit.

            char currentChar = normalizedValue.charAt(i); //obtain the char at the current index of str array

            int digit = charToDigit(currentChar); //convert the char to a digit

            sum += digit * (int) Math.pow(fromBase, power); // sum the digit with the result of base raised to the power.

            power++; // increment the power

        }

        return sum;
    }

    private String fromDecimal(int decimalValue, int toBase){
        if (decimalValue == 0) {
            return "0";
        }
        StringBuilder result = new StringBuilder();
        while (decimalValue != 0 ){
            int remainder = decimalValue % toBase;
            result.append(digitToChar(remainder));
            decimalValue /= toBase;
        }
        return result.reverse().toString();
    }


     private int charToDigit(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0'; // returns an integer value of the digit
        }

        if (c >= 'A' && c <= 'Z'){
            return c - 'A' + 10;
        }
        throw new IllegalArgumentException("Invalid character: " + c);
    }

    private char digitToChar(int digit) {
        if (digit >= 0 && digit <= 9){
            return (char) ('0' + digit);
        }

        if (digit >= 10 && digit <= 35){
            return (char) ('A' + digit - 10);
        }

        throw new IllegalArgumentException("Invalid digit: " + digit);
    }

    private void validateBase(int base) {
        if (base < 2 || base > 36) {
            throw new IllegalArgumentException("Base must be between 2 and 36");
        }
    }
}
