package com.codurance.stringcalculator;

import com.codurance.stringcalculator.calculator.Expression;
import com.codurance.stringcalculator.exceptions.WrongFormat;
import com.codurance.stringcalculator.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorValidatorShould {
    StringCalculatorValidator validator = null;

    @BeforeEach
    void beforeEach() {
        validator = new StringCalculatorValidator();
        validator.addValidationRule(new DelimiterAndNewLineRule());
        validator.addValidationRule(new EOFRule());
        validator.addValidationRule(new NegativeNumbersRule());
        validator.addValidationRule(new WrongCharactersRule());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "",
        "1,1",
        "1.1,2.2",
        "1.32,2.34",
        "1,2.34,3,4,5.1",
        "1\n2,3",
        "//;\n1;2",
        "//sep\n2sep3"
    })
    void validate_good_formats(String number) {
        Expression expresion = new Expression(number);

        assertTrue(validator.verify(expresion));
    }

    @ParameterizedTest
    @CsvSource({
        "'//|\n175.2|\n35','\\n',6",
        "'175.2,\n35','\\n',6",
        "'//|\n1|2,3',',',3"
    })
    void throw_an_exception_when_the_format_is_not_correct_no_expected_character(String number, String character, int position) {
        Exception ex = assertThrows(WrongFormat.class, () -> {
            Expression expresion = new Expression(number);
            validator.verify(expresion);
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
            validator.verify(new Expression("1,3" + lastChar));
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
            validator.verify(new Expression(number));
        });
        assertEquals(
            "Negative not allowed : " + negNumber,
            ex.getMessage()
        );
    }

    @Test
    void throw_an_exception_when_the_expresion_has_multiple_errors() {
        Exception ex = assertThrows(WrongFormat.class, () -> {
            validator.verify(new Expression("-1,,2"));
        });
        assertEquals(
            "Negative not allowed : -1\n" +
                "Number expected but ',' found at position 3.",
            ex.getMessage()
        );
    }
}
