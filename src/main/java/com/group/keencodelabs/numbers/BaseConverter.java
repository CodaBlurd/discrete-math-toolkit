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
        String normalizedValue = value.toUpperCase();

        // Check if the value contains a decimal point and handle the edge case.
        if (normalizedValue.contains(".")) {
            double decimalValue = handleToDecimalFractions(normalizedValue, fromBase);
            String result = handleFromDecimalFractions(decimalValue, toBase);
            return new ConversionResult(result, toBase);
        }
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

    private String handleFromDecimalFractions(double decimalValue, int toBase) {
        int wholePart = (int) decimalValue;
        double fractionPart = decimalValue - wholePart;

        String wholeResult = fromDecimal(wholePart, toBase);

        if (fractionPart == 0) {
            return wholeResult; // early return
        }

        StringBuilder fractionResult = new StringBuilder();

        int maxFractionDigits = 10; // prevents infinite loops

        while ((fractionPart > 0) && (fractionResult.length() < maxFractionDigits)) {

            fractionPart *= toBase;

            int digit = (int) fractionPart; // get the next digit

            fractionResult.append(digitToChar(digit)); // append the digit to the result

            fractionPart -= digit; // remove the used digit
        }

        return wholeResult + "." + fractionResult;
    }

    private double handleToDecimalFractions(String normalizedValue, int fromBase){
            double tempSum = 0;
            String[] split = normalizedValue.split("\\."); // split the string at the decimal point
            String wholePart = split[0];
            String fractionPart = split[1];
            int wholePower = 0;

            for (int i = wholePart.length() - 1; i >= 0; i--) {
                char currentChar = wholePart.charAt(i);
                int digit = charToDigit(currentChar);
                tempSum += digit * Math.pow(fromBase, wholePower);
                wholePower++;
            }


            int fractionPower = -fractionPart.length();
            for (int i = fractionPart.length() - 1; i >= 0; i--) {
                char currentChar = fractionPart.charAt(i);
                int digit = charToDigit(currentChar);
                tempSum += digit * Math.pow(fromBase, fractionPower);
                fractionPower++;
            }

            return tempSum;


    }
}
