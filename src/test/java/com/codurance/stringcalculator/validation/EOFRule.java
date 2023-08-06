package com.codurance.stringcalculator.validation;

import com.codurance.stringcalculator.calculator.Expression;

import java.util.ArrayList;
import java.util.List;

public class EOFRule extends ValidationRule {

    @Override
    public List<String> verify(Expression expression) {
        String delimiter = expression.getDelimiter();
        String value = expression.getValue();
        List<String> result = new ArrayList<>();
        String lastChar = value.charAt(value.length() - 1) + "";
        if (lastChar.equals(delimiter) || lastChar.equals("\n")) {
            result.add("Number expected but EOF found.");
        }
        return result;
    }
}
