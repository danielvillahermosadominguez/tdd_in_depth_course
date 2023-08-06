package com.codurance.stringcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StringCalculatorShould {

    StringCalculatorParser parser;
    StringCalculator calculator = null;

    @BeforeEach
    void beforeEach() {
        parser = mock();
        calculator = new StringCalculator(parser);
    }

    @Test
    void return_the_sum_of_numbers() {
        when(parser.parse(any())).thenReturn(Arrays.asList(1.0, 2.0, 3.0, 4.0));

        assertEquals("10", calculator.add("1,2,3\n4"));
    }
}
