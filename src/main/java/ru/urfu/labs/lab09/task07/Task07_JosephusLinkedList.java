package ru.urfu.labs.lab09.task07;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Task07_JosephusLinkedList {
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
            System.out.println("Время (LinkedList), нс: " + elapsed);
        }
    }

    private static int solve(int n) {
        LinkedList<Integer> people = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            people.add(i);
        }

        ListIterator<Integer> iterator = people.listIterator();
        while (people.size() > 1) {
            if (!iterator.hasNext()) {
                iterator = people.listIterator();
            }
            iterator.next(); // пропускаем первого

            if (!iterator.hasNext()) {
                iterator = people.listIterator();
            }
            iterator.next(); // второй вычеркивается
            iterator.remove();
        }

        return people.getFirst();
    }
}
