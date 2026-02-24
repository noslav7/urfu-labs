package ru.urfu.labs.lab09;

public class Example04_Factorial {

    private Example04_Factorial() {
    }

    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Факториал определен только для n >= 0");
        }
        if (n == 0 || n == 1) {
            return 1L;
        }
        return n * factorial(n - 1);
    }
}
