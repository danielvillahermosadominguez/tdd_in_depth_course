package com.codurance.stringcalculator.rules;

import com.codurance.stringcalculator.Expression;

import java.util.ArrayList;
import java.util.List;

public class NegativeNumbersRule extends ValidationRule {
    @Override
    public List<String> verify(Expression expression) {
        List<String> result = new ArrayList<>();
        String[] numbers = expression.getTokens();
        String negativeNumbers = "";
        for (String text : numbers) {
            if (isNegativeNumber(text)) {
                negativeNumbers += " " + text + ",";
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
