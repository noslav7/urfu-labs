package ru.urfu.labs.lab13.task01;

/**
 * Пример 9. Генерация исключительной ситуации в методе и дополнительное использование оператора return.
 */
public class Example09_TryFinallyReturn {

    public static int m() {
        try {
            System.out.println("0");
            return 55;
        } finally {
            System.out.println("1");
        }
    }

    public static void main(String[] args) {
        System.out.println(m());
    }
}
