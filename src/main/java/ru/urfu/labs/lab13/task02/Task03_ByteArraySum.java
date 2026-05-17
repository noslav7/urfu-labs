package ru.urfu.labs.lab13.task02;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Таблица 1, задание 3. Сумма элементов массива типа {@code byte}, вводимого с клавиатуры.
 * <p>
 * Экспериментально выявленные исключения:
 * <ul>
 *   <li>{@link InputMismatchException} — ввод нечисловой строки;</li>
 *   <li>{@link NumberFormatException} — число вне диапазона {@code byte} или нецелое;</li>
 *   <li>{@link ArithmeticException} — сумма элементов выходит за границы типа {@code byte}.</li>
 * </ul>
 */
public class Task03_ByteArraySum {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Сумма элементов массива byte ===");

        try {
            System.out.print("Размер массива: ");
            int size = readPositiveInt(scanner);
            byte[] array = new byte[size];

            for (int i = 0; i < size; i++) {
                System.out.printf("Элемент [%d] (от %d до %d): ", i, Byte.MIN_VALUE, Byte.MAX_VALUE);
                array[i] = readByte(scanner);
            }

            System.out.println("Массив: " + Arrays.toString(array));
            byte sum = sumBytes(array);
            System.out.println("Сумма элементов (byte): " + sum);
        } catch (InputMismatchException e) {
            System.out.println("Ошибка: ввод строки вместо числа.");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: значение за границами диапазона типа byte или неверный формат числа.");
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: сумма элементов выходит за границы диапазона типа byte.");
        } finally {
            scanner.close();
            System.out.println("Завершение работы программы (finally).");
        }
    }

    private static int readPositiveInt(Scanner scanner) {
        if (!scanner.hasNextInt()) {
            throw new InputMismatchException();
        }
        int size = scanner.nextInt();
        if (size <= 0) {
            throw new InputMismatchException("размер <= 0");
        }
        return size;
    }

    private static byte readByte(Scanner scanner) {
        if (!scanner.hasNext()) {
            throw new InputMismatchException("нет данных");
        }
        String token = scanner.next();
        if (!token.matches("-?\\d+")) {
            throw new InputMismatchException(token);
        }
        return Byte.parseByte(token);
    }

    private static byte sumBytes(byte[] array) {
        int sum = 0;
        for (byte value : array) {
            sum += value;
        }
        if (sum < Byte.MIN_VALUE || sum > Byte.MAX_VALUE) {
            throw new ArithmeticException("overflow");
        }
        return (byte) sum;
    }
}
