package com.codurance.stringcalculator.calculator;

import com.codurance.stringcalculator.validation.StringCalculatorValidator;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class StringCalculator {

    private static final String DECIMAL_PATTERN = "##.#";
    StringCalculatorValidator validator;

    public StringCalculator(StringCalculatorValidator parser) {
        this.validator = parser;
    }

    public Expression add(Expression number) {
        List<Double> numbers = parseNumbers(number);

        return format(
            numbers.
                stream().
                reduce(0.0, Double::sum
                )
        );
    }

    private List<Double> parseNumbers(Expression number) {
        List<Double> numbers = new ArrayList<>();
        if (validator.verify(number)) {
            List<String> tokens = number.getTokens();
            numbers.addAll(tokens
                .stream()
                .map(token -> Double.parseDouble(token))
                .collect(Collectors.toList())
            );
        }
        return numbers;
    }


    private Expression format(double result) {
        DecimalFormat format = new DecimalFormat(DECIMAL_PATTERN);
        format.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));

        return new Expression(format.format(result));
    }

    public Expression multiply(Expression number) {
        List<Double> numbers = parseNumbers(number);

        return format(
            numbers.
                stream().
                reduce(1.0, (a, b) -> a * b
                )
        );
    }
}
