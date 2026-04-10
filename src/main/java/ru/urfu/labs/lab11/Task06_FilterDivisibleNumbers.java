package ru.urfu.labs.lab11;

import java.util.List;

public class Task06_FilterDivisibleNumbers {

    public static List<Integer> filterDivisibleNumbers(List<Integer> numbers, int divisor) {
        if (numbers == null) {
            return List.of();
        }
        if (divisor == 0) {
            throw new IllegalArgumentException("Делитель не может быть равен 0.");
        }

        return numbers.stream()
            .filter(number -> number % divisor == 0)
            .toList();
    }

    public static void main(String[] args) {
        List<Integer> source = List.of(3, 6, 7, 9, 12, 14, 18);
        int divisor = 3;
        List<Integer> result = filterDivisibleNumbers(source, divisor);

        System.out.println("Исходный список: " + source);
        System.out.println("Делитель: " + divisor);
        System.out.println("Числа без остатка: " + result);
    }
}
