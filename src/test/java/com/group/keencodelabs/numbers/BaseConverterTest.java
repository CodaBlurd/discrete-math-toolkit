package com.group.keencodelabs.numbers;

import com.group.keencodelabs.numbers.dto.ConversionResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class BaseConverterTest {

    private BaseConverter converter;

    @BeforeEach
    void setUp() {
        converter = new BaseConverter();
    }

    // ====================== HAPPY PATH TESTS ======================

    @Nested
    @DisplayName("Happy Path - Common Conversions")
    class HappyPathTests {
        @Test
        @DisplayName("Binary to Decimal: 1010 -> 10")
        void binaryToDecimal(){
            ConversionResult result = converter.convert("1010", 2, 10);
            assertEquals("10", result.value());
            assertEquals(10, result.base());
        }

        @Test
        @DisplayName("Decimal to Binary: 10 → 1010")
        void decimalToBinary() {
            ConversionResult result = converter.convert("10", 10, 2);
            assertEquals("1010", result.value());
            assertEquals(2, result.base());
        }

        @Test
        @DisplayName("Decimal to Hexadecimal: 255 → FF")
        void decimalToHex() {
            ConversionResult result = converter.convert("255", 10, 16);
            assertEquals("FF", result.value());
        }

        @Test
        @DisplayName("Hexadecimal to Decimal: FF → 255")
        void hexToDecimal() {
            ConversionResult result = converter.convert("FF", 16, 10);
            assertEquals("255", result.value());
        }

        @Test
        @DisplayName("Octal to Decimal: 17 → 15")
        void octalToDecimal() {
            ConversionResult result = converter.convert("17", 8, 10);
            assertEquals("15", result.value());
        }
    }

    // =========================================================================
    // PARAMETERIZED TESTS - Test many similar cases efficiently
    // =========================================================================

    @Nested
    @DisplayName("Parameterized Conversion Tests")
    class ParameterizedTests {

        @ParameterizedTest(name = "Decimal {0} to Binary = {1}")
        @CsvSource({
                "0, 0",
                "1, 1",
                "2, 10",
                "7, 111",
                "8, 1000",
                "15, 1111",
                "16, 10000",
                "255, 11111111"
        })
        void decimalToBinaryConversions(String decimal, String expectedBinary) {
            ConversionResult result = converter.convert(decimal, 10, 2);
            assertEquals(expectedBinary, result.value());
        }

        @ParameterizedTest(name = "Decimal {0} to Hex = {1}")
        @CsvSource({
                "0, 0",
                "9, 9",
                "10, A",
                "15, F",
                "16, 10",
                "255, FF",
                "256, 100"
        })
        void decimalToHexConversions(String decimal, String expectedHex) {
            ConversionResult result = converter.convert(decimal, 10, 16);
            assertEquals(expectedHex, result.value());
        }
    }

    // =========================================================================
    // BOUNDARY TESTS - Edge cases at the limits
    // =========================================================================
    @Nested
    @DisplayName("Boundary Cases")
    class BoundaryTests {

        @Test
        @DisplayName("Zero converts to zero in any base")
        void zeroConvertsToZero() {
            assertEquals("0", converter.convert("0", 10, 2).value());
            assertEquals("0", converter.convert("0", 10, 16).value());
            assertEquals("0", converter.convert("0", 2, 10).value());
        }

        @Test
        @DisplayName("Minimum base (2) works correctly")
        void minimumBaseWorks() {
            ConversionResult result = converter.convert("1010", 2, 10);
            assertEquals("10", result.value());
        }

        @Test
        @DisplayName("Maximum base (36) works correctly")
        void maximumBaseWorks() {
            // In base 36: Z = 35
            ConversionResult result = converter.convert("Z", 36, 10);
            assertEquals("35", result.value());
        }

        @Test
        @DisplayName("Single digit values convert correctly")
        void singleDigitConversion() {
            assertEquals("1", converter.convert("1", 10, 2).value());
            assertEquals("1", converter.convert("1", 2, 10).value());
        }

        @Test
        @DisplayName("Same base conversion returns same value")
        void sameBaseConversion() {
            assertEquals("12345", converter.convert("12345", 10, 10).value());
            assertEquals("1010", converter.convert("1010", 2, 2).value());
        }
    }
    // =========================================================================
    // CASE INSENSITIVITY - Input should handle both cases
    // =========================================================================
    @Nested
    @DisplayName("Case Handling")
    class CaseHandlingTests {

        @Test
        @DisplayName("Lowercase hex input is handled")
        void lowercaseInputWorks() {
            ConversionResult result = converter.convert("ff", 16, 10);
            assertEquals("255", result.value());
        }

        @Test
        @DisplayName("Mixed case input is handled")
        void mixedCaseInputWorks() {
            ConversionResult result = converter.convert("FfFf", 16, 10);
            assertEquals("65535", result.value());
        }
    }

    // =========================================================================
    // FRACTIONAL NUMBER TESTS
    // =========================================================================
    @Nested
    @DisplayName("Fractional Numbers")
    class FractionalTests {

        @Test
        @DisplayName("Decimal fraction to binary")
        void decimalFractionToBinary() {
            ConversionResult result = converter.convert("5.5", 10, 2);
            assertEquals("101.1", result.value());
        }

        @Test
        @DisplayName("Binary fraction to decimal")
        void binaryFractionToDecimal() {
            ConversionResult result = converter.convert("101.1", 2, 10);
            assertEquals("5.5", result.value());
        }

        @Test
        @DisplayName("Whole number with .0 fraction")
        void wholeNumberWithZeroFraction() {
            ConversionResult result = converter.convert("10.0", 10, 2);
            assertEquals("1010", result.value()); // No decimal point needed
        }
    }

    // =========================================================================
    // ROUND-TRIP TESTS - Convert A→B→A should return original
    // =========================================================================
    @Nested
    @DisplayName("Round-trip Conversions")
    class RoundTripTests {

        @ParameterizedTest(name = "Round-trip decimal {0} through base {1}")
        @CsvSource({
                "42, 2",
                "42, 8",
                "42, 16",
                "255, 2",
                "1000, 36"
        })
        void roundTripConversion(String original, int intermediateBase) {
            // Decimal → Other Base → Decimal
            ConversionResult intermediate = converter.convert(original, 10, intermediateBase);
            ConversionResult backToDecimal = converter.convert(intermediate.value(), intermediateBase, 10);

            assertEquals(original, backToDecimal.value());
        }
    }

    // =========================================================================
    // ERROR HANDLING TESTS - Invalid inputs should throw exceptions
    // =========================================================================
    @Nested
    @DisplayName("Error Handling")
    class ErrorHandlingTests {

        @ParameterizedTest(name = "Base {0} should throw exception")
        @ValueSource(ints = {-1, 0, 1, 37, 100})
        void invalidBaseThrowsException(int invalidBase) {
            assertThrows(IllegalArgumentException.class,
                    () -> converter.convert("10", invalidBase, 10));

            assertThrows(IllegalArgumentException.class,
                    () -> converter.convert("10", 10, invalidBase));
        }

        @Test
        @DisplayName("Invalid character for base throws exception")
        void invalidCharacterThrowsException() {
            // '2' is not valid in binary (base 2)
            assertThrows(IllegalArgumentException.class,
                    () -> converter.convert("102", 2, 10));
        }

        @Test
        @DisplayName("Hex character in decimal conversion throws exception")
        void hexCharacterInDecimalThrowsException() {
            assertThrows(IllegalArgumentException.class,
                    () -> converter.convert("1A", 10, 2));
        }
    }


}
