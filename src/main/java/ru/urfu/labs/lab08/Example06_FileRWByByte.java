package ru.urfu.labs.lab08;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Пример 6. Посимвольное копирование из одного файла в другой.
 * Метод main генерирует IOException (как в методичке).
 */
public class Example06_FileRWByByte {
    public static void main(String[] args) throws IOException {
        Reader in = null;
        Writer out = null;
        try {
            Path base = Paths.get("src", "main", "java", "ru", "urfu", "labs", "lab08", "lab08_data", "ex06");
            Files.createDirectories(base);
            Path source = base.resolve("MyFile1.txt");
            Path dest = base.resolve("MyFile2.txt");
            Charset cp1251 = Charset.forName("cp1251");

            if (!Files.exists(source)) {
                Files.writeString(source, "Пример строки 1\nПример строки 2\nПример строки 3", cp1251);
            }

            in = new FileReader(source.toFile()); // файл для чтения
            out = new FileWriter(dest.toFile(), true); // разрешено добавление

            int oneByte; // переменная, в которую считываются данные
            while ((oneByte = in.read()) != -1) {
                out.append((char) oneByte); // запись с добавлением данных в конец
                System.out.print((char) oneByte);
            }
        } catch (IOException e) {
            System.out.println("Ошибка!!!! ");
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}

