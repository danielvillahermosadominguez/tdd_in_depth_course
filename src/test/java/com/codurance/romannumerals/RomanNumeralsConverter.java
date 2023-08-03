package com.codurance.romannumerals;

public class RomanNumeralsConverter {
    private SymbolTable symbols;

    public RomanNumeralsConverter(SymbolTable symbols) {
        this.symbols = symbols;
    }

    public String convert(Integer number) {
        int rest = number;

        for (Symbol current : symbols.getSortedSymbols()) {
            if (current.isBiggerOrEqual(rest)) {
                rest = current.subtract(rest);
                return current.getRomanNumeral() + convert(rest);
            }

            Symbol previous = symbols.getPreviousSymbol(current);
            if (current.isBetween(rest, previous)) {
                rest -= current.subtract(previous);
                Symbol dynamicSymbol = symbols.combine(previous, current);
                return dynamicSymbol.getRomanNumeral() + convert(rest);
            }
        }
        return "";
    }
}
