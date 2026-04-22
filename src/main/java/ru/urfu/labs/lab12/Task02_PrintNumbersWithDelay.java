package ru.urfu.labs.lab12;

public class Task02_PrintNumbersWithDelay {

    public static void main(String[] args) {
        Thread numbersThread = new Thread(() -> {
            for (int number = 1; number <= 10; number++) {
                System.out.println(number);
                try {
                    Thread.sleep(1_000L);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }, "NumbersThread");

        numbersThread.start();
        try {
            numbersThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
