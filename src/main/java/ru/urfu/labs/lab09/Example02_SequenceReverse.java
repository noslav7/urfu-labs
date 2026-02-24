package ru.urfu.labs.lab09;

public class Example02_SequenceReverse {

    private Example02_SequenceReverse() {
    }

    public static void run(int x) {
        if (x < 0 || x >= 20) {
            return;
        }
        run(2 * x + 1);
        System.out.println("x = " + x);
    }
}
