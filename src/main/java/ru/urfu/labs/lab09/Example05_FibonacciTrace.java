package ru.urfu.labs.lab09;

public class Example05_FibonacciTrace {

    private Example05_FibonacciTrace() {
    }

    public static int fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Номер числа Фибоначчи должен быть >= 0");
        }
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static String runWithTrace(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Номер числа Фибоначчи должен быть >= 0");
        }
        StringBuilder trace = new StringBuilder();
        fibonacciWithTrace(n, 0, trace);
        return trace.toString();
    }

    private static int fibonacciWithTrace(int n, int depth, StringBuilder trace) {
        String indent = "  ".repeat(depth);
        trace.append(indent).append("visit F(").append(n).append(")\n");

        if (n == 0) {
            System.out.println(indent + "F(0) = 0 (база)");
            return 0;
        }
        if (n == 1) {
            System.out.println(indent + "F(1) = 1 (база)");
            return 1;
        }

        System.out.println(indent + "Вычисляем F(" + n + ") = F(" + (n - 1) + ") + F(" + (n - 2) + ")");
        int left = fibonacciWithTrace(n - 1, depth + 1, trace);
        int right = fibonacciWithTrace(n - 2, depth + 1, trace);
        int result = left + right;
        System.out.println(indent + "F(" + n + ") = " + result);
        return result;
    }
}
