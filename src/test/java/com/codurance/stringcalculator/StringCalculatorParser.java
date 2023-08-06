package com.codurance.stringcalculator;

import com.codurance.stringcalculator.rules.ValidationRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculatorParser {

    private Expression expresionContext = new Expression();
    private List<ValidationRule> rules = new ArrayList<>();

    public void addValidationRule(ValidationRule rule) {
        this.rules.add(rule);
    }

    List<Double> parse(String formatedText) {
        if (formatedText.isEmpty()) {
            return Arrays.asList();
        }

        expresionContext.setValue(formatedText);

        List<String> errors = new ArrayList<>();
        for (ValidationRule rule : rules) {
            errors.addAll(rule.verify(expresionContext));
        }

        if (!errors.isEmpty()) {
            String message = errors.stream().map(Object::toString).collect(Collectors.joining("\n"));
            throw new WrongFormat(message);
        }

        return parseNumbers();
    }

    private List<Double> parseNumbers() {
        List<Double> result = new ArrayList<>();
        String[] numbers = expresionContext.getTokens();

        for (String n : numbers) {
            double dn = Double.parseDouble(n);

            result.add(dn);
        }
        return result;
    }
}
