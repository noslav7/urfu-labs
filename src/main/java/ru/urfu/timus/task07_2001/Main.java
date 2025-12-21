package ru.urfu.timus.task07_2001;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int a1 =  input.nextInt();
        int b1 =  input.nextInt();
        int a2 =  input.nextInt();
        int b2 =  input.nextInt();
        int a3 =  input.nextInt();
        int b3 =  input.nextInt();

        int berries2 = b1 - b2;
        int berries1 = b3 - berries2 - b2;

        System.out.println(berries1 + " " + berries2);


        input.close();
    }
}
