package ru.urfu.labs.lab11;

import java.util.List;

public class Task04_SquareNumbers {

    public static List<Integer> squareNumbers(List<Integer> numbers) {
        if (numbers == null) {
            return List.of();
        }

        return numbers.stream()
            .map(number -> number * number)
            .toList();
    }

    public static void main(String[] args) {
        List<Integer> source = List.of(1, -2, 3, 4, -5);
        List<Integer> result = squareNumbers(source);
        System.out.println("Исходный список: " + source);
        System.out.println("Квадраты чисел: " + result);
    }
}
