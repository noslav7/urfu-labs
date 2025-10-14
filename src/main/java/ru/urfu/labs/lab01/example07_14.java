package ru.urfu.labs.lab01;

import java.util.Calendar;
import java.util.Scanner;

public class example07_14 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input age: ");
        int age = in.nextInt();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int birthYear = currentYear - age;
        System.out.printf("Birth year: %d%n", birthYear);
        in.close();
    }
}
