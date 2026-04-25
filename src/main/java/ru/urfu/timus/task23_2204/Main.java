package ru.urfu.timus.task23_2204;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer tokenizer;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int examCount = nextInt(reader);
        int tripCount = nextInt(reader);

        long[] firstIntervalLeft = new long[examCount];
        long[] firstIntervalRight = new long[examCount];
        long[] secondIntervalLeft = new long[examCount];
        long[] secondIntervalRight = new long[examCount];

        for (int i = 0; i < examCount; i++) {
            firstIntervalLeft[i] = nextLong(reader);
            firstIntervalRight[i] = nextLong(reader);
            secondIntervalLeft[i] = nextLong(reader);
            secondIntervalRight[i] = nextLong(reader);
        }

        long[] tripStart = new long[tripCount];
        long[] tripEnd = new long[tripCount];
        for (int i = 0; i < tripCount; i++) {
            tripStart[i] = nextLong(reader);
            tripEnd[i] = nextLong(reader);
        }

        long[][] blockedTripsByOption = new long[examCount][2];
        for (int i = 0; i < examCount; i++) {
            blockedTripsByOption[i][0] =
                    countIntersect(tripStart, tripEnd, firstIntervalLeft[i], firstIntervalRight[i]);
            blockedTripsByOption[i][1] =
                    countIntersect(tripStart, tripEnd, secondIntervalLeft[i], secondIntervalRight[i]);
        }

        long[][][] overlapBlockedTrips = new long[Math.max(0, examCount - 1)][2][2];
        for (int i = 0; i + 1 < examCount; i++) {
            overlapBlockedTrips[i][0][0] =
                    countIntersectBoth(tripStart, tripEnd, firstIntervalRight[i], firstIntervalLeft[i + 1]);
            overlapBlockedTrips[i][0][1] =
                    countIntersectBoth(tripStart, tripEnd, firstIntervalRight[i], secondIntervalLeft[i + 1]);
            overlapBlockedTrips[i][1][0] =
                    countIntersectBoth(tripStart, tripEnd, secondIntervalRight[i], firstIntervalLeft[i + 1]);
            overlapBlockedTrips[i][1][1] =
                    countIntersectBoth(tripStart, tripEnd, secondIntervalRight[i], secondIntervalLeft[i + 1]);
        }

        // dp[x] = минимальное число "потерянных" поездок после обработки экзаменов до i,
        // если для i-го экзамена выбран вариант x (0 - первый, 1 - второй).
        long[] minBlockedUpToExam = new long[2];
        minBlockedUpToExam[0] = blockedTripsByOption[0][0];
        minBlockedUpToExam[1] = blockedTripsByOption[0][1];

        for (int i = 1; i < examCount; i++) {
            long[] nextMinBlocked = new long[2];
            nextMinBlocked[0] = Math.min(
                    minBlockedUpToExam[0] + blockedTripsByOption[i][0] - overlapBlockedTrips[i - 1][0][0],
                    minBlockedUpToExam[1] + blockedTripsByOption[i][0] - overlapBlockedTrips[i - 1][1][0]
            );
            nextMinBlocked[1] = Math.min(
                    minBlockedUpToExam[0] + blockedTripsByOption[i][1] - overlapBlockedTrips[i - 1][0][1],
                    minBlockedUpToExam[1] + blockedTripsByOption[i][1] - overlapBlockedTrips[i - 1][1][1]
            );
            minBlockedUpToExam = nextMinBlocked;
        }

        long minBlockedTrips = Math.min(minBlockedUpToExam[0], minBlockedUpToExam[1]);
        long maxAvailableTrips = tripCount - minBlockedTrips;
        System.out.println(maxAvailableTrips);
    }

    private static long countIntersect(long[] tripStart, long[] tripEnd, long left, long right) {
        // Кол-во поездок с пересечением [L, R]:
        // starts <= R  минус  ends < L.
        int startsNotAfterRight = upperBound(tripStart, right);
        int endsBeforeLeft = lowerBound(tripEnd, left);
        return startsNotAfterRight - endsBeforeLeft;
    }

    private static long countIntersectBoth(long[] tripStart, long[] tripEnd,
                                           long firstIntervalRight, long secondIntervalLeft) {
        // Поездка пересекает оба соседних интервала тогда и только тогда,
        // когда start <= rightOfFirst и end >= leftOfSecond.
        int startsNotAfterFirstRight = upperBound(tripStart, firstIntervalRight); // [0..x-1]
        int endsBeforeSecondLeft = lowerBound(tripEnd, secondIntervalLeft); // [0..x-1]
        return Math.max(0, startsNotAfterFirstRight - endsBeforeSecondLeft);
    }

    private static int lowerBound(long[] arr, long x) {
        int l = 0;
        int r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] < x) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    private static int upperBound(long[] arr, long x) {
        int l = 0;
        int r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] <= x) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    private static int nextInt(BufferedReader reader) throws Exception {
        return (int) nextLong(reader);
    }

    private static long nextLong(BufferedReader reader) throws Exception {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return Long.parseLong(tokenizer.nextToken());
    }
}
