package ru.urfu.labs.lab09.task07;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task07_JosephusArrayList {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите N: ");
            int n = scanner.nextInt();
            if (n <= 0) {
                System.out.println("N должно быть больше 0.");
                return;
            }

            long start = System.nanoTime();
            int survivor = solve(n);
            long elapsed = System.nanoTime() - start;

            System.out.println("Остался человек под номером: " + survivor);
            System.out.println("Время (ArrayList), нс: " + elapsed);
        }
    }

    private static int solve(int n) {
        List<Integer> people = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            people.add(i);
        }

        int index = 0;
        while (people.size() > 1) {
            index = (index + 1) % people.size();
            people.remove(index);
        }

        return people.get(0);
    }
}
