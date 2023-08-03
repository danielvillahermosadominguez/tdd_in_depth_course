package com.codurance.romannumerals;

public class RomanNumeralsConverter {
    private SymbolTable symbols;

    public RomanNumeralsConverter(SymbolTable symbols) {
        this.symbols = symbols;
    }

    public String convert(Integer number) {
        int rest = number;

        for (Symbol current : symbols.getSymbols()) {
            if (rest >= current.getNumber()) {
                rest = current.subtract(rest);
                return current.getRomanNumeral() + convert(rest);
            }

            Symbol previous = symbols.getPreviousSymbol(current);
            if (isBetween(rest, current, previous)) {
                rest -= current.subtract(previous);
                Symbol dynamicSymbol = symbols.combine(previous, current);
                return dynamicSymbol.getRomanNumeral() + convert(rest);
            }
        }
        return "";
    }

    private boolean isBetween(int rest, Symbol left, Symbol right) {
        return (right != null) && (rest >= (left.getNumber() - right.getNumber()));
    }
}
