package ru.urfu.labs.lab01;

import java.util.Calendar;
import java.util.Scanner;

public class example07_12 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input birth year: ");
        int birthYear = in.nextInt();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int age = Math.max(0, currentYear - birthYear);
        System.out.printf("Age: %d%n", age);
        in.close();
    }
}
