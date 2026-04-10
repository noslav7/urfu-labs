package ru.urfu.labs.lab11;

import java.util.List;

public class Task07_FilterStringsByLength {

    public static List<String> filterByLength(List<String> strings, int minLength) {
        if (strings == null) {
            return List.of();
        }

        return strings.stream()
            .filter(value -> value != null && value.length() > minLength)
            .toList();
    }

    public static void main(String[] args) {
        List<String> source = List.of("кот", "университет", "дом", "лабораторная", "java");
        int minLength = 4;
        List<String> result = filterByLength(source, minLength);

        System.out.println("Исходный список: " + source);
        System.out.println("Минимальная длина: " + minLength);
        System.out.println("Строки длиннее заданной: " + result);
    }
}
