package ru.urfu.labs.lab11;

import java.util.Arrays;

public class Task01_FilterEvenNumbers {

    public static int[] filterEvenNumbers(int[] numbers) {
        if (numbers == null) {
            return new int[0];
        }
        return Arrays.stream(numbers)
            .filter(number -> number % 2 == 0)
            .toArray();
    }

    public static void main(String[] args) {
        int[] source = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] result = filterEvenNumbers(source);
        System.out.println("Исходный массив: " + Arrays.toString(source));
        System.out.println("Четные числа: " + Arrays.toString(result));
    }
}
