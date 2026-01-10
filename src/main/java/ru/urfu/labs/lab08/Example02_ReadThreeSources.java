package ru.urfu.labs.lab08;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Пример 2. Прочитать и вывести информацию из трех источников:
 * файл, интернет-страница и массив byte.
 * Кодировка вывода — UTF-8 для консоли.
 */
public class Example02_ReadThreeSources {

    private static final Charset OUTPUT = StandardCharsets.UTF_8;

    public static void readAllByByte(InputStream in, Charset charset) throws IOException {
        try (BufferedInputStream buffered = new BufferedInputStream(in)) {
            byte[] data = buffered.readAllBytes();
            System.out.println(new String(data, charset));
        }
    }

    public static void main(String[] args) {
        try {
            Path base = Paths.get("lab08_data", "ex02");
            Files.createDirectories(base);
            Path file = base.resolve("source.txt");
            Files.writeString(file, "Строка из файла\nВторая строка", OUTPUT);

            System.out.println("=== Файл ===");
            try (InputStream inFile = new FileInputStream(file.toFile())) {
                readAllByByte(inFile, OUTPUT);
            }

            System.out.println("\n=== Интернет-страница ===");
            try (InputStream inUrl = URI.create("https://example.com").toURL().openStream()) {
                readAllByByte(inUrl, OUTPUT);
            } catch (IOException e) {
                System.out.println("Не удалось прочитать страницу: " + e.getMessage());
            }

            System.out.println("\n=== Массив byte ===");
            byte[] array = "Данные из массива byte".getBytes(OUTPUT);
            try (InputStream inArray = new ByteArrayInputStream(array)) {
                readAllByByte(inArray, OUTPUT);
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e);
        }
    }
}

