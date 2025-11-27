package ru.urfu.labs.lab05;

import java.util.Scanner;

public class Task06_MinMaxManager {

    private int max;
    private int min;
    private boolean initialized;

    public Task06_MinMaxManager() {
    }

    public Task06_MinMaxManager(int value) {
        updateBounds(value);
    }

    public Task06_MinMaxManager(int first, int second) {
        updateBounds(first, second);
    }

    public void setValues(int value) {
        updateBounds(value);
    }

    public void setValues(int first, int second) {
        updateBounds(first, second);
    }

    public void printValues() {
        if (!initialized) {
            System.out.println("Значения не установлены.");
        } else {
            System.out.println("min = " + min + ", max = " + max);
        }
    }

    private void updateBounds(int... values) {
        if (values == null || values.length == 0) {
            return;
        }

        int localMin = values[0];
        int localMax = values[0];
        for (int value : values) {
            if (value < localMin) {
                localMin = value;
            }
            if (value > localMax) {
                localMax = value;
            }
        }

        if (!initialized) {
            min = localMin;
            max = localMax;
            initialized = true;
        } else {
            if (localMin < min) {
                min = localMin;
            }
            if (localMax > max) {
                max = localMax;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Task06_MinMaxManager manager = new Task06_MinMaxManager();
        manager.printValues();

        System.out.print("Введите значение для обновления: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Ошибка: требуется целое число.");
            scanner.close();
            return;
        }
        int value = scanner.nextInt();
        manager.setValues(value);
        manager.printValues();

        System.out.print("Введите два значения для обновления диапазона: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Ошибка: требуется целое число.");
            scanner.close();
            return;
        }
        int first = scanner.nextInt();
        if (!scanner.hasNextInt()) {
            System.out.println("Ошибка: требуется целое число.");
            scanner.close();
            return;
        }
        int second = scanner.nextInt();
        manager.setValues(first, second);
        manager.printValues();

        scanner.close();
    }
}


