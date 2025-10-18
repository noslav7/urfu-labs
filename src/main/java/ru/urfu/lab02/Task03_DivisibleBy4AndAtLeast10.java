package ru.urfu.lab02;

import java.util.Scanner;

public class Task03_DivisibleBy4AndAtLeast10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите целое число: ");
        long number = scanner.nextLong();
        boolean matches = number % 4 == 0 && number >= 10;
        System.out.println("Делится на 4 и не меньше 10: " + (matches ? "да" : "нет"));
        scanner.close();
    }
}


