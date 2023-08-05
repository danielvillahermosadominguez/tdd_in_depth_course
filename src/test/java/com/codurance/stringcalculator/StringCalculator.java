package com.codurance.stringcalculator;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StringCalculator {

    private static final String DECIMAL_PATTERN = "##.#";
    private String delimiter = ",";

    public String add(String number) {
        if (number.isEmpty()) {
            return format(0);
        }

        if (number.matches("//(.*)\n(.|\n)*")) {
            int pos = number.indexOf('\n');
            delimiter = number.substring(2, pos);
            number = number.substring(pos + 1, number.length());
        }

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

    private void checkNotExpectedEOF(String number) {
        String lastChar = number.charAt(number.length() - 1) + "";
        if (lastChar.equals(delimiter) || lastChar.equals("\n")) {
            throw new WrongFormat("Number expected but EOF found.");
        }
    }

    private void checkWrongCharacters(String number) {
        if (delimiter.equals("|")) {
            delimiter = "//|";
        }
        String[] numbers = number.split("[" + delimiter + "\n]");
        int position = 0;
        for (String text : numbers) {
            checkUnexpectedCharacter(position, text);
            position += text.length();
        }
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
