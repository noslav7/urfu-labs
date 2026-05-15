package ru.urfu.timus.task38_2031;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        String[] best = null;
        long bestSum = Long.MAX_VALUE;

        for (int start = 1; start + n - 1 <= 99; start++) {
            String[] candidate = buildSequence(n, start);
            if (candidate == null) {
                continue;
            }
            long sum = sequenceSum(candidate);
            if (isBetterCandidate(candidate, sum, best, bestSum)) {
                best = candidate;
                bestSum = sum;
            }
        }
        System.out.println(best == null ? "Glupenky Pierre" : String.join(" ", best));
    }

    /** После переворота последовательности получаются подряд идущие числа [start .. start+n-1]. */
    static String[] buildSequence(int n, int start) {
        String[] sequence = new String[n];
        for (int i = 0; i < n; i++) {
            int upsideDownValue = start + (n - 1 - i);
            String code = displayCodeForUpsideDownValue(upsideDownValue);
            if (code == null) {
                return null;
            }
            sequence[i] = code;
        }
        return sequence;
    }

    static String displayCodeForUpsideDownValue(int target) {
        for (int value = 1; value <= 99; value++) {
            String code = toDisplayCode(value);
            if (upsideDownValue(code) == target) {
                return code;
            }
        }
        return null;
    }

    static String toDisplayCode(int value) {
        return value <= 9 ? "0" + value : String.valueOf(value);
    }

    static int upsideDownValue(String code) {
        char first = flipDigit(code.charAt(1));
        char second = flipDigit(code.charAt(0));
        if (first == 0 || second == 0) {
            return -1;
        }
        return (first - '0') * 10 + (second - '0');
    }

    static char flipDigit(char digit) {
        return switch (digit) {
            case '0', '1', '8' -> digit;
            case '6' -> '9';
            case '9' -> '6';
            default -> 0;
        };
    }

    static long sequenceSum(String[] sequence) {
        long sum = 0;
        for (String code : sequence) {
            sum += Integer.parseInt(code);
        }
        return sum;
    }

    static boolean isBetterCandidate(String[] candidate, long candidateSum, String[] best, long bestSum) {
        if (best == null) {
            return true;
        }
        if (candidateSum != bestSum) {
            return candidateSum < bestSum;
        }
        for (int i = 0; i < candidate.length; i++) {
            int cmp = candidate[i].compareTo(best[i]);
            if (cmp != 0) {
                return cmp < 0;
            }
        }
        return false;
    }
}
