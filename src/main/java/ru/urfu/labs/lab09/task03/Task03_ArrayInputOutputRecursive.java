package ru.urfu.labs.lab09.task03;

import java.util.Scanner;

/**
 * Лабораторная работа №09, задание 3.
 * Рекурсивный ввод и вывод одномерного массива целых чисел.
 */
public class Task03_ArrayInputOutputRecursive {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите размер массива: ");
            int size = scanner.nextInt();

            if (size < 0) {
                System.out.println("Размер массива не может быть отрицательным.");
                return;
            }

            int[] array = new int[size];
            readArrayRecursive(scanner, array, 0);

            System.out.print("Введенный массив: ");
            printArrayRecursive(array, 0);
            System.out.println();
        }
    }

    private static void readArrayRecursive(Scanner scanner, int[] array, int index) {
        if (index == array.length) {
            return;
        }

        System.out.print("array[" + index + "] = ");
        array[index] = scanner.nextInt();
        readArrayRecursive(scanner, array, index + 1);
    }

    private static void printArrayRecursive(int[] array, int index) {
        if (index == array.length) {
            return;
        }

        if (index > 0) {
            System.out.print(" ");
        }
        System.out.print(array[index]);
        printArrayRecursive(array, index + 1);
    }
}
