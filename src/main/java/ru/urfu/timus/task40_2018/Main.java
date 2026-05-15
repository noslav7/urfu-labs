package ru.urfu.timus.task40_2018;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int MODULO = 1_000_000_007;
    private static final int TRACK_MY_LOVE = 0;
    private static final int TRACK_MISS_YOU = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(input.readLine());
        int albumLength = Integer.parseInt(line.nextToken());
        int maxConsecutiveMyLove = Integer.parseInt(line.nextToken());
        int maxConsecutiveMissYou = Integer.parseInt(line.nextToken());

        int maxRunLength = Math.max(maxConsecutiveMyLove, maxConsecutiveMissYou);
        long[][] waysEndingWithRunLength = new long[2][maxRunLength + 1];
        waysEndingWithRunLength[TRACK_MY_LOVE][1] = 1;
        waysEndingWithRunLength[TRACK_MISS_YOU][1] = 1;

        for (int currentLength = 2; currentLength <= albumLength; currentLength++) {
            long[][] nextWaysEndingWithRunLength = new long[2][maxRunLength + 1];
            accumulateTransitionsFromMyLoveTrack(
                    waysEndingWithRunLength, nextWaysEndingWithRunLength, maxConsecutiveMyLove);
            accumulateTransitionsFromMissYouTrack(
                    waysEndingWithRunLength, nextWaysEndingWithRunLength, maxConsecutiveMissYou);
            waysEndingWithRunLength = nextWaysEndingWithRunLength;
        }

        long validAlbumCount = 0;
        for (int runLength = 1; runLength <= maxConsecutiveMyLove; runLength++) {
            validAlbumCount = (validAlbumCount + waysEndingWithRunLength[TRACK_MY_LOVE][runLength]) % MODULO;
        }
        for (int runLength = 1; runLength <= maxConsecutiveMissYou; runLength++) {
            validAlbumCount = (validAlbumCount + waysEndingWithRunLength[TRACK_MISS_YOU][runLength]) % MODULO;
        }
        System.out.println(validAlbumCount);
    }

    static void accumulateTransitionsFromMyLoveTrack(
            long[][] waysEndingWithRunLength, long[][] nextWaysEndingWithRunLength, int maxConsecutiveMyLove) {
        for (int currentRunOfMyLove = 1; currentRunOfMyLove <= maxConsecutiveMyLove; currentRunOfMyLove++) {
            long partialCount = waysEndingWithRunLength[TRACK_MY_LOVE][currentRunOfMyLove];
            if (partialCount == 0) {
                continue;
            }
            if (currentRunOfMyLove + 1 <= maxConsecutiveMyLove) {
                int nextIndex = currentRunOfMyLove + 1;
                nextWaysEndingWithRunLength[TRACK_MY_LOVE][nextIndex] =
                        (nextWaysEndingWithRunLength[TRACK_MY_LOVE][nextIndex] + partialCount) % MODULO;
            }
            nextWaysEndingWithRunLength[TRACK_MISS_YOU][1] =
                    (nextWaysEndingWithRunLength[TRACK_MISS_YOU][1] + partialCount) % MODULO;
        }
    }

    static void accumulateTransitionsFromMissYouTrack(
            long[][] waysEndingWithRunLength, long[][] nextWaysEndingWithRunLength, int maxConsecutiveMissYou) {
        for (int currentRunOfMissYou = 1; currentRunOfMissYou <= maxConsecutiveMissYou; currentRunOfMissYou++) {
            long partialCount = waysEndingWithRunLength[TRACK_MISS_YOU][currentRunOfMissYou];
            if (partialCount == 0) {
                continue;
            }
            if (currentRunOfMissYou + 1 <= maxConsecutiveMissYou) {
                int nextIndex = currentRunOfMissYou + 1;
                nextWaysEndingWithRunLength[TRACK_MISS_YOU][nextIndex] =
                        (nextWaysEndingWithRunLength[TRACK_MISS_YOU][nextIndex] + partialCount) % MODULO;
            }
            nextWaysEndingWithRunLength[TRACK_MY_LOVE][1] =
                    (nextWaysEndingWithRunLength[TRACK_MY_LOVE][1] + partialCount) % MODULO;
        }
    }
}
