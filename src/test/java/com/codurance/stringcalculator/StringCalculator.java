package com.codurance.stringcalculator;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class StringCalculator {

    public static final char NEW_LINE = '\n';
    public static final String CUSTOM_SEPARATOR_BODY_PATTERN = "(.*)\n(.|\n)*";
    private static final String DECIMAL_PATTERN = "##.#";
    private String CUSTOM_SEPARATOR_TOKEN = "//";
    private String delimiter = ",";

    public String add(String number) {
        if (number.isEmpty()) {
            return format(0);
        }

        number = checkCustomSeparators(number);

        List<String> errors = new ArrayList<>();
        String error = checkNewLine(number);
        if (!error.isEmpty()) {
            errors.add(error);
            number = number.replace(delimiter + "\n", delimiter);
        }

        number = number.replace("\n", delimiter);

        error = checkNegativeNumbers(number);
        if (!error.isEmpty()) {
            errors.add(error);
        }

        errors.addAll(checkWrongCharacters(number));

        error = checkNotExpectedEOF(number);
        if (!error.isEmpty()) {
            errors.add(error);
        }

        errors = errors.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        if (!errors.isEmpty()) {
            String message = errors.stream().map(Object::toString).collect(Collectors.joining("\n"));
            throw new WrongFormat(message);
        }

        List<Double> numbers = parseNumbers(number);

        return format(
            numbers.
                stream().
                reduce(0.0, Double::sum
                )
        );
    }

    private String checkCustomSeparators(String number) {
        if (number.matches(CUSTOM_SEPARATOR_TOKEN + CUSTOM_SEPARATOR_BODY_PATTERN)) {
            int pos = number.indexOf(NEW_LINE);
            delimiter = number.substring(CUSTOM_SEPARATOR_TOKEN.length(), pos);
            number = number.substring(pos + 1, number.length());
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

    private String checkNegativeNumbers(String number) {
        String[] numbers = splitWithDelimiter(number);
        String negativeNumbers = "";
        for (String text : numbers) {
            if (isNegativeNumber(text)) {
                negativeNumbers += " " + text + ",";
            }
        }
        if (!negativeNumbers.isEmpty()) {
            negativeNumbers = negativeNumbers.substring(0, negativeNumbers.length() - 1);
            return "Negative not allowed :" + negativeNumbers;
        }
        return "";
    }

    private String[] splitWithDelimiter(String number) {
        if (delimiter.equals("|")) {
            return number.split("[" + CUSTOM_SEPARATOR_TOKEN + delimiter + NEW_LINE + "]");
        }

        return number.split(delimiter + "|" + NEW_LINE);
    }

    private List<String> checkWrongCharacters(String number) {
        String[] numbers = splitWithDelimiter(number);
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

    private String checkNewLine(String number) {
        int pos = number.indexOf(delimiter + "\n");
        if (pos != -1) {
            return "Number expected but '\\n' found at position " + (pos + delimiter.length()) + ".";
        }
        return "";
    }

    private List<Double> parseNumbers(String number) {
        List<Double> result = new ArrayList<>();
        String numberToParse = number.replace("\n", delimiter);
        String[] numbers = numberToParse.split(delimiter);

        for (String n : numbers) {
            double dn = Double.parseDouble(n);
            result.add(dn);
        }
        return result;
    }

    private String format(double result) {
        DecimalFormat format = new DecimalFormat(DECIMAL_PATTERN);
        format.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));

        return format.format(result);
    }
}
