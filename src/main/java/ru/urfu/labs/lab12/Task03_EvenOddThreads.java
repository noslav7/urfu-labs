package ru.urfu.labs.lab12;

public class Task03_EvenOddThreads {

    public static void main(String[] args) {
        Thread evenThread = new Thread(() -> printNumbersByParity(0), "EvenThread");
        Thread oddThread = new Thread(() -> printNumbersByParity(1), "OddThread");

        evenThread.start();
        oddThread.start();

        try {
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void printNumbersByParity(int parity) {
        for (int number = 1; number <= 10; number++) {
            if (number % 2 == parity) {
                System.out.println(Thread.currentThread().getName() + ": " + number);
            }
        }
    }
}
