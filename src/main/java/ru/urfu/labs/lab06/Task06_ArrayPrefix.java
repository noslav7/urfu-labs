package ru.urfu.labs.lab06;

import java.util.Arrays;

public class Task06_ArrayPrefix {

    public static int[] takeFirstElements(int[] source, int count) {
        if (source == null || count <= 0) {
            return new int[0];
        }
        if (count >= source.length) {
            return source.clone();
        }
        int[] result = new int[count];
        System.arraycopy(source, 0, result, 0, count);
        return result;
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};

        System.out.println("Исходный массив: " + Arrays.toString(numbers));
        System.out.println("Первые два элемента: " + Arrays.toString(takeFirstElements(numbers, 2)));
        System.out.println("Первые десять элементов: " + Arrays.toString(takeFirstElements(numbers, 10)));
        System.out.println("Ноль элементов: " + Arrays.toString(takeFirstElements(numbers, 0)));
    }
}



