package ru.urfu.labs.lab08;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * Пример 6. Посимвольное копирование из одного файла в другой.
 * Метод main генерирует IOException (как в методичке).
 */
public class Example06_FileRWByByte {
    public static void main(String[] args) throws IOException {
        Reader in = null;
        Writer out = null;
        try {
            in = new FileReader("E:\\MyFile1.txt"); // файл для чтения
            out = new FileWriter("E:\\MyFile2.txt", true); // разрешено добавление

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

