package ru.urfu.labs.lab09.task06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task06_HashMapProcessing {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семьсот");
        map.put(8, "восемь");
        map.put(9, "девять");

        List<String> keyGreaterThanFive = new ArrayList<>();
        List<String> keyEqualsZero = new ArrayList<>();
        long product = 1L;
        boolean hasFactor = false;

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();

            if (key > 5) {
                keyGreaterThanFive.add(value);
            }

            if (key == 0) {
                keyEqualsZero.add(value);
            }

            if (value.length() > 5) {
                product *= key;
                hasFactor = true;
            }
        }

        System.out.println("Строки, у которых ключ > 5: " + String.join(", ", keyGreaterThanFive));
        System.out.println("Строки, у которых ключ = 0: " + String.join(", ", keyEqualsZero));
        System.out.println("Произведение ключей (длина строки > 5): " + (hasFactor ? product : 0));
    }
}
