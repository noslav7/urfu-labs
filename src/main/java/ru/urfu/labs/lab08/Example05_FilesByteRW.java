package ru.urfu.labs.lab08;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

/**
 * Пример 5. Ввод указанного количества строк и запись в файл UTF-8 через DataOutputStream.
 */
public class Example05_FilesByteRW {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in, "cp1251")) {
            System.out.print("Введите имя файла => ");
            String fname = sc.nextLine();
            try {
                File f1 = new File(fname);
                f1.createNewFile();
                System.out.println("Полный путь файла:\t" + f1.getAbsolutePath());

                System.out.print("Введите количество строк для записи в файл => ");
                int n = sc.nextInt();
                try (DataOutputStream dOut = new DataOutputStream(new FileOutputStream(f1))) {
                    sc.nextLine(); // очистка буфера
                    for (int i = 0; i < n; i++) {
                        System.out.print("Введите строку для записи в файл => ");
                        String s = sc.nextLine();
                        dOut.writeUTF(s);
                    }
                    dOut.flush();
                }

                try (DataInputStream dIn = new DataInputStream(new FileInputStream(f1))) {
                    while (true) {
                        System.out.println(dIn.readUTF());
                    }
                }
            } catch (Exception e) {
                System.out.println("" + e);
            }
        }
    }
}

