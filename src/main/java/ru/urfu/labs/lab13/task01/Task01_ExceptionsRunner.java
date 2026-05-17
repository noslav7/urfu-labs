package ru.urfu.labs.lab13.task01;

/**
 * Задание 1. Последовательный запуск примеров 1–14 из лабораторной работы по исключениям.
 */
public class Task01_ExceptionsRunner {

    public static void main(String[] args) {
        run("Пример 1", () -> Example01_RuntimeExceptionCaught.main(new String[0]));
        run("Пример 2", () -> Example02_ExceptionSuperclassCatch.main(new String[0]));
        run("Пример 3", () -> Example03_MultipleCatchMatching.main(new String[0]));
        run("Пример 4", () -> Example04_MultipleCatchHierarchy.main(new String[0]));
        run("Пример 5", () -> Example05_UncaughtException.main(new String[0]));
        run("Пример 6", () -> Example06_InvalidCatchOrder.main(new String[0]));
        run("Пример 7", () -> Example07_CatchScope.main(new String[0]));
        run("Пример 8", () -> Example08_TryFinallyThrow.main(new String[0]));
        run("Пример 9", () -> Example09_TryFinallyReturn.main(new String[0]));
        run("Пример 10", () -> Example10_TryFinallyReturnOverride.main(new String[0]));
        run("Пример 11", () -> Example11_TryCatchFinally.main(new String[0]));
        run("Пример 12", () -> Example12_IllegalArgumentException.main(new String[0]));
        run("Пример 13", () -> Example13_MainArgs.main(new String[0]));
        run("Пример 14", () -> Example14_ThrowsClause.main(new String[0]));
    }

    private static void run(String title, Runnable action) {
        System.out.println("\n==================================================");
        System.out.println(title);
        System.out.println("==================================================");
        try {
            action.run();
        } catch (Throwable t) {
            System.out.println("Исключение (ожидаемо для части примеров): " + t);
        }
    }
}
