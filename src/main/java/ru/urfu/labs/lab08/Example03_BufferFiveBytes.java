package ru.urfu.labs.lab08;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Пример 3. Чтение из файла с использованием буфера в 5 байт.
 */
public class Example03_BufferFiveBytes {
    public static void main(String[] args) {
        try {
            Path base = Paths.get("src", "main", "java", "ru", "urfu", "labs", "lab08", "lab08_data", "ex03");
            Files.createDirectories(base);
            Path file = base.resolve("buffered.txt");
            Files.writeString(file, "Буфер в 5 байт показывает работу чтения.", StandardCharsets.UTF_8);

            try (InputStream in = new FileInputStream(file.toFile())) {
                byte[] buffer = new byte[5];
                int read;
                while ((read = in.read(buffer)) != -1) {
                    System.out.print(new String(buffer, 0, read, StandardCharsets.UTF_8));
                }
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}

