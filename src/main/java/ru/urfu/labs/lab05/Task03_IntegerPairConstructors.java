package ru.urfu.labs.lab05;

import java.util.Scanner;

public class Task03_IntegerPairConstructors {

    private final int first;
    private final int second;

    public Task03_IntegerPairConstructors() {
        this(0, 0);
    }

    public Task03_IntegerPairConstructors(int value) {
        this(value, value);
    }

    public Task03_IntegerPairConstructors(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "first=" + first + ", second=" + second;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Task03_IntegerPairConstructors defaultPair = new Task03_IntegerPairConstructors();
        System.out.println("Пара без аргументов: " + defaultPair);

        System.out.print("Введите значение для конструктора с одним аргументом: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Ошибка: требуется целое число.");
            scanner.close();
            return;
        }
        int singleValue = scanner.nextInt();
        Task03_IntegerPairConstructors singlePair = new Task03_IntegerPairConstructors(singleValue);
        System.out.println("Пара с одним аргументом: " + singlePair);

        System.out.print("Введите два целых числа для конструктора с двумя аргументами: ");
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

        Task03_IntegerPairConstructors pair = new Task03_IntegerPairConstructors(first, second);
        System.out.println("Пара с двумя аргументами: " + pair);

        scanner.close();
    }
}


