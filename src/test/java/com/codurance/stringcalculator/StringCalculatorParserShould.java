package com.codurance.stringcalculator;

import com.codurance.stringcalculator.rules.DelimiterAndNewLineRule;
import com.codurance.stringcalculator.rules.EOFRule;
import com.codurance.stringcalculator.rules.NegativeNumbersRule;
import com.codurance.stringcalculator.rules.WrongCharactersRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorParserShould {
    StringCalculatorParser parser = null;

    static Stream<Arguments> generateData() {
        return Stream.of(
            Arguments.of("", Arrays.asList()),
            Arguments.of("1,1", Arrays.asList(1.0, 1.0)),
            Arguments.of("1.1,2.2", Arrays.asList(1.1, 2.2)),
            Arguments.of("1.32,2.34", Arrays.asList(1.32, 2.34)),
            Arguments.of("1,2.34,3,4,5.1", Arrays.asList(1.0, 2.34, 3.0, 4.0, 5.1)),
            Arguments.of("1\n2,3", Arrays.asList(1.0, 2.0, 3.0)),
            Arguments.of("//;\n1;2", Arrays.asList(1.0, 2.0)),
            Arguments.of("//sep\n2sep3", Arrays.asList(2.0, 3.0))
        );
    }

    @BeforeEach
    void beforeEach() {
        parser = new StringCalculatorParser();
        parser.addValidationRule(new DelimiterAndNewLineRule());
        parser.addValidationRule(new EOFRule());
        parser.addValidationRule(new NegativeNumbersRule());
        parser.addValidationRule(new WrongCharactersRule());
    }

    @ParameterizedTest
    @MethodSource("generateData")
    void return_the_sum_of_numbers(String number, List<Double> expectedResult) {
        List<Double> result = parser.parse(number);

        assertEquals(
            expectedResult,
            result
        );
    }

    @ParameterizedTest
    @CsvSource({
        "'//|\n175.2|\n35','\\n',6",
        "'175.2,\n35','\\n',6",
        "'//|\n1|2,3',',',3"
    })
    void throw_an_exception_when_the_format_is_not_correct_no_expected_character(String number, String character, int position) {
        Exception ex = assertThrows(WrongFormat.class, () -> {
            parser.parse(number);
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
            parser.parse("1,3" + lastChar);
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
            parser.parse(number);
        });
        assertEquals(
            "Negative not allowed : " + negNumber,
            ex.getMessage()
        );
    }

    @Test
    void throw_an_exception_when_the_expresion_has_multiple_errors() {
        Exception ex = assertThrows(WrongFormat.class, () -> {
            parser.parse("-1,,2");
        });
        assertEquals(
            "Negative not allowed : -1\n" +
                "Number expected but ',' found at position 3.",
            ex.getMessage()
        );
    }
}
