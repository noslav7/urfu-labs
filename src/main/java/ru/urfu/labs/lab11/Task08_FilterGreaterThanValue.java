package ru.urfu.labs.lab11;

import java.util.List;

public class Task08_FilterGreaterThanValue {

    public static List<Integer> filterGreaterThan(List<Integer> numbers, int threshold) {
        if (numbers == null) {
            return List.of();
        }

        return numbers.stream()
            .filter(number -> number > threshold)
            .toList();
    }

    public static void main(String[] args) {
        List<Integer> source = List.of(-5, 0, 2, 7, 10, 12);
        int threshold = 5;
        List<Integer> result = filterGreaterThan(source, threshold);

        System.out.println("Исходный список: " + source);
        System.out.println("Порог: " + threshold);
        System.out.println("Числа больше порога: " + result);
    }
}
