package ru.urfu.labs.lab08;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Пример 4 из методички. Работа с DataInputStream/DataOutputStream и числами float.
 * Кодировка Scanner — cp1251 для корректного ввода кириллицы в консоли Windows.
 */
public class Example04_FilesData {
    public static void main(String[] args) {
        Path base = Paths.get("lab08_data", "ex04", "My");
        try {
            Files.createDirectories(base);
            File f1 = base.resolve("numIsh.txt").toFile();
            f1.createNewFile();

            try (Scanner sc = new Scanner(System.in, "cp1251");
                 DataOutputStream wr = new DataOutputStream(new FileOutputStream(f1.getAbsolutePath()))) {
                System.out.println("Сколько вещественных чисел записать в файл?");
                int count = sc.nextInt();
                System.out.println("Введите числа:");
                for (int i = 0; i < count; i++) {
                    wr.writeFloat(sc.nextFloat());
                }
                wr.flush();
            }

            File f2 = base.resolve("numRez.txt").toFile();
            f2.createNewFile();
            try (DataInputStream rd = new DataInputStream(new FileInputStream(f1.getAbsolutePath()));
                 DataOutputStream wr = new DataOutputStream(new FileOutputStream(f2.getAbsolutePath()))) {
                try {
                    while (true) {
                        float number = rd.readFloat();
                        wr.writeFloat(number);
                        System.out.println(" Число " + number);
                    }
                } catch (EOFException e) {
                    // конец файла
                }
                wr.flush();
            }
        } catch (IOException e) {
            System.out.println("End of file");
        }
    }
}

