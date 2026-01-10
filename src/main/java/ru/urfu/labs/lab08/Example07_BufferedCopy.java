package ru.urfu.labs.lab08;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Пример 7. Построчное копирование с буфером 1 КБ.
 */
public class Example07_BufferedCopy {
    public static void main(String[] args) {
        Path base = Paths.get("src", "main", "java", "ru", "urfu", "labs", "lab08", "lab08_data", "ex07");
        Path source = base.resolve("MyFile1.txt");
        Path dest = base.resolve("MyFile2.txt");
        try {
            Files.createDirectories(base);
            if (!Files.exists(source)) {
                Files.writeString(source, "Строка один\nСтрока два\nСтрока три", java.nio.charset.StandardCharsets.UTF_8);
            }

            try (BufferedReader br = new BufferedReader(new FileReader(source.toFile()), 1024);
                 BufferedWriter bw = new BufferedWriter(new FileWriter(dest.toFile()))) {
                String s;
                while ((s = br.readLine()) != null) {
                    bw.write(s);
                    bw.newLine();
                }
                bw.flush();
            }
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}

