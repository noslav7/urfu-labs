package ru.urfu.labs.lab04;

import java.util.Random;

public class Task06_RemoveRowAndColumn {

    private static final int ROWS = 5;
    private static final int COLUMNS = 6;

    public static void main(String[] args) {
        int[][] matrix = buildInitialMatrix();

        System.out.println("Исходный массив:");
        printMatrix(matrix);

        Random random = new Random();
        int rowToRemove = random.nextInt(matrix.length);
        int columnToRemove = random.nextInt(matrix[0].length);

        System.out.printf("Удаляем строку с индексом %d и столбец с индексом %d%n", rowToRemove, columnToRemove);

        int[][] reduced = removeRowAndColumn(matrix, rowToRemove, columnToRemove);
        System.out.println("Результирующий массив:");
        printMatrix(reduced);
    }

    private static int[][] buildInitialMatrix() {
        int[][] matrix = new int[Task06_RemoveRowAndColumn.ROWS][Task06_RemoveRowAndColumn.COLUMNS];
        int value = 1;
        for (int row = 0; row < Task06_RemoveRowAndColumn.ROWS; row++) {
            for (int column = 0; column < Task06_RemoveRowAndColumn.COLUMNS; column++) {
                matrix[row][column] = value++;
            }
        }
        return matrix;
    }

    private static int[][] removeRowAndColumn(int[][] matrix, int rowToRemove, int columnToRemove) {
        int[][] result = new int[matrix.length - 1][matrix[0].length - 1];
        int newRow = 0;
        for (int row = 0; row < matrix.length; row++) {
            if (row == rowToRemove) {
                continue;
            }
            int newColumn = 0;
            for (int column = 0; column < matrix[row].length; column++) {
                if (column == columnToRemove) {
                    continue;
                }
                result[newRow][newColumn++] = matrix[row][column];
            }
            newRow++;
        }
        return result;
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.printf("%3d ", value);
            }
            System.out.println();
        }
    }
}

