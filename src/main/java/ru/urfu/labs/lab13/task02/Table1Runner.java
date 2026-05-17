package ru.urfu.labs.lab13.task02;

/**
 * Задание 2 лабораторной работы 13. Запуск всех программ из таблицы 1.
 */
public class Table1Runner {

    public static void main(String[] args) {
        run("Таблица 1, задание 1: среднее положительных элементов", () -> Task01_PositiveAverage.main(new String[0]));
        run("Таблица 1, задание 2: столбец матрицы", () -> Task02_MatrixColumn.main(new String[0]));
        run("Таблица 1, задание 3: сумма массива byte", () -> Task03_ByteArraySum.main(new String[0]));
    }

    private static void run(String title, Runnable action) {
        System.out.println("\n==================================================");
        System.out.println(title);
        System.out.println("==================================================");
        action.run();
    }
}
