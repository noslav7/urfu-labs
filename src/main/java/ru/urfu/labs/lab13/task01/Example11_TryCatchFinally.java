package ru.urfu.labs.lab13.task01;

/**
 * Пример 11.
 */
public class Example11_TryCatchFinally {

    public static void main(String[] args) {
        try {
            System.out.println("0");
            throw new NullPointerException("ошибка");
        } catch (NullPointerException e) {
            System.out.println("1");
        } finally {
            System.out.println("2");
        }
        System.out.println("3");
    }
}
