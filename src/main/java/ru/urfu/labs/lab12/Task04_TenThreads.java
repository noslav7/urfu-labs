package ru.urfu.labs.lab12;

import java.util.ArrayList;
import java.util.List;

public class Task04_TenThreads {

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            int threadNumber = i;
            Thread thread = new Thread(() -> System.out.println("Thread #" + threadNumber), "Thread-" + threadNumber);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
