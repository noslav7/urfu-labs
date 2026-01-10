package ru.urfu.labs.lab08;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Пример 9. Буферизация символьных потоков на базе байтовых.
 */
public class Example09_BufWrIo4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            Charset cp1251 = Charset.forName("cp1251");
            Path base = Paths.get("src", "main", "java", "ru", "urfu", "labs", "lab08", "lab08_data", "ex09");
            Files.createDirectories(base);
            Path source = base.resolve("MyFile1.txt");
            Path dest = base.resolve("MyFile2.txt");

            if (!Files.exists(source)) {
                Files.writeString(source, "Строка один\nСтрока два\nСтрока три", cp1251);
            }

            br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(source.toFile()), cp1251));

            bw = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(dest.toFile()), cp1251));

            int lineCount = 0; // счетчик строк
            String s;
            while ((s = br.readLine()) != null) {
                lineCount++;
                System.out.println(lineCount + ": " + s);
                bw.write(lineCount + ": " + s); // запись без перевода строки
                bw.newLine(); // принудительный переход на новую строку
            }
        } catch (IOException e) {
            System.out.println("Ошибка!!!!!!!!");
        } finally {
            if (br != null) {
                br.close();
            }
            if (bw != null) {
                bw.flush();
                bw.close();
            }
        }
    }
}

