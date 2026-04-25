package ru.urfu.timus.task21_2217;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int stringLength = Integer.parseInt(reader.readLine().trim());
        String binaryString = reader.readLine().trim();

        System.out.println(canMakePalindrome(stringLength, binaryString) ? "YES" : "NO");
    }

    private static boolean canMakePalindrome(int stringLength, String binaryString) {
        List<Integer> onePositions = collectOnePositions(binaryString);
        int oneCount = onePositions.size();

        // В строке четной длины палиндром не может содержать нечетное число единиц.
        if (stringLength % 2 == 0 && oneCount % 2 == 1) {
            return false;
        }

        int leftHalfLength = stringLength / 2;
        int centerPosition = leftHalfLength + 1; // 1-based позиция центра для нечетной длины
        int pairCount = oneCount / 2;
        boolean hasCenterOne = (stringLength % 2 == 1 && oneCount % 2 == 1);

        // Если в центре должна быть единица, k/2+1-я единица обязана оказаться не правее центра.
        if (hasCenterOne && onePositions.get(pairCount) > centerPosition) {
            return false;
        }

        // Выбираем пары (a_j, n+1-a_j), где 1 <= a_j <= half, j = 1..pairs.
        // Для каждого j:
        //   a_j >= p_j
        //   a_j <= n + 1 - p_{k-j+1}
        // и последовательность a_j должна быть строго возрастающей.
        int previousChosenPosition = 0;
        for (int pairIndex = 1; pairIndex <= pairCount; pairIndex++) {
            int leftBound = onePositions.get(pairIndex - 1);
            int rightBound = stringLength + 1 - onePositions.get(oneCount - pairIndex);

            leftBound = Math.max(leftBound, 1);
            rightBound = Math.min(rightBound, leftHalfLength);

            if (leftBound > rightBound) {
                return false;
            }

            int chosenPosition = Math.max(leftBound, previousChosenPosition + 1);
            if (chosenPosition > rightBound) {
                return false;
            }
            previousChosenPosition = chosenPosition;
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
