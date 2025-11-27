package ru.urfu.timus.task08_1264;

import java.util.Scanner;

public class Task08_1264_WorkdayRoutine {

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            int n = input.nextInt();
            int m = input.nextInt();

            int numberOfM = m + 1;

            int result = n * numberOfM;

            System.out.println(result);
        }
    }
}
