package com.codurance.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorShould {
    @Test
    void returns_the_sum_of_two_numbers() {
        //Arrange
        Calculator calculator = new Calculator();

        //Act
        int result = calculator.add(2, 3);

        //Assert
        assertEquals(5, result);
    }

    @Test
    void returns_the_substraction_of_two_numbers() {
        //Arrange
        Calculator calculator = new Calculator();

        //Act
        int result = calculator.sub(3, 2);

        //Assert
        assertEquals(1, result);
    }
}
