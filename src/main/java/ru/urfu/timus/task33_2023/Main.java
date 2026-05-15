package ru.urfu.timus.task33_2023;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int lettersToDeliver = Integer.parseInt(input.readLine());
        int currentCabinetIndex = 0;
        long totalSteps = 0;
        for (int letterIndex = 0; letterIndex < lettersToDeliver; letterIndex++) {
            String addressee = input.readLine();
            int targetCabinetIndex = cabinetIndexFromFirstLetter(addressee.charAt(0));
            totalSteps += Math.abs(currentCabinetIndex - targetCabinetIndex);
            currentCabinetIndex = targetCabinetIndex;
        }
        System.out.println(totalSteps);
    }

    static int cabinetIndexFromFirstLetter(char firstLetterOfName) {
        if (firstLetterOfName == 'A' || firstLetterOfName == 'P' || firstLetterOfName == 'O'
                || firstLetterOfName == 'R') {
            return 0;
        }
        if (firstLetterOfName == 'B' || firstLetterOfName == 'M' || firstLetterOfName == 'Q'
                || firstLetterOfName == 'S') {
            return 1;
        }
        return 2;
    }
}
