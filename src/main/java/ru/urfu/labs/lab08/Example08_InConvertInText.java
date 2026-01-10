package ru.urfu.labs.lab08;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Пример 8. Конвертация байтового потока в символьный через InputStreamReader с кодировкой.
 */
public class Example08_InConvertInText {

    private static final Charset CP1251 = Charset.forName("cp1251");

    public static void readAllByByte(Reader in) throws IOException {
        while (true) {
            int oneByte = in.read();
            if (oneByte != -1) {
                System.out.print((char) oneByte);
            } else {
                System.out.print("\n" + " конец ");
                break;
            }
        }
    }

    public static void main(String[] args) {
        try {
            Path base = Paths.get("src", "main", "java", "ru", "urfu", "labs", "lab08", "lab08_data", "ex08");
            Files.createDirectories(base);
            Path file = base.resolve("MyFile1.txt");
            Files.writeString(file, "Работа с кодировкой через InputStreamReader.", CP1251);

            // С потоком связан файл
            InputStream inFile = Files.newInputStream(file);
            Reader rFile = new InputStreamReader(inFile, CP1251);
            readAllByByte(rFile);
            System.out.print("\n\n\n");
            inFile.close();
            rFile.close();

            // С потоком связана интернет-страница
            InputStream inUrl = URI.create("https://example.com").toURL().openStream();
            Reader rUrl = new InputStreamReader(inUrl, CP1251);
            readAllByByte(rUrl);
            System.out.print("\n\n\n");
            inUrl.close();
            rUrl.close();

            // С потоком связан массив типа byte
            InputStream inArray = new ByteArrayInputStream(new byte[]{5, 8, 3, 9, 11});
            Reader rArray = new InputStreamReader(inArray, CP1251);
            readAllByByte(rArray);
            System.out.print("\n\n\n");
            inArray.close();
            rArray.close();
        } catch (IOException e) {
            System.out.println("Ошибка: " + e);
        }
    }
}

