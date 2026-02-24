package ru.urfu.labs.lab09;

/**
 * Лабораторная работа №09, задание 1.
 * Демонстрация рекурсивных алгоритмов (примеры 1-5).
 */
public class Task01_RecursiveAlgorithms {

    public static void main(String[] args) {
        int startX = 1;
        int factorialN = 5;
        int fibonacciN = 7;

        System.out.println("=== Пример 1: последовательность x = 2*x + 1, 0 <= x < 20 ===");
        Example01_SequenceForward.run(startX);

        System.out.println("\n=== Пример 2: вывод той же последовательности в обратном порядке ===");
        Example02_SequenceReverse.run(startX);

        System.out.println("\n=== Пример 3: вывод до и после рекурсивного вызова ===");
        Example03_BeforeAfter.run(startX);

        System.out.println("\n=== Пример 4: факториал ===");
        long factorialValue = Example04_Factorial.factorial(factorialN);
        System.out.println(factorialN + "! = " + factorialValue);

        System.out.println("\n=== Пример 5: число Фибоначчи + обход дерева вызовов ===");
        String preOrderTrace = Example05_FibonacciTrace.runWithTrace(fibonacciN);
        int fibonacciValue = Example05_FibonacciTrace.fibonacci(fibonacciN);
        System.out.println("\nПорядок обхода дерева рекурсивных вызовов (pre-order):");
        System.out.println(preOrderTrace);
        System.out.println("F(" + fibonacciN + ") = " + fibonacciValue);
    }
}
