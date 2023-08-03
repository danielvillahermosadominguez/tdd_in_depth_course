package com.codurance.fizzbuzz;

public class FizzBuzz {
    public String of(Integer number) {
        String result = "";
        if (number % 3 == 0) {
            result = "Fizz";
        }

        if (number % 5 == 0) {
            result += "Buzz";
        }

        if (result.isEmpty()) {
            result = number.toString();
        }

        return result;
    }
}
