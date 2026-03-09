package ru.urfu.timus.task23_2204;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer tokenizer;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = nextInt(reader);
        int m = nextInt(reader);

        long[] fl = new long[n];
        long[] fr = new long[n];
        long[] sl = new long[n];
        long[] sr = new long[n];

        for (int i = 0; i < n; i++) {
            fl[i] = nextLong(reader);
            fr[i] = nextLong(reader);
            sl[i] = nextLong(reader);
            sr[i] = nextLong(reader);
        }

        long[] tripL = new long[m];
        long[] tripR = new long[m];
        for (int i = 0; i < m; i++) {
            tripL[i] = nextLong(reader);
            tripR[i] = nextLong(reader);
        }

        long[][] c = new long[n][2];
        for (int i = 0; i < n; i++) {
            c[i][0] = countIntersect(tripL, tripR, fl[i], fr[i]);
            c[i][1] = countIntersect(tripL, tripR, sl[i], sr[i]);
        }

        long[][][] d = new long[Math.max(0, n - 1)][2][2];
        for (int i = 0; i + 1 < n; i++) {
            d[i][0][0] = countIntersectBoth(tripL, tripR, fr[i], fl[i + 1]);
            d[i][0][1] = countIntersectBoth(tripL, tripR, fr[i], sl[i + 1]);
            d[i][1][0] = countIntersectBoth(tripL, tripR, sr[i], fl[i + 1]);
            d[i][1][1] = countIntersectBoth(tripL, tripR, sr[i], sl[i + 1]);
        }

        // dp[x] = минимальное число "потерянных" поездок после обработки экзаменов до i,
        // если для i-го экзамена выбран вариант x (0 - первый, 1 - второй).
        long[] dp = new long[2];
        dp[0] = c[0][0];
        dp[1] = c[0][1];

        for (int i = 1; i < n; i++) {
            long[] ndp = new long[2];
            ndp[0] = Math.min(
                    dp[0] + c[i][0] - d[i - 1][0][0],
                    dp[1] + c[i][0] - d[i - 1][1][0]
            );
            ndp[1] = Math.min(
                    dp[0] + c[i][1] - d[i - 1][0][1],
                    dp[1] + c[i][1] - d[i - 1][1][1]
            );
            dp = ndp;
        }

        long minBlocked = Math.min(dp[0], dp[1]);
        long maxTrips = m - minBlocked;
        System.out.println(maxTrips);
    }

    private static long countIntersect(long[] tripL, long[] tripR, long L, long R) {
        // Кол-во поездок с пересечением [L, R]:
        // starts <= R  минус  ends < L.
        int a = upperBound(tripL, R);
        int b = lowerBound(tripR, L);
        return a - b;
    }

    private static long countIntersectBoth(long[] tripL, long[] tripR, long rightOfFirst, long leftOfSecond) {
        // Поездка пересекает оба соседних интервала тогда и только тогда,
        // когда start <= rightOfFirst и end >= leftOfSecond.
        int a = upperBound(tripL, rightOfFirst); // индексы [0..a-1]
        int b = lowerBound(tripR, leftOfSecond); // индексы [b..m-1]
        return Math.max(0, a - b);
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
