package ru.urfu.labs.lab01;

import java.util.Scanner;

public class example07_08 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input surname: ");
        String surname = in.nextLine();
        System.out.print("Input name: ");
        String name = in.nextLine();
        System.out.print("Input patronymic: ");
        String patronymic = in.nextLine();
        System.out.println("Hallo " + surname + ", " + name + ", " + patronymic);
        in.close();
    }
}
