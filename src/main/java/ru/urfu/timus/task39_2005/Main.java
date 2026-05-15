package ru.urfu.timus.task39_2005;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int PRACTICE_ROOM = 1;
    private static final int MISHA_HOME = 5;
    private static final int ILYA_HOME = 3;

    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int[][] distanceBetweenPoints = new int[6][6];
        for (int from = 1; from <= 5; from++) {
            StringTokenizer line = new StringTokenizer(input.readLine());
            for (int to = 1; to <= 5; to++) {
                distanceBetweenPoints[from][to] = Integer.parseInt(line.nextToken());
            }
        }
        int[] intermediatePoints = {2, 3, 4};
        int bestRouteLength = Integer.MAX_VALUE;
        int bestSecondStop = 0;
        int bestThirdStop = 0;
        int bestFourthStop = 0;
        for (int secondStop : intermediatePoints) {
            for (int thirdStop : intermediatePoints) {
                if (thirdStop == secondStop) {
                    continue;
                }
                for (int fourthStop : intermediatePoints) {
                    if (fourthStop == secondStop || fourthStop == thirdStop) {
                        continue;
                    }
                    if (fourthStop == ILYA_HOME) {
                        continue;
                    }
                    int routeLength = distanceBetweenPoints[PRACTICE_ROOM][secondStop]
                            + distanceBetweenPoints[secondStop][thirdStop]
                            + distanceBetweenPoints[thirdStop][fourthStop]
                            + distanceBetweenPoints[fourthStop][MISHA_HOME];
                    if (routeLength < bestRouteLength) {
                        bestRouteLength = routeLength;
                        bestSecondStop = secondStop;
                        bestThirdStop = thirdStop;
                        bestFourthStop = fourthStop;
                    }
                }
            }
        }
        System.out.println(bestRouteLength);
        System.out.println(PRACTICE_ROOM + " " + bestSecondStop + " " + bestThirdStop + " " + bestFourthStop + " "
                + MISHA_HOME);
    }
}
