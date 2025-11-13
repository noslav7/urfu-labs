package ru.urfu.timus.task06_1877;

import java.util.Scanner;

public class Task06_1877_BicycleCodes {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int code1 = input.nextInt();
        int code2 = input.nextInt();

        if (code1 % 2 == 0 || code2 % 2 != 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        input.close();
    }
}
