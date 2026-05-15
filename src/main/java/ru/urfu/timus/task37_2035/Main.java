package ru.urfu.timus.task37_2035;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(input.readLine());
        long maxValueA = Long.parseLong(line.nextToken());
        long maxValueB = Long.parseLong(line.nextToken());
        long targetSum = Long.parseLong(line.nextToken());
        if (targetSum > maxValueA + maxValueB) {
            System.out.println("Impossible");
            return;
        }
        long smallestFeasibleA = Math.max(0, targetSum - maxValueB);
        long largestFeasibleA = Math.min(maxValueA, targetSum);
        if (smallestFeasibleA > largestFeasibleA) {
            System.out.println("Impossible");
            return;
        }
        long chosenA = largestFeasibleA;
        long chosenB = targetSum - chosenA;
        System.out.println(chosenA + " " + chosenB);
    }
}
