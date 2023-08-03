package com.codurance.romannumerals;

import java.util.LinkedHashMap;
import java.util.Map;

public class RomanNumerals {
    private static Map<Integer, Symbol> symbols = new LinkedHashMap<Integer, Symbol>() {{
        put(1000, new Symbol(1000, "M"));
        put(500, new Symbol(500, "D"));
        put(100, new Symbol(100, "C"));
        put(50, new Symbol(50, "L"));
        put(10, new Symbol(10, "X"));
        put(5, new Symbol(5, "V"));
        put(1, new Symbol(1, "I"));
    }};

    public static String convert(int number) {
        int rest = number;

        for (Map.Entry<Integer, Symbol> entry : symbols.entrySet()) {
            Symbol current = entry.getValue();

            if (current.isBiggerOrEqual(rest)) {
                rest -= entry.getKey();
                return current.getRomanNumeral() + convert(rest);
            }

            Symbol previous = getPreviousSymbol(current);

            if (current.isBetween(rest, previous)) {
                rest -= (current.getNumber() - previous.getNumber());
                return previous.getRomanNumeral() + current.getRomanNumeral() + convert(rest);
            }
        }

        return "";
    }

    private static int getFirstDigit(int n) {
        while (n >= 10) {
            n /= 10;
        }
        return n;
    }

    private static Symbol getPreviousSymbol(Symbol symbol) {
        Symbol previous = null;
        int firstDigit = getFirstDigit(symbol.getNumber());
        if (firstDigit == 1) {
            previous = getPreviousSymbol(symbol.getNumber(), 2);
        }
        if (firstDigit == 5) {
            previous = getPreviousSymbol(symbol.getNumber(), 1);
        }
        return previous;
    }

    private static Symbol getPreviousSymbol(Integer number, int positions) {
        boolean found = false;
        int restPositions = positions;
        for (Map.Entry<Integer, Symbol> entry : symbols.entrySet()) {
            Symbol currentSymbol = entry.getValue();
            if (found) {
                restPositions--;
                if (restPositions == 0) {
                    return currentSymbol;
                }
            }

            if (currentSymbol.getNumber() == number) {
                found = true;
            }
        }
        return null;
    }
}
