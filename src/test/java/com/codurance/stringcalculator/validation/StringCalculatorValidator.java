package com.codurance.stringcalculator.validation;

import com.codurance.stringcalculator.calculator.Expression;
import com.codurance.stringcalculator.exceptions.WrongFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculatorValidator {

    private List<ValidationRule> rules = new ArrayList<>();

    public void addValidationRule(ValidationRule rule) {
        this.rules.add(rule);
    }

    public Boolean verify(Expression expression) {
        if (expression.getValue().isEmpty()) {
            return true;
        }

        List<String> errors = new ArrayList<>();
        for (ValidationRule rule : rules) {
            errors.addAll(rule.verify(expression));
        }

        if (!errors.isEmpty()) {
            String message = errors.stream().map(Object::toString).collect(Collectors.joining("\n"));
            throw new WrongFormat(message);
        }

        return true;
    }
}
