package ru.urfu.labs.lab05;

import java.util.Scanner;

public class Task05_BoundedValue {

    private static final int MAX_VALUE = 100;

    private int value;

    public Task05_BoundedValue() {
        this.value = 0;
    }

    public Task05_BoundedValue(int value) {
        setValue(value);
    }

    public void setValue() {
        this.value = 0;
    }

    public void setValue(int value) {
        this.value = Math.min(value, MAX_VALUE);
    }

    public int getValue() {
        return value;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Task05_BoundedValue defaultValue = new Task05_BoundedValue();
        System.out.println("Значение по умолчанию: " + defaultValue.getValue());

        System.out.print("Введите значение для конструктора: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Ошибка: требуется целое число.");
            scanner.close();
            return;
        }
        int constructorValue = scanner.nextInt();

        Task05_BoundedValue fromConstructor = new Task05_BoundedValue(constructorValue);
        System.out.println("Значение после конструктора: " + fromConstructor.getValue());

        System.out.print("Введите значение для метода setValue(int): ");
        if (!scanner.hasNextInt()) {
            System.out.println("Ошибка: требуется целое число.");
            scanner.close();
            return;
        }
        int methodValue = scanner.nextInt();

        fromConstructor.setValue(methodValue);
        System.out.println("После вызова setValue(int): " + fromConstructor.getValue());

        fromConstructor.setValue();
        System.out.println("После вызова setValue(): " + fromConstructor.getValue());

        scanner.close();
    }
}


