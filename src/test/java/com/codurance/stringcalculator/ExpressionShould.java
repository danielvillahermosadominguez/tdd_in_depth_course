package com.codurance.stringcalculator;

import com.codurance.stringcalculator.calculator.Expression;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpressionShould {
    static Stream<Arguments> generateData() {
        return Stream.of(
            Arguments.of("", Arrays.asList()),
            Arguments.of("1,1", Arrays.asList("1", "1")),
            Arguments.of("1.1,2.2", Arrays.asList("1.1", "2.2")),
            Arguments.of("1.32,2.34", Arrays.asList("1.32", "2.34")),
            Arguments.of("1,2.34,3,4,5.1", Arrays.asList("1", "2.34", "3", "4", "5.1")),
            Arguments.of("1\n2,3", Arrays.asList("1", "2", "3")),
            Arguments.of("//;\n1;2", Arrays.asList("1", "2")),
            Arguments.of("//sep\n2sep3", Arrays.asList("2", "3"))
        );
    }

    @ParameterizedTest
    @MethodSource("generateData")
    void return_the_valid_tokens_in_list(String text, List<String> tokens) {
        Expression expression = new Expression(text);

        assertEquals(tokens,
            expression.getTokens());
    }
}
