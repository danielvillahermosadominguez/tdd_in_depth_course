package com.codurance.stringcalculator.rules;

import com.codurance.stringcalculator.Expression;

import java.util.ArrayList;
import java.util.List;

public class WrongCharactersRule extends ValidationRule {
    @Override
    public List<String> verify(Expression expression) {
        List<String> result = new ArrayList<>();
        String[] tokens = expression.getTokens();
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
        String delimiter = expression.getDelimiter();
        int pos = 0;
        if (text == null || text.isEmpty()) {
            return "Number expected but '" + delimiter + "' found at position " + (position + 1) + ".";
        }

        for (char c : text.toCharArray()) {
            if (!Character.isDigit(c) && c != '.' && c != '-') {
                return "Number expected but '" + c + "' found at position " + (position + pos + 1) + ".";
            }
            pos++;
        }
        return "";
    }

}
