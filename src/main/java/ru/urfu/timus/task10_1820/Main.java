package ru.urfu.timus.task10_1820;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int k = in.nextInt();

        int time = (2 * n + k - 1) / k;
        System.out.println(time);
    }
}
