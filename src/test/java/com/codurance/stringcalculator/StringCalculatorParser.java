package com.codurance.stringcalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculatorParser {
    public static final char NEW_LINE = '\n';
    public static final String CUSTOM_SEPARATOR_BODY_PATTERN = "(.*)\n(.|\n)*";
    public static final String COMMA = ",";
    public static final String SPACE = " ";
    public static final String PIPE = "|";
    private String CUSTOM_SEPARATOR_TOKEN = "//";
    private String delimiter = COMMA;

    List<Double> parse(String formatedText) {
        if (formatedText.isEmpty()) {
            return Arrays.asList();
        }
        formatedText = checkCustomSeparators(formatedText);

        List<String> errors = new ArrayList<>();
        String error = checkNewLine(formatedText);
        if (!error.isEmpty()) {
            errors.add(error);
            formatedText = formatedText.replace(delimiter + NEW_LINE, delimiter);
        }

        formatedText = formatedText.replace(NEW_LINE + "", delimiter);

        error = checkNegativeNumbers(formatedText);
        if (!error.isEmpty()) {
            errors.add(error);
        }

        errors.addAll(checkWrongCharacters(formatedText));

        error = checkNotExpectedEOF(formatedText);
        if (!error.isEmpty()) {
            errors.add(error);
        }

        errors = errors.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        if (!errors.isEmpty()) {
            String message = errors.stream().map(Object::toString).collect(Collectors.joining(NEW_LINE + ""));
            throw new WrongFormat(message);
        }

        return parseNumbers(formatedText);
    }

    private String checkCustomSeparators(String number) {
        if (number.matches(CUSTOM_SEPARATOR_TOKEN + CUSTOM_SEPARATOR_BODY_PATTERN)) {
            int pos = number.indexOf(NEW_LINE);
            delimiter = number.substring(CUSTOM_SEPARATOR_TOKEN.length(), pos);
            number = number.substring(pos + 1);
        }
        return number;
    }

    private String checkNotExpectedEOF(String number) {
        String lastChar = number.charAt(number.length() - 1) + "";
        if (lastChar.equals(delimiter) || lastChar.equals(NEW_LINE + "")) {
            return "Number expected but EOF found.";
        }
        return "";
    }

    private String checkNegativeNumbers(String formatedText) {
        String[] numbers = splitWithDelimiter(formatedText);
        String negativeNumbers = "";
        for (String text : numbers) {
            if (isNegativeNumber(text)) {
                negativeNumbers += SPACE + text + COMMA;
            }
        }
        if (!negativeNumbers.isEmpty()) {
            negativeNumbers = negativeNumbers.substring(0, negativeNumbers.length() - 1);
            return "Negative not allowed :" + negativeNumbers;
        }
        return "";
    }

    private String[] splitWithDelimiter(String formatedText) {
        if (delimiter.equals(PIPE)) {
            return formatedText.split("[" + CUSTOM_SEPARATOR_TOKEN + delimiter + NEW_LINE + "]");
        }

        return formatedText.split(delimiter + PIPE + NEW_LINE);
    }

    private List<String> checkWrongCharacters(String formatedText) {
        String[] numbers = splitWithDelimiter(formatedText);
        int position = 0;
        List<String> errors = new ArrayList<>();
        for (String text : numbers) {
            errors.add(checkUnexpectedCharacter(position, text));
            position += text.length();
        }
        return errors;
    }

    private boolean isNegativeNumber(String text) {
        return text.matches("^-\\d*\\.?\\d+$");
    }

    private String checkUnexpectedCharacter(int position, String text) {
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

    private String checkNewLine(String formatedText) {
        int pos = formatedText.indexOf(delimiter + NEW_LINE + "");
        if (pos != -1) {
            return "Number expected but '\\n' found at position " + (pos + delimiter.length()) + ".";
        }
        return "";
    }

    private List<Double> parseNumbers(String formatedText) {
        List<Double> result = new ArrayList<>();
        String numberToParse = formatedText.replace(NEW_LINE + "", delimiter);
        String[] numbers = numberToParse.split(delimiter);

        for (String n : numbers) {
            double dn = Double.parseDouble(n);

            result.add(dn);
        }
        return result;
    }
}
