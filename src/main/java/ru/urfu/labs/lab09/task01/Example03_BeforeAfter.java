package ru.urfu.labs.lab09.task01;

public class Example03_BeforeAfter {

    private Example03_BeforeAfter() {
    }

    public static void run(int x) {
        if (x < 0 || x >= 20) {
            return;
        }
        System.out.println("До рекурсивного вызова: x = " + x);
        run(2 * x + 1);
        System.out.println("После рекурсивного вызова: x = " + x);
    }
}
