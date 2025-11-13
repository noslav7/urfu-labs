package ru.urfu.labs.lab04;

public class Task04_ArrayTriangle {

    private static final int SIZE = 8;
    private static final int FILL_VALUE = 2;

    public static void main(String[] args) {
        int[][] triangle = new int[SIZE][SIZE];
        fillLowerTriangle(triangle);
        printMatrix(triangle);
    }

    private static void fillLowerTriangle(int[][] triangle) {
        for (int row = 0; row < triangle.length; row++) {
            for (int column = 0; column <= row; column++) {
                triangle[row][column] = FILL_VALUE;
            }
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                if (value == 0) {
                    System.out.print("  ");
                } else {
                    System.out.print(value + " ");
                }
            }
            System.out.println();
        }
    }
}

