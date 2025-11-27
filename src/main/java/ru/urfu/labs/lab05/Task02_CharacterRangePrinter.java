package ru.urfu.labs.lab05;

import java.util.Scanner;

public class Task02_CharacterRangePrinter {

    private final char start;
    private final char end;

    public Task02_CharacterRangePrinter(char start, char end) {
        this.start = start;
        this.end = end;
    }

    public String printRange() {
        String range = buildRange();
        System.out.println(range);
        return range;
    }

    private String buildRange() {
        int lower = Math.min(start, end);
        int upper = Math.max(start, end);

        StringBuilder builder = new StringBuilder();
        for (int code = lower; code <= upper; code++) {
            builder.append((char) code);
            if (code < upper) {
                builder.append(' ');
            }
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Character first = readCharacter(scanner, "Введите первый символ: ");
        if (first == null) {
            scanner.close();
            return;
        }

        Character second = readCharacter(scanner, "Введите второй символ: ");
        if (second == null) {
            scanner.close();
            return;
        }

        Task02_CharacterRangePrinter printer = new Task02_CharacterRangePrinter(first, second);
        String result = printer.printRange();
        System.out.println("Диапазон: " + result);

        scanner.close();
    }

    private static Character readCharacter(Scanner scanner, String prompt) {
        System.out.print(prompt);

        String line = scanner.nextLine();
        if (line.isEmpty()) {
            System.out.println("Ошибка: требуется ввести символ.");
            return null;
        }

        return line.charAt(0);
    }
}


