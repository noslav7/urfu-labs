package ru.urfu.labs.lab13.task01;

/**
 * Пример 14. Обработка исключения, порожденного одним методом m() в другом (в методе main).
 */
public class Example14_ThrowsClause {

    public static void m(int x) throws ArithmeticException {
        int h = 10 / x;
    }

    public static void main(String[] args) {
        try {
            int l = args.length;
            System.out.println("размер массива= " + l);
            m(l);
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: Деление на ноль");
        }
    }
}
