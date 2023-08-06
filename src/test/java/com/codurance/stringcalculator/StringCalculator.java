package com.codurance.stringcalculator;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class StringCalculator {

    private static final String DECIMAL_PATTERN = "##.#";
    StringCalculatorParser parser;

    public StringCalculator(StringCalculatorParser parser) {
        this.parser = parser;
    }

    public String add(String number) {
        List<Double> tokens = parser.parse(number);

        return format(
            tokens.
                stream().
                reduce(0.0, Double::sum
                )
        );
    }


    private String format(double result) {
        DecimalFormat format = new DecimalFormat(DECIMAL_PATTERN);
        format.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));

        return format.format(result);
    }
}
