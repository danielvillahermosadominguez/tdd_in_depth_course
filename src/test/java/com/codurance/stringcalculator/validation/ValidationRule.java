package com.codurance.stringcalculator.validation;

import com.codurance.stringcalculator.calculator.Expression;

import java.util.List;

public abstract class ValidationRule {
    public abstract List<String> verify(Expression expression);
}
