package com.codurance.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorShould {
    private Calculator calculator;

    @BeforeEach
    void beforeEach() {
        calculator = new Calculator();

    }

    @ParameterizedTest
    @CsvSource({
        "2,3,5",
        "4,5,9",
        "9,10,19"
    })
    void returns_the_sum_of_two_numbers(int a, int b, int expectedResult) {
        assertEquals(expectedResult, calculator.add(a, b));
    }

    @ParameterizedTest
    @CsvSource({
        "3,2,1",
        "5,3,2",
        "10,5,5"
    })
    void returns_the_substraction_of_two_numbers(int a, int b, int expectedResult) {
        assertEquals(expectedResult, calculator.sub(a, b));
    }
}
