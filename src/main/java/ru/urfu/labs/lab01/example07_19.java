package ru.urfu.labs.lab01;

public class example07_19 {
    static double a = 10.0, b = 4.0, c;

    public static double powByExpLog(double base, double exponent) {
        return Math.exp(exponent * Math.log(base));
    }

    public static double hyp() {
        return c = Math.sqrt(powByExpLog(a, 2.0) + powByExpLog(b, 2.0));
    }

    public static double hyp(double x, double y) {
        return Math.sqrt(powByExpLog(x, 2.0) + powByExpLog(y, 2.0));
    }

    public static void main(String[] args) {
        System.out.println("katet a=" + a);
        System.out.println("katet b=" + b);
        System.out.println("hypotenuse c (fields)=" + hyp());
        System.out.println("hypotenuse c (params)=" + hyp(3.0, 4.0));
    }
}
