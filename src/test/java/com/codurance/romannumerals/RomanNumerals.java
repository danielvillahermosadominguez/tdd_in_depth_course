package com.codurance.romannumerals;

import java.util.LinkedHashMap;
import java.util.Map;

public class RomanNumerals {
    private static Map<Integer, String> symbols = new LinkedHashMap<Integer, String>() {{
        put(1000, "M");
        put(500, "D");
        put(100, "C");
        put(50, "L");
        put(10, "X");
        put(5, "V");
        put(1, "I");
    }};

    private static int getFirstDigit(int n) {
        while (n >= 10) {
            n /= 10;
        }
        return n;
    }

    public static String convert(int number) {
        int rest = number;

        for (Map.Entry<Integer, String> entry : symbols.entrySet()) {
            if (rest >= entry.getKey()) {
                rest -= entry.getKey();
                return entry.getValue() + convert(rest);
            }
            
            int firstDigit = getFirstDigit(entry.getKey());
            Map.Entry<Integer, String> previous = null;
            if (firstDigit == 1) {
                previous = getPreviousSymbol(entry.getKey(), 2);
            }
            if (firstDigit == 5) {
                previous = getPreviousSymbol(entry.getKey(), 1);
            }

            if ((previous != null) && (rest >= (entry.getKey() - previous.getKey()))) {
                rest -= (entry.getKey() - previous.getKey());
                return previous.getValue() + entry.getValue() + convert(rest);
            }
        }

        return "";
    }

    private static Map.Entry<Integer, String> getPreviousSymbol(Integer number, int positions) {
        boolean found = false;
        int restPositions = positions;
        for (Map.Entry<Integer, String> entry : symbols.entrySet()) {
            if (found) {
                restPositions--;
                if (restPositions == 0) {
                    return entry;
                }
            }

            if (entry.getKey() == number) {
                found = true;
            }
        }
        return null;
    }
}
