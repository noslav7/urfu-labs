package ru.urfu.labs.lab02;

import java.util.Scanner;

public class Task01_DivisibleBy3 {
    public static void main(String[] args) {
        System.out.print("Введите целое число: ");

        Scanner scanner = new Scanner(System.in);
        long number = scanner.nextLong();

        boolean isDivisible = number % 3 == 0;
        System.out.println("Число делится на 3: " + (isDivisible ? "да" : "нет"));

        scanner.close();
    }
}


