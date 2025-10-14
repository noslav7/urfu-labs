package ru.urfu.labs.lab01;

public class example07_20 {
    public static double powBuiltin(double a, double b) {
        return Math.pow(a, b);
    }

    public static void main(String[] args) {
        double a = 10.0;
        double b = 4.0;
        System.out.println("a^b via Math.pow = " + powBuiltin(a, b));
    }
}
