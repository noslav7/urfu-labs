package ru.urfu.labs.lab04;

public class Task03_ArrayRectangleOfTwos {

    private static final int ROWS = 6;
    private static final int COLUMNS = 10;

    public static void main(String[] args) {
        int[][] rectangle = new int[ROWS][COLUMNS];
        fillWithTwos(rectangle);
        printMatrix(rectangle);
    }

    private static void fillWithTwos(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                matrix[row][column] = 2;
            }
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}

