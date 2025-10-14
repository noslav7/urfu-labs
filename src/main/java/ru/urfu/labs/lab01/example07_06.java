package ru.urfu.labs.lab01;

public class example07_06 {
    static double a = 10.0, b = 4.0, c;

    public static double hyp() {
        return c = Math.sqrt(a * a + b * b);
    }

    public static void main(String[] args) {
        System.out.println("katet a=" + a);
        System.out.println("katet b=" + b);
        System.out.println("hypotenuse c=" + hyp());
    }
}
