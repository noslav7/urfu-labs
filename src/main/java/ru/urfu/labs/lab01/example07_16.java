package ru.urfu.labs.lab01;

import java.util.Scanner;

public class example07_16 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input number: ");
        long x = in.nextLong();
        long a = x - 1;
        long b = x;
        long c = x + 1;
        long d = (a + b + c) * (a + b + c);
        System.out.printf("%d %d %d %d%n", a, b, c, d);
        in.close();
    }
}
