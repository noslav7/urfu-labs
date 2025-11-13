package ru.urfu.labs.lab04;

public class Task01_PrintRectangle {

    private static final int WIDTH = 23;
    private static final int HEIGHT = 11;
    private static final char SYMBOL = '*';

    public static void main(String[] args) {
        String line = createLine();
        for (int row = 0; row < HEIGHT; row++) {
            System.out.println(line);
        }
    }

    private static String createLine() {
        StringBuilder builder = new StringBuilder(Task01_PrintRectangle.WIDTH);
        for (int i = 0; i < Task01_PrintRectangle.WIDTH; i++) {
            builder.append(Task01_PrintRectangle.SYMBOL);
        }
        return builder.toString();
    }
}

