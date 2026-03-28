package ru.urfu.timus.task24_2216;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer tokenizer;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = nextInt(reader);
        int k = nextInt(reader);

        if (n == 1) {
            System.out.println(-1);
            return;
        }

        int[] diff = new int[n + 2];
        for (int i = 0; i < k; i++) {
            int l = nextInt(reader);
            int r = nextInt(reader);

            // Лифт [l, r] пересекает все "границы" i между этажами i и i+1, где l <= i < r.
            if (l < r) {
                diff[l]++;
                diff[r]--;
            }
        }

        int current = 0;
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= n - 1; i++) {
            current += diff[i];
            answer = Math.min(answer, current);
        }

        System.out.println(answer == 0 ? -1 : answer);
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
