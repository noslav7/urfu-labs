package ru.urfu.labs.lab01;

public class example07_18 {
    static double a = 10.0, b = 4.0, c;

    public static double hyp() {
        return c = Math.sqrt(a * a + b * b);
    }

    public static double powByExpLog(double base, double exponent) {
        return Math.exp(exponent * Math.log(base));
    }

    public static void main(String[] args) {
        System.out.println("katet a=" + a);
        System.out.println("katet b=" + b);
        System.out.println("hypotenuse c=" + hyp());
        System.out.println("a^b via exp/log = " + powByExpLog(a, b));
    }
}
