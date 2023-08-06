package com.codurance.stringcalculator.rules;

import com.codurance.stringcalculator.Expression;

import java.util.ArrayList;
import java.util.List;

public class DelimiterAndNewLineRule extends ValidationRule {
    @Override
    public List<String> verify(Expression expression) {
        String delimiter = expression.getDelimiter();
        String value = expression.getValue();
        List<String> result = new ArrayList<>();
        int pos = value.indexOf(delimiter + "\n");
        if (pos != -1) {
            result.add("Number expected but '\\n' found at position " + (pos + delimiter.length()) + ".");
            value = value.replace(delimiter + "\n", delimiter);
            expression.setValue(value);
        }
        return result;
    }
}
