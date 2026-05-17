package ru.urfu.labs.lab13.task02;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Таблица 1, задание 2. Вывод столбца матрицы по номеру, введённому с клавиатуры.
 * <p>
 * Экспериментально выявленные исключения:
 * <ul>
 *   <li>{@link InputMismatchException} — ввод нецелого номера столбца;</li>
 *   <li>{@link ArrayIndexOutOfBoundsException} — столбца с таким номером нет.</li>
 * </ul>
 */
public class Task02_MatrixColumn {

    private static final int[][] MATRIX = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Столбец матрицы по номеру ===");
        printMatrix(MATRIX);

        try {
            System.out.print("Номер столбца (0 — первый): ");
            int columnIndex = readColumnIndex(scanner);
            printColumn(MATRIX, columnIndex);
        } catch (InputMismatchException e) {
            System.out.println("Ошибка: ввод строки вместо числа.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка: нет столбца с таким номером.");
        } finally {
            scanner.close();
            System.out.println("Завершение работы программы (finally).");
        }
    }

    private static int readColumnIndex(Scanner scanner) {
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
        throw new InputMismatchException(token);
    }

    private static void printColumn(int[][] matrix, int columnIndex) {
        System.out.printf("Столбец [%d]:%n", columnIndex);
        for (int row = 0; row < matrix.length; row++) {
            System.out.println(matrix[row][columnIndex]);
        }
    }

    private static void printMatrix(int[][] matrix) {
        System.out.println("Матрица:");
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.printf("%4d ", value);
            }
            System.out.println();
        }
    }
}
