package com.codurance.stringcalculator.validation;

import com.codurance.stringcalculator.calculator.Expression;

import java.util.ArrayList;
import java.util.List;

public class NegativeNumbersRule extends ValidationRule {

    public static final String SPACE = " ";
    public static final String COMMA = ",";

    @Override
    public List<String> verify(Expression expression) {
        List<String> result = new ArrayList<>();
        List<String> numbers = expression.getTokens();
        String negativeNumbers = "";
        for (String text : numbers) {
            if (isNegativeNumber(text)) {
                negativeNumbers += SPACE + text + COMMA;
            }
        }

        if (!negativeNumbers.isEmpty()) {
            negativeNumbers = negativeNumbers.substring(0, negativeNumbers.length() - 1);
            result.add("Negative not allowed :" + negativeNumbers);
        }

        return result;
    }

    private boolean isNegativeNumber(String text) {
        return text.matches("^-\\d*\\.?\\d+$");
    }
}
