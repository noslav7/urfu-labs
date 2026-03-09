package ru.urfu.timus.task22_2225;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[] parent;
    private static int[] size;
    private static boolean[] active;
    private static long levelCost;
    private static StringTokenizer tokenizer;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = nextInt(reader);
        int[] l = new int[n];
        for (int i = 0; i < n; i++) {
            l[i] = nextInt(reader);
        }

        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }
        Arrays.sort(order, (a, b) -> Integer.compare(l[b], l[a]));

        parent = new int[n];
        size = new int[n];
        active = new boolean[n];
        levelCost = 0L;

        long answer = 0L;
        int ptr = 0;
        int currentHeight = l[order[0]];

        while (currentHeight > 0) {
            while (ptr < n && l[order[ptr]] == currentHeight) {
                activate(order[ptr], n);
                ptr++;
            }

            int nextHeight = (ptr < n) ? l[order[ptr]] : 0;
            answer += (long) (currentHeight - nextHeight) * levelCost;
            currentHeight = nextHeight;
        }

        System.out.println(answer);
    }

    private static void activate(int index, int n) {
        active[index] = true;
        parent[index] = index;
        size[index] = 1;
        levelCost += contribution(1);

        if (index > 0 && active[index - 1]) {
            unite(index, index - 1);
        }
        if (index + 1 < n && active[index + 1]) {
            unite(index, index + 1);
        }
    }

    private static void unite(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if (ra == rb) {
            return;
        }

        levelCost -= contribution(size[ra]);
        levelCost -= contribution(size[rb]);

        if (size[ra] < size[rb]) {
            int tmp = ra;
            ra = rb;
            rb = tmp;
        }

        parent[rb] = ra;
        size[ra] += size[rb];

        levelCost += contribution(size[ra]);
    }

    private static int find(int v) {
        if (parent[v] == v) {
            return v;
        }
        parent[v] = find(parent[v]);
        return parent[v];
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
