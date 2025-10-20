package ru.urfu.lab02;

import java.util.Scanner;

public class Task06_Residues5and7Again {
    public static void main(String[] args) {
        System.out.print("Введите целое число: ");

        Scanner scanner = new Scanner(System.in);
        long number = scanner.nextLong();

        boolean matches = number % 5 == 2 && number % 7 == 1;
        System.out.println("Остатки по условиям (mod5==2 и mod7==1): " + (matches ? "да" : "нет"));

        scanner.close();
    }
}


