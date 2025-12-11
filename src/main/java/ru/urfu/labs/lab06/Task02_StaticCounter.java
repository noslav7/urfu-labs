package ru.urfu.labs.lab06;

public class Task02_StaticCounter {

    private static int counter;

    public static void printAndIncrement() {
        System.out.println("Текущее значение: " + counter);
        counter++;
    }

    public static void main(String[] args) {
        printAndIncrement();
        printAndIncrement();
        printAndIncrement();
    }
}



