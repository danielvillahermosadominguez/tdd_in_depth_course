package com.codurance.romannumerals;

public class Symbol {
    private final int number;
    private final String romanNumber;

    public Symbol(int number, String romanNumber) {
        this.number = number;
        this.romanNumber = romanNumber;
    }

    public String getRomanNumeral() {
        return romanNumber;
    }

    public int getNumber() {
        return number;
    }

    public int subtract(int rest) {
        return rest - number;
    }

    public int subtract(Symbol symbol) {
        return number - symbol.getNumber();
    }
}
