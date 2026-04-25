package ru.urfu.timus.task24_2216;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer tokenizer;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int floorCount = nextInt(reader);
        int elevatorCount = nextInt(reader);

        if (floorCount == 1) {
            System.out.println(-1);
            return;
        }

        int[] boundaryDelta = new int[floorCount + 2];
        for (int i = 0; i < elevatorCount; i++) {
            int fromFloor = nextInt(reader);
            int toFloor = nextInt(reader);

            // Лифт [l, r] пересекает все "границы" i между этажами i и i+1, где l <= i < r.
            if (fromFloor < toFloor) {
                boundaryDelta[fromFloor]++;
                boundaryDelta[toFloor]--;
            }
        }

        int activeElevatorsOnBoundary = 0;
        int minElevatorsAcrossBoundary = Integer.MAX_VALUE;
        for (int boundary = 1; boundary <= floorCount - 1; boundary++) {
            activeElevatorsOnBoundary += boundaryDelta[boundary];
            minElevatorsAcrossBoundary = Math.min(minElevatorsAcrossBoundary, activeElevatorsOnBoundary);
        }

        System.out.println(minElevatorsAcrossBoundary == 0 ? -1 : minElevatorsAcrossBoundary);
    }

    private static int nextInt(BufferedReader reader) throws Exception {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            String line = reader.readLine();
            if (line == null) {
                throw new EOFException("Unexpected end of input");
            }
            tokenizer = new StringTokenizer(line);
        }
        return Integer.parseInt(tokenizer.nextToken());
    }
}
