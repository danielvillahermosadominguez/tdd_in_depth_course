package com.codurance.stringcalculator;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

        checkNewLine(number);
        checkWrongCharacters(number);
        checkNotExpectedEOF(number);

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

    private void checkNotExpectedEOF(String number) {
        String lastChar = number.charAt(number.length() - 1) + "";
        if (lastChar.equals(delimiter) || lastChar.equals(NEW_LINE + "")) {
            throw new WrongFormat("Number expected but EOF found.");
        }
    }

    private void checkWrongCharacters(String number) {
        if (delimiter.equals("|")) {
            delimiter = CUSTOM_SEPARATOR_TOKEN + "|";
        }
        String[] numbers = number.split("[" + delimiter + NEW_LINE + "]");
        int position = 0;
        String negativeNumbers = "";
        for (String text : numbers) {
            if (isNegativeNumber(text)) {
                negativeNumbers += " " + text + ",";
            }
        }
        if (!negativeNumbers.isEmpty()) {
            negativeNumbers = negativeNumbers.substring(0, negativeNumbers.length() - 1);
            throw new WrongFormat("Negative not allowed :" + negativeNumbers);
        }
        for (String text : numbers) {
            isNegativeNumber(text);
            checkUnexpectedCharacter(position, text);
            position += text.length();
        }
    }

    private boolean isNegativeNumber(String text) {
        return text.matches("^-\\d*\\.?\\d+$");
    }

    private void checkUnexpectedCharacter(int position, String text) {
        int pos = 0;
        for (char c : text.toCharArray()) {
            if (!Character.isDigit(c) && c != '.') {
                throw new WrongFormat("Number expected but '" + c + "' found at position " + (position + pos + 1) + ".");
            }
            pos++;
        }
    }

    private void checkNewLine(String number) {
        int pos = number.indexOf(delimiter + "\n");
        if (pos != -1) {
            throw new WrongFormat("Number expected but '\\n' found at position " + (pos + delimiter.length()) + ".");
        }
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
