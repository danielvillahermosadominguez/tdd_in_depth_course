package com.codurance.stringcalculator.rules;

import com.codurance.stringcalculator.Expression;

import java.util.List;

public abstract class ValidationRule {
    public abstract List<String> verify(Expression expression);
}
