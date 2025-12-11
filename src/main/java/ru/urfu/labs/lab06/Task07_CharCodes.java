package ru.urfu.labs.lab06;

import java.util.Arrays;

public class Task07_CharCodes {

    public static int[] toCodes(char[] characters) {
        if (characters == null) {
            return new int[0];
        }
        int[] codes = new int[characters.length];
        for (int i = 0; i < characters.length; i++) {
            codes[i] = characters[i];
        }
        return codes;
    }

    public static void main(String[] args) {
        char[] symbols = {'J', 'a', 'v', 'a'};
        System.out.println("Символы: " + Arrays.toString(symbols));
        System.out.println("Коды: " + Arrays.toString(toCodes(symbols)));
    }
}



