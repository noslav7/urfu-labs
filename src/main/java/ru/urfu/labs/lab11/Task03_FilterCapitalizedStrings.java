package ru.urfu.labs.lab11;

import java.util.List;

public class Task03_FilterCapitalizedStrings {

    public static List<String> filterCapitalizedStrings(List<String> strings) {
        if (strings == null) {
            return List.of();
        }

        return strings.stream()
            .filter(value -> value != null && !value.isBlank())
            .filter(value -> Character.isUpperCase(value.charAt(0)))
            .toList();
    }

    public static void main(String[] args) {
        List<String> source = List.of("Москва", "река", "Java", "курс", "Университет");
        List<String> result = filterCapitalizedStrings(source);
        System.out.println("Исходный список: " + source);
        System.out.println("Строки с заглавной буквы: " + result);
    }
}
