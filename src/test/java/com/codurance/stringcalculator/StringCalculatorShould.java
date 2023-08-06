package com.codurance.stringcalculator;

import com.codurance.stringcalculator.calculator.Expression;
import com.codurance.stringcalculator.calculator.StringCalculator;
import com.codurance.stringcalculator.validation.StringCalculatorValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StringCalculatorShould {

    StringCalculatorValidator validator;
    StringCalculator calculator = null;

    @BeforeEach
    void beforeEach() {
        validator = mock();
        calculator = new StringCalculator(validator);
    }

    @Test
    void return_the_sum_of_numbers() {
        when(validator.verify(any())).thenReturn(true);

        Expression expression = new Expression("1,2,3\n4");
        Expression result = calculator.add(expression);
        assertEquals("10", result.getValue());
    }

    @Test
    void return_the_multiplication_of_numbers() {
        when(validator.verify(any())).thenReturn(true);

        Expression expression = new Expression("1,2,3\n4");
        Expression result = calculator.multiply(expression);
        assertEquals("24", result.getValue());
    }
}
