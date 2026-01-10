package ru.urfu.labs.lab08;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Задание 2: из файла с двумя строками (UTF-8) и пятью числами double
 * переписать во второй файл вторую строку и положительные числа.
 * Файл-источник создается программно из ввода пользователя (предварительно).
 */
public class Task02_FilterNumbersAndStrings {

    private static final Path BASE_DIR = Paths.get("lab08_data", "task02");
    private static final Path SOURCE_FILE = BASE_DIR.resolve("source.bin");
    private static final Path RESULT_FILE = BASE_DIR.resolve("result.bin");

    public static void main(String[] args) throws IOException {
        Files.createDirectories(BASE_DIR);
        prepareSourceFileFromInput();
        copyFilteredData();
        printResultFile();
    }

    private static void prepareSourceFileFromInput() throws IOException {
        Files.deleteIfExists(SOURCE_FILE);
        try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
             DataOutputStream out = new DataOutputStream(new FileOutputStream(SOURCE_FILE.toFile()))) {
            System.out.println("Введите две строки (UTF-8):");
            String firstLine = scanner.nextLine();
            String secondLine = scanner.nextLine();
            out.writeUTF(firstLine);
            out.writeUTF(secondLine);

            System.out.println("Введите 5 чисел типа double:");
            for (int i = 0; i < 5; i++) {
                double number = scanner.nextDouble();
                out.writeDouble(number);
            }
            out.flush();
        }
    }

    private static void copyFilteredData() throws IOException {
        Files.deleteIfExists(RESULT_FILE);
        String secondLine;
        List<Double> positiveNumbers = new ArrayList<>();

        try (DataInputStream in = new DataInputStream(new FileInputStream(SOURCE_FILE.toFile()))) {
            in.readUTF(); // первая строка пропускается
            secondLine = in.readUTF();
            while (true) {
                try {
                    double value = in.readDouble();
                    if (value > 0) {
                        positiveNumbers.add(value);
                    }
                } catch (EOFException eof) {
                    break;
                }
            }
        }

        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(RESULT_FILE.toFile()))) {
            out.writeUTF(secondLine);
            for (double value : positiveNumbers) {
                out.writeDouble(value);
            }
            out.flush();
        }
    }

    private static void printResultFile() throws IOException {
        System.out.println("Результат из файла " + RESULT_FILE.toAbsolutePath());
        try (DataInputStream in = new DataInputStream(new FileInputStream(RESULT_FILE.toFile()))) {
            String line = in.readUTF();
            System.out.println("Строка: " + line);
            System.out.print("Положительные числа: ");
            List<String> values = new ArrayList<>();
            while (true) {
                try {
                    values.add(String.valueOf(in.readDouble()));
                } catch (EOFException eof) {
                    break;
                }
            }
            System.out.println(String.join(", ", values));
        }
    }
}
