package ru.urfu.labs.lab09;

public class Example01_SequenceForward {

    private Example01_SequenceForward() {
    }

    public static void run(int x) {
        if (x < 0 || x >= 20) {
            return;
        }
        System.out.println("x = " + x);
        run(2 * x + 1);
    }
}
