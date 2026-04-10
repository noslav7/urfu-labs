package ru.urfu.labs.lab11;

import java.util.List;

public class Task05_FilterStringsBySubstring {

    public static List<String> filterBySubstring(List<String> strings, String substring) {
        if (strings == null || substring == null) {
            return List.of();
        }

        return strings.stream()
            .filter(value -> value != null && value.contains(substring))
            .toList();
    }

    public static void main(String[] args) {
        List<String> source = List.of("программирование", "грамматика", "код", "телеграм", "алгоритм");
        String substring = "грам";
        List<String> result = filterBySubstring(source, substring);

        System.out.println("Исходный список: " + source);
        System.out.println("Подстрока: " + substring);
        System.out.println("Подходящие строки: " + result);
    }
}
