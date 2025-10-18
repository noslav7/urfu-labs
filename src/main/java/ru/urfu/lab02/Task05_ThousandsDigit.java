package ru.urfu.lab02;

import java.util.Scanner;

public class Task05_ThousandsDigit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите целое число: ");
        long number = scanner.nextLong();
        long thousandsDigit = Math.abs(number) / 1000 % 10;
        System.out.println("Число тысяч (4-я справа цифра): " + thousandsDigit);
        scanner.close();
    }
}


