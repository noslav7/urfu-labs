package ru.urfu.timus.task26_2226;

import java.io.BufferedInputStream;
import java.io.IOException;

public class Main {
    private static int[] from;
    private static int[] to;
    private static int[] b1w;
    private static int[] b2w;
    private static int[] b1to;
    private static int[] b1s;
    private static int[] b2s;
    private static int[] end;
    private static boolean[] onStack;
    private static int[] stack;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        int n = fs.nextInt();
        int m = n - 1;

        from = new int[2 * m];
        to = new int[2 * m];
        b1w = new int[n + 1];
        b2w = new int[n + 1];
        b1to = new int[n + 1];
        b1s = new int[n + 1];
        b2s = new int[n + 1];
        final int INF = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            b1w[i] = INF;
            b2w[i] = INF;
            b1s[i] = -1;
            b2s[i] = -1;
        }

        int ptr = 0;
        for (int i = 0; i < m; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();
            int l = fs.nextInt();

            from[ptr] = u;
            to[ptr] = v;
            relax(u, v, ptr, l);
            ptr++;

            from[ptr] = v;
            to[ptr] = u;
            relax(v, u, ptr, l);
            ptr++;
        }

        end = new int[2 * m];
        onStack = new boolean[2 * m];
        stack = new int[2 * m];

        StringBuilder out = new StringBuilder();
        for (int start = 1; start <= n; start++) {
            int first = b1s[start];
            int answer = (first == -1) ? start : solve(first);

            if (start > 1) {
                out.append(' ');
            }
            out.append(answer);
        }
        System.out.println(out);
    }

    private static void relax(int v, int u, int s, int w) {
        if (w < b1w[v]) {
            b2w[v] = b1w[v];
            b2s[v] = b1s[v];
            b1w[v] = w;
            b1to[v] = u;
            b1s[v] = s;
        } else if (w < b2w[v]) {
            b2w[v] = w;
            b2s[v] = s;
        }
    }

    private static int nextState(int s) {
        int v = to[s];
        if (b1s[v] == -1) {
            return -1;
        }
        return (b1to[v] != from[s]) ? b1s[v] : b2s[v];
    }

    private static int solve(int start) {
        if (end[start] != 0) {
            return end[start];
        }

        int top = 0;
        int cur = start;
        int ans = 0;

        while (true) {
            if (end[cur] != 0) {
                ans = end[cur];
                break;
            }
            if (onStack[cur]) {
                ans = to[cur];
                break;
            }

            onStack[cur] = true;
            stack[top++] = cur;

            int nxt = nextState(cur);
            if (nxt == -1) {
                ans = to[cur];
                break;
            }
            cur = nxt;
        }

        while (top > 0) {
            int s = stack[--top];
            onStack[s] = false;
            end[s] = ans;
        }
        return ans;
    }

    private static class FastScanner {
        private final BufferedInputStream in = new BufferedInputStream(System.in);
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0;
        private int len = 0;

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) {
                    return -1;
                }
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ' && c != -1);

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            int value = 0;
            while (c > ' ') {
                value = value * 10 + (c - '0');
                c = read();
            }
            return value * sign;
        }
    }
}
