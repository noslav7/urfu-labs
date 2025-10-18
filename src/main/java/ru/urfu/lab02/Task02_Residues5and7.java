package ru.urfu.lab02;

import java.util.Scanner;

public class Task02_Residues5and7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите целое число: ");
        long number = scanner.nextLong();
        boolean matches = number % 5 == 2 && number % 7 == 1;
        System.out.println("Остатки по условиям (mod5==2 и mod7==1): " + (matches ? "да" : "нет"));
        scanner.close();
    }
}


