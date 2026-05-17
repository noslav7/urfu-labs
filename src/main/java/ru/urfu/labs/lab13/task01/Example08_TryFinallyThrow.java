package ru.urfu.labs.lab13.task01;

/**
 * Пример 8. Генерация исключения в методе.
 */
public class Example08_TryFinallyThrow {

    public static int m() {
        try {
            System.out.println("0");
            throw new RuntimeException();
        } finally {
            System.out.println("1");
        }
    }

    public static void main(String[] args) {
        System.out.println(m());
    }
}
