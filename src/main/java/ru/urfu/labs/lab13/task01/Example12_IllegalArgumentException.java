package ru.urfu.labs.lab13.task01;

/**
 * Пример 12. Исключение IllegalArgumentException – неверные аргументы.
 */
public class Example12_IllegalArgumentException {

    public static void m(String str, double chislo) {
        if (str == null) {
            throw new IllegalArgumentException("Строка введена неверно");
        }
        if (chislo > 0.001) {
            throw new IllegalArgumentException("Неверное число");
        }
    }

    public static void main(String[] args) {
        m(null, 0.000001);
    }
}
