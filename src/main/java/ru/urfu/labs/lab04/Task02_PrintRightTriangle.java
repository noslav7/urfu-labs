package ru.urfu.labs.lab04;

public class Task02_PrintRightTriangle {

    private static final int HEIGHT = 10;
    private static final char SYMBOL = '#';

    public static void main(String[] args) {
        for (int row = 1; row <= HEIGHT; row++) {
            System.out.println(createLine(row));
        }
    }

    private static String createLine(int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append(Task02_PrintRightTriangle.SYMBOL);
        }
        return builder.toString();
    }
}

