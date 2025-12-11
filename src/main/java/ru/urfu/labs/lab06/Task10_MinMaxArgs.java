package ru.urfu.labs.lab06;

import java.util.Arrays;

public class Task10_MinMaxArgs {

    public static int[] findMaxAndMin(int... values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Нужно передать хотя бы одно число.");
        }
        int maxValue = values[0];
        int minValue = values[0];
        for (int value : values) {
            if (value > maxValue) {
                maxValue = value;
            }
            if (value < minValue) {
                minValue = value;
            }
        }
        return new int[] {maxValue, minValue};
    }

    public static void main(String[] args) {
        System.out.println("Результат: " + Arrays.toString(findMaxAndMin(3, 9, -2, 7)));
    }
}



