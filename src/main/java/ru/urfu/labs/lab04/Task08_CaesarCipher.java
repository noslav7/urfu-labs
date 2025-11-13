package ru.urfu.labs.lab04;

import java.util.Locale;
import java.util.Scanner;

public class Task08_CaesarCipher {

    private static final int UNICODE_RANGE = Character.MAX_VALUE + 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите текст для шифрования");
        String text = scanner.nextLine();

        System.out.println("Введите ключ");
        int key = readInteger(scanner);

        String encrypted = shift(text, key);
        System.out.println("Текст после преобразования: " + encrypted);

        handleReverse(scanner, encrypted, key);
    }

    private static int readInteger(Scanner scanner) {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException exception) {
                System.out.println("Введите целое число в качестве ключа");
            }
        }
    }

    private static void handleReverse(Scanner scanner, String encrypted, int key) {
        while (true) {
            System.out.println("Выполнить обратное преобразование? (y/n)");
            String answer = scanner.nextLine().trim().toLowerCase(Locale.ROOT);

            if ("y".equals(answer)) {
                String decrypted = shift(encrypted, -key);
                System.out.println("Текст после обратного преобразования: " + decrypted);
                System.out.println("До свидания!");
                return;
            }

            if ("n".equals(answer)) {
                System.out.println("До свидания!");
                return;
            }

            System.out.println("Введите корректный ответ");
        }
    }

    private static String shift(String text, int key) {
        int normalizedKey = normalizeKey(key);
        StringBuilder builder = new StringBuilder(text.length());
        for (char ch : text.toCharArray()) {
            int shifted = (ch + normalizedKey) % UNICODE_RANGE;
            if (shifted < 0) {
                shifted += UNICODE_RANGE;
            }
            builder.append((char) shifted);
        }
        return builder.toString();
    }

    private static int normalizeKey(int key) {
        int normalized = key % UNICODE_RANGE;
        if (normalized < 0) {
            normalized += UNICODE_RANGE;
        }
        return normalized;
    }
}

