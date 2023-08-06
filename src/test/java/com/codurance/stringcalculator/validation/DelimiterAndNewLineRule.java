package com.codurance.stringcalculator.validation;

import com.codurance.stringcalculator.calculator.Expression;

import java.util.ArrayList;
import java.util.List;

public class DelimiterAndNewLineRule extends ValidationRule {

    public static final String NEW_LINE = "\n";

    @Override
    public List<String> verify(Expression expression) {
        List<String> result = new ArrayList<>();
        String delimiter = expression.getDelimiter();
        String value = expression.getValue();
        int pos = value.indexOf(delimiter + NEW_LINE);
        if (pos != -1) {
            int positionInExpression = pos + delimiter.length();
            String message = String.format("Number expected but '\\n' found at position %s.", positionInExpression);
            result.add(message);
            value = value.replace(delimiter + NEW_LINE, delimiter);
            expression.setValue(value);
        }
        return result;
    }
}
