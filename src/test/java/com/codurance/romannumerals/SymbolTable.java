package com.codurance.romannumerals;

import java.util.LinkedHashMap;
import java.util.Map;

public class SymbolTable {
    private static Map<Integer, Symbol> symbols = new LinkedHashMap<Integer, Symbol>() {{
        put(1000, new Symbol(1000, "M"));
        put(500, new Symbol(500, "D"));
        put(100, new Symbol(100, "C"));
        put(50, new Symbol(50, "L"));
        put(10, new Symbol(10, "X"));
        put(5, new Symbol(5, "V"));
        put(1, new Symbol(1, "I"));
    }};

}
