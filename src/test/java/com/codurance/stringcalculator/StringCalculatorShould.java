package com.codurance.stringcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorShould {

    StringCalculator calculator = null;

    @BeforeEach
    void beforeEach() {
        calculator = new StringCalculator();
    }

    @ParameterizedTest
    @CsvSource({
        "'',0",
        "1,1",
        "'1.1,2.2',3.3",
        "'1.32,2.34',3.7",
        "'1,2.34,3,4,5.1',15.4",
        "'1\n2,3',6",
        "'//;\n1;2',3",
        "'//sep\n2sep3',5"
    })
    void return_the_sum_of_numbers(String number, String expectedResult) {
        assertEquals(expectedResult, calculator.add(number));
    }

    @ParameterizedTest
    @CsvSource({
        "'//|\n175.2|\n35','\\n',6",
        "'175.2,\n35','\\n',6",
        "'//|\n1|2,3',',',3"
    })
    void throw_an_exception_when_the_format_is_not_correct_no_expected_character(String number, String character, int position) {
        Exception ex = assertThrows(WrongFormat.class, () -> {
            calculator.add(number);
        });

        assertEquals(
            "Number expected but '" + character + "' found at position " + position + ".",
            ex.getMessage()
        );
    }

    @ParameterizedTest
    @CsvSource({
        "','",
        "'\n'",
    })
    void throw_an_exception_when_the_format_is_not_correct_EOF_not_expected(String lastChar) {
        Exception ex = assertThrows(WrongFormat.class, () -> {
            calculator.add("1,3" + lastChar);
        });
        assertEquals(
            "Number expected but EOF found.",
            ex.getMessage()
        );
    }

    @ParameterizedTest
    @CsvSource({
        "'-1,2','-1'",
        "'2,-4,-5','-4, -5'"
    })
    void throw_an_exception_when_the_format_is_not_correct_Negative_not_allowed(String number, String negNumber) {
        Exception ex = assertThrows(WrongFormat.class, () -> {
            calculator.add(number);
        });
        assertEquals(
            "Negative not allowed : " + negNumber,
            ex.getMessage()
        );
    }
}
