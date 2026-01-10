package ru.urfu.labs.lab08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;

/**
 * Пример 10. Чтение из одного файла и запись в другой с использованием PrintWriter.
 */
public class Example10_BufRw2 {
    public static void main(String[] args) {
        BufferedReader br = null;
        PrintWriter out = null;
        try {
            Charset cp1251 = Charset.forName("cp1251");
            br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("E:\\MyFile1.txt"), cp1251));

            out = new PrintWriter("E:\\MyFile2.txt", "cp1251");

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

