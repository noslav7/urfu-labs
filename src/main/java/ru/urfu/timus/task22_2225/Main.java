package ru.urfu.timus.task22_2225;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[] dsuParent;
    private static int[] componentSize;
    private static boolean[] isActive;
    private static long levelCost;
    private static StringTokenizer tokenizer;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int columnCount = nextInt(reader);
        int[] heights = new int[columnCount];
        for (int i = 0; i < columnCount; i++) {
            heights[i] = nextInt(reader);
        }

        Integer[] order = new Integer[columnCount];
        for (int i = 0; i < columnCount; i++) {
            order[i] = i;
        }
        Arrays.sort(order, (a, b) -> Integer.compare(heights[b], heights[a]));

        dsuParent = new int[columnCount];
        componentSize = new int[columnCount];
        isActive = new boolean[columnCount];
        levelCost = 0L;

        long answer = 0L;
        int orderPointer = 0;
        int currentHeight = heights[order[0]];

        while (currentHeight > 0) {
            while (orderPointer < columnCount && heights[order[orderPointer]] == currentHeight) {
                activateIndex(order[orderPointer], columnCount);
                orderPointer++;
            }

            int nextHeight = (orderPointer < columnCount) ? heights[order[orderPointer]] : 0;
            answer += (long) (currentHeight - nextHeight) * levelCost;
            currentHeight = nextHeight;
        }

        System.out.println(answer);
    }

    private static void activateIndex(int index, int totalCount) {
        isActive[index] = true;
        dsuParent[index] = index;
        componentSize[index] = 1;
        levelCost += contribution(1);

        if (index > 0 && isActive[index - 1]) {
            unite(index, index - 1);
        }
        if (index + 1 < totalCount && isActive[index + 1]) {
            unite(index, index + 1);
        }
    }

    private static void unite(int firstIndex, int secondIndex) {
        int firstRoot = findRoot(firstIndex);
        int secondRoot = findRoot(secondIndex);
        if (firstRoot == secondRoot) {
            return;
        }

        levelCost -= contribution(componentSize[firstRoot]);
        levelCost -= contribution(componentSize[secondRoot]);

        if (componentSize[firstRoot] < componentSize[secondRoot]) {
            int temp = firstRoot;
            firstRoot = secondRoot;
            secondRoot = temp;
        }

        dsuParent[secondRoot] = firstRoot;
        componentSize[firstRoot] += componentSize[secondRoot];

        levelCost += contribution(componentSize[firstRoot]);
    }

    private static int findRoot(int vertex) {
        if (dsuParent[vertex] == vertex) {
            return vertex;
        }
        dsuParent[vertex] = findRoot(dsuParent[vertex]);
        return dsuParent[vertex];
    }

    private static long contribution(int length) {
        return (length + 1L) / 2L;
    }

    private static int nextInt(BufferedReader reader) throws Exception {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return Integer.parseInt(tokenizer.nextToken());
    }
}
