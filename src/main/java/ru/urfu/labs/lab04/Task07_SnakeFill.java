package ru.urfu.labs.lab04;

public class Task07_SnakeFill {

    private static final int ROWS = 7;
    private static final int COLUMNS = 9;

    public static void main(String[] args) {
        int[][] snake = fillSnake();
        printMatrix(snake);
    }

    private static int[][] fillSnake() {
        int[][] matrix = new int[Task07_SnakeFill.ROWS][Task07_SnakeFill.COLUMNS];
        int value = 1;

        for (int row = 0; row < Task07_SnakeFill.ROWS; row++) {
            if (row % 2 == 0) {
                for (int column = 0; column < Task07_SnakeFill.COLUMNS; column++) {
                    matrix[row][column] = value++;
                }
            } else {
                for (int column = Task07_SnakeFill.COLUMNS - 1; column >= 0; column--) {
                    matrix[row][column] = value++;
                }
            }
        }
        return matrix;
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

