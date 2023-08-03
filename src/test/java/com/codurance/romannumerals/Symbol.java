package com.codurance.romannumerals;

public class Symbol {
    private final int number;
    private final String romanNumber;

    public Symbol(int number, String romanNumber) {
        this.number = number;
        this.romanNumber = romanNumber;
    }

    public boolean isBiggerOrEqual(int numberToCompare) {
        return numberToCompare >= number;
    }

    public String getRomanNumeral() {
        return romanNumber;
    }

    public boolean isBetween(int rest, Symbol symbolToCompare) {
        return (symbolToCompare != null) && (rest >= (number - symbolToCompare.getNumber()));
    }

    public int getNumber() {
        return number;
    }
}
