package ru.urfu.labs.lab04;

import java.util.Random;

public class Task05_ArrayTranspose {

    private static final int ROWS = 3;
    private static final int COLUMNS = 5;
    private static final int RANDOM_BOUND = 90;
    private static final int RANDOM_SHIFT = 10;

    public static void main(String[] args) {
        int[][] original = new int[ROWS][COLUMNS];
        fillRandom(original);

        System.out.println("Исходный массив:");
        printMatrix(original);

        int[][] transposed = transpose(original);
        System.out.println("Транспонированный массив:");
        printMatrix(transposed);
    }

    private static void fillRandom(int[][] matrix) {
        Random random = new Random();
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                matrix[row][column] = random.nextInt(RANDOM_BOUND) + RANDOM_SHIFT;
            }
        }
    }

    private static int[][] transpose(int[][] matrix) {
        int[][] result = new int[matrix[0].length][matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                result[column][row] = matrix[row][column];
            }
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

