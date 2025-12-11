package ru.urfu.labs.lab06;

public class Task05_SumOfSquares {

    public static long sumOfSquares(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n должно быть натуральным числом.");
        }
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += (long) i * i;
        }
        return sum;
    }

    public static long formulaSumOfSquares(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n должно быть натуральным числом.");
        }
        return n * (long) (n + 1) * (2L * n + 1) / 6;
    }

    public static void main(String[] args) {
        int n = 5;
        long byLoop = sumOfSquares(n);
        long byFormula = formulaSumOfSquares(n);

        System.out.println("Сумма квадратов чисел от 1 до " + n + ": " + byLoop);
        System.out.println("Проверка по формуле: " + byFormula);
    }
}



