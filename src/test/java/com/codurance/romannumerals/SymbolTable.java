package com.codurance.romannumerals;


import java.util.*;

public class SymbolTable {
    public static final int BASE_10 = 10;
    private Map<Integer, Symbol> symbols = new LinkedHashMap<Integer, Symbol>() {{
        put(1000, new Symbol(1000, "M"));
        put(500, new Symbol(500, "D"));
        put(100, new Symbol(100, "C"));
        put(50, new Symbol(50, "L"));
        put(BASE_10, new Symbol(BASE_10, "X"));
        put(5, new Symbol(5, "V"));
        put(1, new Symbol(1, "I"));
    }};

    public List<Symbol> getSortedSymbols() {
        return new ArrayList<>(symbols.values());
    }


    private int getFirstDigit(int number) {
        while (number >= BASE_10) {
            number /= BASE_10;
        }
        return number;
    }

    public Symbol combine(Symbol symbolLeft, Symbol symbolRight) {
        int number = symbolLeft.getNumber() - symbolRight.getNumber();
        String romanNumber = symbolLeft.getRomanNumeral() + symbolRight.getRomanNumeral();
        return new Symbol(number, romanNumber);
    }

    public Symbol getPreviousSymbol(Symbol symbol) {
        int firstDigit = getFirstDigit(symbol.getNumber());
        if (firstDigit == 1) {
            return getPreviousSymbol(symbol.getNumber(), 2);
        }

        if (firstDigit == 5) {
            return getPreviousSymbol(symbol.getNumber(), 1);
        }

        return null;
    }

    private Symbol getPreviousSymbol(Integer number, int positions) {
        List<Symbol> list = getSortedSymbols();
        Optional<Symbol> symbol = list.stream()
            .filter(item -> item.getNumber() == number)
            .findFirst();

        if (symbol.isPresent()) {
            int currentPosition = list.indexOf(symbol.get());
            int previousPosition = currentPosition + positions;
            if (previousPosition < list.size()) {
                return list.get(previousPosition);
            }
        }

        return null;
    }
}
