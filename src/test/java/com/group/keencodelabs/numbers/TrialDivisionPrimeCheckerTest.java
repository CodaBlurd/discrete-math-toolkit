package com.group.keencodelabs.numbers;

import com.group.keencodelabs.numbers.dto.PrimeCheckerResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class TrialDivisionPrimeCheckerTest {
    private TrialDivisionPrimeChecker checker;

    @BeforeEach
    void setUp(){
        checker = new TrialDivisionPrimeChecker();
    }


    // ================== isPrime() Tests ==================

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 10007, 100003})
    void isPrime_withPrimeNumbers_returnsTrue(int prime){
        assertTrue(checker.isPrime(prime));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 8, 9, 10, 12, 15, 100, 1000, 1000000})
    void isPrime_withCompositeNumbers_returnsFalse(int composite) {
        assertFalse(checker.isPrime(composite));
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -1, 0, 1})
    void isPrime_withNumbersLessThanOrEqualToOne_returnsFalse(int number) {
        assertFalse(checker.isPrime(number));
    }

    @Test
    void isPrime_withEvenNumber_completesInOneStep() {
        checker.isPrime(1000);
        assertEquals(1, checker.getStepCount());
    }

    @Test
    void isPrime_withSmallPrime_completesInExpectedSteps() {
        checker.isPrime(17);
        assertEquals(15, checker.getStepCount()); // checks 2 through 16
    }


    // ==================== isPrimeOptimized() Tests ====================

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 10007})
    void isPrimeOptimized_withPrimeNumbers_returnsTrue(int prime) {
        PrimeCheckerResult result = checker.isPrimeOptimized(prime);
        assertTrue(result.prime());
        assertNull(result.firstDivisor());
        assertEquals(prime, result.number());
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 8, 10, 100, 1000})
    void isPrimeOptimized_withEvenCompositeNumbers_returnsFalseWithDivisorTwo(int composite) {
        PrimeCheckerResult result = checker.isPrimeOptimized(composite);
        assertFalse(result.prime());
        assertEquals(2, result.firstDivisor());
    }

    @Test
    void isPrimeOptimized_withCompositeOddNumber_returnsFirstDivisor() {
        PrimeCheckerResult result = checker.isPrimeOptimized(15); // 15 = 3 * 5
        assertFalse(result.prime());
        assertEquals(3, result.firstDivisor());
    }

    @Test
    void isPrimeOptimized_withSquareOfPrime_returnsCorrectDivisor() {
        PrimeCheckerResult result = checker.isPrimeOptimized(49); // 49 = 7 * 7
        assertFalse(result.prime());
        assertEquals(7, result.firstDivisor());
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -1, 0, 1})
    void isPrimeOptimized_withNumbersLessThanOrEqualToOne_returnsFalse(int number) {
        PrimeCheckerResult result = checker.isPrimeOptimized(number);
        assertFalse(result.prime());
    }

    @Test
    void isPrimeOptimized_withTwo_returnsTrue() {
        PrimeCheckerResult result = checker.isPrimeOptimized(2);
        assertTrue(result.prime());
        assertNull(result.firstDivisor());
        assertEquals(0, result.checksPerformed());
    }

    @Test
    void isPrimeOptimized_requiresFewerStepsThanBasicMethod() {
        int largeNumber = 100003;

        checker.isPrime(largeNumber);
        int basicSteps = checker.getStepCount();

        PrimeCheckerResult result = checker.isPrimeOptimized(largeNumber);
        int optimizedSteps = result.checksPerformed();

        assertTrue(optimizedSteps < basicSteps,
                "Optimized method should require fewer steps");
    }




}
