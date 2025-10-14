package ru.urfu.labs.lab01;

import java.util.Scanner;

public class example07_15 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input first number: ");
        double a = in.nextDouble();
        System.out.print("Input second number: ");
        double b = in.nextDouble();
        double sum = a + b;
        System.out.printf("Sum: %.6f%n", sum);
        in.close();
    }
}
