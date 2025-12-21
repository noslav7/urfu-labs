package ru.urfu.timus.task05_2012;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int numberOfTasks = input.nextInt();

        int difference = 12 - numberOfTasks;

        int time = difference * 45;

        String result = time <= 240 ? "YES" : "NO";

//        if (time <= 240) {
//            System.out.println("YES");
//        } else {
//            System.out.println("NO");
//        }

        System.out.println(result);
        out.flush();
        input.close();
    }
}
