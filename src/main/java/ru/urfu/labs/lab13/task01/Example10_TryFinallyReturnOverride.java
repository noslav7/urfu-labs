package ru.urfu.labs.lab13.task01;

/**
 * Пример 10. Генерация исключительной ситуации в методе.
 * Использование оператора return в секциях try и finally.
 */
public class Example10_TryFinallyReturnOverride {

    public static int m() {
        try {
            System.out.println("0");
            return 15;
        } finally {
            System.out.println("1");
            return 20;
        }
    }

    public static void main(String[] args) {
        System.out.println(m());
    }
}
