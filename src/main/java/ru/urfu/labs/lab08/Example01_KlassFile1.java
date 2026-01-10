package ru.urfu.labs.lab08;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Пример 1. Создание файлов и папок (как в методичке).
 * Пути вынесены в подпапку lab08_data/ex01, чтобы не писать на диск E:.
 */
public class Example01_KlassFile1 {
    public static void main(String[] args) {
        try {
            Path base = Paths.get("lab08_data", "ex01");
            Files.createDirectories(base);

            // Создание файла в текущей папке (каталог примеров)
            File f1 = base.resolve("MyFile1.txt").toFile();
            f1.createNewFile();
            if (f1.exists()) {
                System.out.println("Создан!!!!");
                System.out.println("Полный путь1:\t" + f1.getAbsolutePath());
            }

            // Создание файла и вывод полного пути
            File f2 = base.resolve("MyFile2.txt").toFile();
            f2.createNewFile();
            System.out.println("Полный путь 2:\t" + f2.getAbsolutePath());

            // Создание нескольких вложенных папок
            File f3 = base.resolve("Papka1").resolve("Papka2").resolve("Papka3").toFile();
            f3.mkdirs();
            System.out.println("Полный путь 3:\t" + f3.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Ошибка!!! " + e);
        }
    }
}

