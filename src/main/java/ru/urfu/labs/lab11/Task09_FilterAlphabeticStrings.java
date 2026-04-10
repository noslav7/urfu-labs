package ru.urfu.labs.lab11;

import java.util.List;

public class Task09_FilterAlphabeticStrings {

    public static List<String> filterAlphabeticStrings(List<String> strings) {
        if (strings == null) {
            return List.of();
        }

        return strings.stream()
            .filter(value -> value != null && !value.isEmpty())
            .filter(Task09_FilterAlphabeticStrings::containsOnlyLetters)
            .toList();
    }

    private static boolean containsOnlyLetters(String value) {
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isLetter(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<String> source = List.of("Java", "Лаба11", "код", "hello!", "УРФУ", "abc123");
        List<String> result = filterAlphabeticStrings(source);

        System.out.println("Исходный список: " + source);
        System.out.println("Только буквенные строки: " + result);
    }
}
