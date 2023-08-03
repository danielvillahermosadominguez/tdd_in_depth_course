package com.codurance.romannumerals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RomanNumeralsShould {
    @ParameterizedTest
    @CsvSource({
        "0,''",
        "1,I",
        "2,II",
        "3,III",
        "4,IV",
        "5,V",
        "6,VI",
        "7,VII",
        "8,VIII",
        "9,IX",
        "10,X",
        "11,XI",
        "12,XII",
        "13,XIII",
        "14,XIV",
        "15,XV",
        "16,XVI",
        "17,XVII",
        "18,XVIII",
        "19,XIX",
        "20,XX",
        "40,XL",
        "49,XLIX",
        "50,L",
        "59, LIX",
        "78, LXXVIII",
        "90, XC",
        "100, C",
        "333, CCCXXXIII",
        "400, CD",
        "496, CDXCVI",
        "500, D",
        "900, CM",
        "1000, M",
    })
    void convert_to_roman_number(int number, String expectedRomanNumber) {
        assertEquals(expectedRomanNumber, RomanNumerals.convert(number));
    }
}
