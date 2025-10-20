package ru.urfu.lab02;

import java.util.Scanner;

public class Task04_InRange5to10Inclusive {
    public static void main(String[] args) {
        System.out.print("Введите целое число: ");

        Scanner scanner = new Scanner(System.in);
        long number = scanner.nextLong();

        boolean inRange = number >= 5 && number <= 10;
        System.out.println("Число в диапазоне [5, 10]: " + (inRange ? "да" : "нет"));

        scanner.close();
    }
}


