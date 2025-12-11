package ru.urfu.labs.lab06;

import java.util.Arrays;

public class Task09_ReverseCharArray {

    public static void reverse(char[] values) {
        if (values == null) {
            return;
        }
        int left = 0;
        int right = values.length - 1;
        while (left < right) {
            char temp = values[left];
            values[left] = values[right];
            values[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        char[] letters = {'a', 'b', 'c', 'd', 'e'};
        System.out.println("До: " + Arrays.toString(letters));
        reverse(letters);
        System.out.println("После: " + Arrays.toString(letters));
    }
}


