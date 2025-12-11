package ru.urfu.labs.lab06;

import java.util.Arrays;

public class Task03_NumberStatistics {

    public static int max(int... values) {
        validate(values);
        int maxValue = values[0];
        for (int value : values) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    public static int min(int... values) {
        validate(values);
        int minValue = values[0];
        for (int value : values) {
            if (value < minValue) {
                minValue = value;
            }
        }
        return minValue;
    }

    public static double average(int... values) {
        validate(values);
        long sum = 0;
        for (int value : values) {
            sum += value;
        }
        return sum / (double) values.length;
    }

    private static void validate(int... values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Нужно передать хотя бы одно значение.");
        }
    }

    public static void main(String[] args) {
        int[] numbers = {4, 7, 1, 9, 2};

        System.out.println("Числа: " + Arrays.toString(numbers));
        System.out.println("Максимум: " + max(numbers));
        System.out.println("Минимум: " + min(numbers));
        System.out.println("Среднее: " + average(numbers));

        System.out.println("Максимум (varargs): " + max(10, 20, -5));
        System.out.println("Минимум (varargs): " + min(10, 20, -5));
        System.out.println("Среднее (varargs): " + average(10, 20, -5));
    }
}



