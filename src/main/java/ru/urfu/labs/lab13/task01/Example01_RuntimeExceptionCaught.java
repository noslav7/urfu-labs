package ru.urfu.labs.lab13.task01;

/**
 * Пример 1. Сгенерировано и перехвачено RuntimeException.
 */
public class Example01_RuntimeExceptionCaught {

    public static void main(String[] args) {
        try {
            System.out.println("0");
            throw new RuntimeException("Непроверяемая ошибка");
        } catch (RuntimeException e) {
            System.out.println("1  " + e);
        }
        System.out.println("2");
    }
}
