package com.codurance.stringcalculator.validation;

import com.codurance.stringcalculator.calculator.Expression;

import java.util.ArrayList;
import java.util.List;

public class WrongCharactersRule extends ValidationRule {

    @Override
    public List<String> verify(Expression expression) {
        List<String> result = new ArrayList<>();
        List<String> tokens = expression.getTokens();
        int position = 0;
        for (String text : tokens) {
            String error = checkUnexpectedCharacter(expression, position, text);
            if (!error.isEmpty()) {
                result.add(error);
            }
            position += text.length();
        }
        return result;
    }

    private String checkUnexpectedCharacter(Expression expression, int position, String text) {
        String messageFormat = "Number expected but '%s' found at position %s.";
        String delimiter = expression.getDelimiter();
        if (text.isEmpty()) {
            return String.format(messageFormat, delimiter, position + 1);
        }

        int pos = 0;
        for (char c : text.toCharArray()) {
            if (!Character.isDigit(c) && c != '.' && c != '-') {
                return String.format(messageFormat, c, position + pos + 1);
            }
            pos++;
        }
        return "";
    }

}
