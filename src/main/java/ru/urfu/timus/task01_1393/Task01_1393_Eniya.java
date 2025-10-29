package ru.urfu.timus.task01_1393;

import java.io.PrintWriter;
import java.util.Scanner;

public class Task01_1393_Eniya {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N =  input.nextInt();
        int A =  input.nextInt();
        int B =  input.nextInt();

        int result = (N * (A * B)) * 2;

        PrintWriter out = new PrintWriter(System.out);
        out.println(result);
        out.flush();
        input.close();
    }
}
