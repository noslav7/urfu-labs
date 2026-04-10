package ru.urfu.labs.lab11;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Task02_IntersectionOfArrays {

    public static int[] findIntersection(int[] first, int[] second) {
        if (first == null || second == null) {
            return new int[0];
        }

        Set<Integer> secondValues = Arrays.stream(second)
            .boxed()
            .collect(Collectors.toSet());

        return Arrays.stream(first)
            .filter(secondValues::contains)
            .distinct()
            .toArray();
    }

    public static void main(String[] args) {
        int[] first = {1, 2, 2, 3, 5, 8};
        int[] second = {2, 4, 6, 8, 10};
        int[] result = findIntersection(first, second);

        System.out.println("Первый массив: " + Arrays.toString(first));
        System.out.println("Второй массив: " + Arrays.toString(second));
        System.out.println("Общие элементы: " + Arrays.toString(result));
    }
}
