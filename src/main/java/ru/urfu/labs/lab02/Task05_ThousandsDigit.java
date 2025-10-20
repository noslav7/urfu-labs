package ru.urfu.labs.lab02;

import java.util.Scanner;

public class Task05_ThousandsDigit {
    public static void main(String[] args) {
        System.out.print("Введите целое число: ");

        Scanner scanner = new Scanner(System.in);
        long number = scanner.nextLong();

        long thousandsDigit = Math.abs(number) / 1000 % 10;
        System.out.println("Число тысяч (4-я справа цифра): " + thousandsDigit);

        scanner.close();
    }
}


