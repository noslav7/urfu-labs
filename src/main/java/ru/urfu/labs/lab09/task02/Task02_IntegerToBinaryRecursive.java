package ru.urfu.labs.lab09.task02;

import java.util.Scanner;

/**
 * Лабораторная работа №09, задание 2.
 * Рекурсивный перевод целого числа в двоичную систему счисления.
 */
public class Task02_IntegerToBinaryRecursive {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите целое число: ");
            int value = scanner.nextInt();

            String binary = toBinary(value);
            System.out.println("Двоичное представление: " + binary);
        }
    }

    private static String toBinary(int value) {
        if (value == 0) {
            return "0";
        }
        if (value < 0) {
            return "-" + toBinaryPositive(-(long) value);
        }
        return toBinaryPositive(value);
    }

    private static String toBinaryPositive(long value) {
        if (value < 2) {
            return String.valueOf(value);
        }
        return toBinaryPositive(value / 2) + (value % 2);
    }
}
