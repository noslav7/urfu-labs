package ru.urfu.labs.lab06;

import java.util.Arrays;

public class Task08_ArrayAverage {

    public static double average(int[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Массив должен содержать хотя бы один элемент.");
        }
        long sum = 0;
        for (int value : values) {
            sum += value;
        }
        return sum / (double) values.length;
    }

    public static void main(String[] args) {
        int[] numbers = {3, 5, 7, 9};
        System.out.println("Массив: " + Arrays.toString(numbers));
        System.out.println("Среднее значение: " + average(numbers));
    }
}



