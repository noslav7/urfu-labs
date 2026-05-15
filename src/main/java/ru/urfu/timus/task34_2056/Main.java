package ru.urfu.timus.task34_2056;

import java.util.Scanner;

public class Main {

    private static final int SATISFACTORY = 3;
    private static final int EXCELLENT = 5;

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            int examCount = input.nextInt();
            boolean hasSatisfactoryMark = false;
            long marksSum = 0;
            boolean allMarksAreExcellent = true;
            for (int examIndex = 0; examIndex < examCount; examIndex++) {
                int mark = input.nextInt();
                marksSum += mark;
                if (mark == SATISFACTORY) {
                    hasSatisfactoryMark = true;
                }
                if (mark != EXCELLENT) {
                    allMarksAreExcellent = false;
                }
            }
            if (hasSatisfactoryMark) {
                System.out.println("None");
                return;
            }
            if (allMarksAreExcellent) {
                System.out.println("Named");
                return;
            }
            boolean qualifiesForHighScholarship = 2 * marksSum >= 9L * examCount;
            System.out.println(qualifiesForHighScholarship ? "High" : "Common");
        }
    }
}
