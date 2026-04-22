package ru.urfu.labs.lab12;

/**
 * Runs all tasks of lab 12 sequentially.
 */
public class Lab12Runner {

    public static void main(String[] args) {
        run("Task 1: two threads with name and time", () -> Task01_TwoThreadsWithTime.main(new String[0]));
        run("Task 2: numbers from 1 to 10 with delay", () -> Task02_PrintNumbersWithDelay.main(new String[0]));
        run("Task 3: even and odd numbers in two threads", () -> Task03_EvenOddThreads.main(new String[0]));
        run("Task 4: launch 10 threads", () -> Task04_TenThreads.main(new String[0]));
        run("Task 5: multithreaded max in array", () -> Task05_FindMaxMultithreaded.main(new String[0]));
        run("Task 6: multithreaded sum of array", () -> Task06_SumMultithreaded.main(new String[0]));
    }

    private static void run(String title, Runnable action) {
        System.out.println("\n==================================================");
        System.out.println(title);
        System.out.println("==================================================");
        action.run();
    }
}
