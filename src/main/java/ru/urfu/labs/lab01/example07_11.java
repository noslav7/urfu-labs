package ru.urfu.labs.lab01;

import java.util.Scanner;

public class example07_11 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input month name: ");
        String month = in.nextLine();
        System.out.print("Input number of days: ");
        int days = in.nextInt();
        System.out.printf("Month %s contains %d days.%n", month, days);
        in.close();
    }
}
