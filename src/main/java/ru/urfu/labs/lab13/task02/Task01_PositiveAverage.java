package ru.urfu.labs.lab13.task02;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Таблица 1, задание 1. Среднее среди положительных элементов int-массива с клавиатуры.
 * <p>
 * Экспериментально выявленные исключения:
 * <ul>
 *   <li>{@link InputMismatchException} — ввод нецелого токена (строка, дробное число) через {@link Scanner#nextInt()};</li>
 *   <li>{@link NumberFormatException} — значение не укладывается в тип {@code int} при разборе строки;</li>
 *   <li>{@link ArithmeticException} — положительные элементы отсутствуют (деление на ноль).</li>
 * </ul>
 */
public class Task01_PositiveAverage {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Среднее положительных элементов массива ===");

        try {
            System.out.print("Размер массива: ");
            int size = readInt(scanner);
            if (size <= 0) {
                throw new InputMismatchException("размер <= 0");
            }
            int[] array = new int[size];

            for (int i = 0; i < size; i++) {
                System.out.printf("Элемент [%d]: ", i);
                array[i] = readInt(scanner);
            }

            System.out.println("Массив: " + Arrays.toString(array));
            double average = averageOfPositive(array);
            System.out.printf("Среднее положительных элементов: %.4f%n", average);
        } catch (InputMismatchException e) {
            System.out.println("Ошибка: ввод строки вместо числа.");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: несоответствие числового типа данных (int).");
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: положительные элементы отсутствуют.");
        } finally {
            scanner.close();
            System.out.println("Завершение работы программы (finally).");
        }
    }

    private static int readInt(Scanner scanner) {
        if (!scanner.hasNext()) {
            throw new InputMismatchException("нет данных");
        }
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        }
        String token = scanner.next();
        if (token.matches("-?\\d+\\.\\d+([Ee][+-]?\\d+)?")) {
            throw new InputMismatchException("дробное число");
        }
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            if (token.matches("-?\\d+")) {
                throw e;
            }
            throw new InputMismatchException(token);
        }
    }

    private static double averageOfPositive(int[] array) {
        long sum = 0;
        int count = 0;
        for (int value : array) {
            if (value > 0) {
                sum += value;
                count++;
            }
        }
        if (count == 0) {
            throw new ArithmeticException("/ by zero");
        }
        return sum / (double) count;
    }
}
