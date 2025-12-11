package ru.urfu.labs.lab06;

public class Task04_DoubleFactorial {

    public static long doubleFactorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Число должно быть неотрицательным.");
        }
        if (number == 0 || number == 1) {
            return 1;
        }

        long result = 1;
        for (int current = number; current > 1; current -= 2) {
            result *= current;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("6!! = " + doubleFactorial(6));
        System.out.println("5!! = " + doubleFactorial(5));
        System.out.println("0!! = " + doubleFactorial(0));
    }
}



