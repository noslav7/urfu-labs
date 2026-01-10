package ru.urfu.labs.lab08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Пример 10. Чтение из одного файла и запись в другой с использованием PrintWriter.
 */
public class Example10_BufRw2 {
    public static void main(String[] args) {
        BufferedReader br = null;
        PrintWriter out = null;
        try {
            Charset cp1251 = Charset.forName("cp1251");
            Path base = Paths.get("src", "main", "java", "ru", "urfu", "labs", "lab08", "lab08_data", "ex10");
            Files.createDirectories(base);
            Path source = base.resolve("MyFile1.txt");
            Path dest = base.resolve("MyFile2.txt");
            if (!Files.exists(source)) {
                Files.writeString(source, "Строка один\nСтрока два\nСтрока три", cp1251);
            }

            br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(source.toFile()), cp1251));

            out = new PrintWriter(dest.toFile(), "cp1251");

            int lineCount = 0;
            String s;
            while ((s = br.readLine()) != null) {
                lineCount++;
                out.println(lineCount + ": " + s);
            }
        } catch (IOException e) {
            System.out.println("Ошибка !!!!!!!!");
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ignored) { }
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }
}

