package ru.urfu.labs.lab01;

import java.util.Scanner;

public class example07_10 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input day of week: ");
        String day = in.nextLine();
        System.out.print("Input month: ");
        String month = in.nextLine();
        System.out.print("Input date (day of month): ");
        int date = in.nextInt();
        System.out.printf("Segodnya: %s, %d, %s%n", day, date, month);
        in.close();
    }
}
