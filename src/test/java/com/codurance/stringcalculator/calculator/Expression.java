package com.codurance.stringcalculator.calculator;

import java.util.Arrays;
import java.util.List;

public class Expression {
    public static final char NEW_LINE = '\n';
    public static final String CUSTOM_SEPARATOR_BODY_PATTERN = "(.*)\n(.|\n)*";
    public static final String COMMA_SYMBOL = ",";
    public static final String PIPE_SYMBOL = "|";
    private String CUSTOM_SEPARATOR_TOKEN = "//";

    private String delimiter = COMMA_SYMBOL;
    private String value = "";

    public Expression(String value) {
        setValue(value);
    }

    public String getDelimiter() {
        return delimiter;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        if (value.matches(CUSTOM_SEPARATOR_TOKEN + CUSTOM_SEPARATOR_BODY_PATTERN)) {
            int pos = value.indexOf(NEW_LINE);
            delimiter = value.substring(CUSTOM_SEPARATOR_TOKEN.length(), pos);
            this.value = value.substring(pos + 1);
        } else {
            this.value = value;
        }
    }

    public List<String> getTokens() {
        if (value.isEmpty()) {
            return Arrays.asList();
        }

        String regex = "";
        if (delimiter.equals(this.PIPE_SYMBOL)) {
            regex = "[" + CUSTOM_SEPARATOR_TOKEN + delimiter + NEW_LINE + "]";
        } else {
            regex = delimiter + PIPE_SYMBOL + NEW_LINE;
        }

        return Arrays.asList(value.split(regex));
    }
}
