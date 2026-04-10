package ru.urfu.labs.lab11;

import java.util.List;

public class Task10_FilterLessThanValue {

    public static List<Integer> filterLessThan(List<Integer> numbers, int threshold) {
        if (numbers == null) {
            return List.of();
        }

        return numbers.stream()
            .filter(number -> number < threshold)
            .toList();
    }

    public static void main(String[] args) {
        List<Integer> source = List.of(-10, -3, 0, 4, 9, 15);
        int threshold = 5;
        List<Integer> result = filterLessThan(source, threshold);

        System.out.println("Исходный список: " + source);
        System.out.println("Порог: " + threshold);
        System.out.println("Числа меньше порога: " + result);
    }
}
