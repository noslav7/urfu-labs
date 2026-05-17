package ru.urfu.labs.lab13.task01;

/**
 * Пример 5. Исключение не перехвачено.
 */
public class Example05_UncaughtException {

    public static void main(String[] args) {
        try {
            System.out.println("0");
            throw new RuntimeException("ошибка");
        } catch (NullPointerException e) {
            System.out.println("1");
        }
        System.out.println("2");
    }
}
