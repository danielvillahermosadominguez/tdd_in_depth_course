package com.codurance.fizzbuzz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FizzBuzzShould {

    private FizzBuzz fizzBuzz;

    @BeforeEach
    void beforeEach() {
        fizzBuzz = new FizzBuzz();
    }

    @ParameterizedTest
    @CsvSource({
        "1,1",
        "2, 2",
        "3,Fizz",
        "4,4",
        "5,Buzz",
        "6,Fizz",
        "7,7",
        "8,8",
        "9,Fizz",
        "10,Buzz",
        "11,11",
        "12,Fizz",
        "13,13",
        "14,14",
        "15, FizzBuzz",
    })
    void return_Fizz_when_number_is_multiple_of_three(int number, String result) {
        assertEquals(result, fizzBuzz.of(number));
    }
}
