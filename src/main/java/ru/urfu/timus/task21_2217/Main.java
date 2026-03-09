package ru.urfu.timus.task21_2217;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine().trim());
        String s = reader.readLine().trim();

        System.out.println(canMakePalindrome(n, s) ? "YES" : "NO");
    }

    private static boolean canMakePalindrome(int n, String s) {
        List<Integer> onesInSource = collectOnePositions(s);
        int k = onesInSource.size();

        // В строке четной длины палиндром не может содержать нечетное число единиц.
        if (n % 2 == 0 && k % 2 == 1) {
            return false;
        }

        int half = n / 2;
        int center = half + 1; // 1-based позиция центра для нечетной длины
        int pairs = k / 2;
        boolean hasCenterOne = (n % 2 == 1 && k % 2 == 1);

        // Если в центре должна быть единица, k/2+1-я единица обязана оказаться не правее центра.
        if (hasCenterOne && onesInSource.get(pairs) > center) {
            return false;
        }

        // Выбираем пары (a_j, n+1-a_j), где 1 <= a_j <= half, j = 1..pairs.
        // Для каждого j:
        //   a_j >= p_j
        //   a_j <= n + 1 - p_{k-j+1}
        // и последовательность a_j должна быть строго возрастающей.
        int prev = 0;
        for (int j = 1; j <= pairs; j++) {
            int leftBound = onesInSource.get(j - 1);
            int rightBound = n + 1 - onesInSource.get(k - j);

            leftBound = Math.max(leftBound, 1);
            rightBound = Math.min(rightBound, half);

            if (leftBound > rightBound) {
                return false;
            }

            int current = Math.max(leftBound, prev + 1);
            if (current > rightBound) {
                return false;
            }
            prev = current;
        }

        return true;
    }

    private static List<Integer> collectOnePositions(String s) {
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                positions.add(i + 1); // 1-based
            }
        }
        return positions;
    }

}
