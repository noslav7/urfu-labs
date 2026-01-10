package ru.urfu.labs.lab08;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Вспомогательный класс для подготовки файлов E:\\MyFile1.txt / E:\\MyFile2.txt
 * в кодировке cp1251, чтобы примеры 6, 9, 10 могли быть запущены без ручного создания файлов.
 * Запускать отдельно по необходимости.
 */
public class Example00_HelperFiles {
    public static void main(String[] args) throws IOException {
        Charset cp1251 = Charset.forName("cp1251");
        Path file1 = Paths.get("E:\\MyFile1.txt");
        Path file2 = Paths.get("E:\\MyFile2.txt");
        if (!Files.exists(file1)) {
            Files.writeString(file1, "Пример строки 1\nПример строки 2\nПример строки 3", cp1251);
            System.out.println("Создан " + file1);
        } else {
            System.out.println(file1 + " уже существует");
        }
        if (!Files.exists(file2)) {
            Files.writeString(file2, "", cp1251);
            System.out.println("Создан " + file2);
        } else {
            System.out.println(file2 + " уже существует");
        }
    }
}

