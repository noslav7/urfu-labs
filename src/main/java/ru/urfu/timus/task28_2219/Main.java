package ru.urfu.timus.task28_2219;

import java.util.Arrays;

public class Main {
    private static final int BUF = 1 << 16;
    private static final byte[] BUFFER = new byte[BUF];
    private static int len = 0;
    private static int ptr = 0;

    public static void main(String[] args) throws Exception {
        int n = nextInt();
        int m = nextInt();
        int k = nextInt();

        int[] head = new int[n + 1];
        int[] to = new int[2 * m];
        int[] next = new int[2 * m];
        Arrays.fill(head, -1);
        int edgeCnt = 0;
        for (int i = 0; i < m; i++) {
            int u = nextInt();
            int v = nextInt();
            to[edgeCnt] = v;
            next[edgeCnt] = head[u];
            head[u] = edgeCnt++;

            to[edgeCnt] = u;
            next[edgeCnt] = head[v];
            head[v] = edgeCnt++;
        }

        long[] cups = new long[k];
        for (int i = 0; i < k; i++) {
            cups[i] = nextLong();
        }

        int[] dist = bfs(n, head, to, next);

        int[] answer = solve(dist, cups);

        StringBuilder out = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            out.append(answer[i]);
            if (i + 1 <= n) {
                out.append(' ');
            }
        }
        System.out.print(out.toString());
    }

    private static int[] bfs(int n, int[] head, int[] to, int[] next) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        int[] q = new int[n];
        int l = 0, r = 0;
        q[r++] = 1;
        dist[1] = 0;
        while (l < r) {
            int v = q[l++];
            for (int e = head[v]; e != -1; e = next[e]) {
                int u = to[e];
                if (dist[u] == -1) {
                    dist[u] = dist[v] + 1;
                    q[r++] = u;
                }
            }
        }
        return dist;
    }

    private static int[] solve(int[] dist, long[] cups) {
        int n = dist.length - 1;
        int k = cups.length;

        Arrays.sort(cups);
        for (int i = 0, j = k - 1; i < j; i++, j--) {
            long t = cups[i];
            cups[i] = cups[j];
            cups[j] = t;
        }

        long[] pref = new long[k + 1];
        long[] minEven = new long[k + 1];
        long[] minOdd = new long[k + 1];
        final long INF = Long.MAX_VALUE / 4;
        minEven[0] = minOdd[0] = INF;
        for (int i = 1; i <= k; i++) {
            pref[i] = pref[i - 1] + cups[i - 1];
            if ((cups[i - 1] & 1L) == 0L) {
                minEven[i] = Math.min(minEven[i - 1], cups[i - 1]);
                minOdd[i] = minOdd[i - 1];
            } else {
                minOdd[i] = Math.min(minOdd[i - 1], cups[i - 1]);
                minEven[i] = minEven[i - 1];
            }
        }

        long[] maxEvenSuf = new long[k + 1];
        long[] maxOddSuf = new long[k + 1];
        maxEvenSuf[k] = maxOddSuf[k] = -1;
        for (int i = k - 1; i >= 0; i--) {
            long val = cups[i];
            if ((val & 1L) == 0L) {
                maxEvenSuf[i] = Math.max(maxEvenSuf[i + 1], val);
                maxOddSuf[i] = maxOddSuf[i + 1];
            } else {
                maxOddSuf[i] = Math.max(maxOddSuf[i + 1], val);
                maxEvenSuf[i] = maxEvenSuf[i + 1];
            }
        }

        long[] bestEven = new long[k + 1];
        long[] bestOdd = new long[k + 1];
        Arrays.fill(bestEven, -1);
        Arrays.fill(bestOdd, -1);
        for (int x = 0; x <= k; x++) {
            long sum = pref[x];
            bestEven[x] = bestWithParity(sum, 0, x, minEven, minOdd, maxEvenSuf, maxOddSuf, INF);
            bestOdd[x] = bestWithParity(sum, 1, x, minEven, minOdd, maxEvenSuf, maxOddSuf, INF);
        }

        for (int i = 1; i <= k; i++) {
            if (bestEven[i] < bestEven[i - 1]) {
                bestEven[i] = bestEven[i - 1];
            }
            if (bestOdd[i] < bestOdd[i - 1]) {
                bestOdd[i] = bestOdd[i - 1];
            }
        }

        int[] ans = new int[n + 1];
        for (int v = 1; v <= n; v++) {
            int d = dist[v];
            if (d == -1) {
                ans[v] = -1;
                continue;
            }
            int parity = d & 1;
            long[] arr = parity == 0 ? bestEven : bestOdd;
            int lo = 0, hi = k, res = -1;
            while (lo <= hi) {
                int mid = (lo + hi) >>> 1;
                if (arr[mid] >= d) {
                    res = mid;
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
            ans[v] = res;
        }
        return ans;
    }

    private static long bestWithParity(long sum, int needParity, int x,
                                       long[] minEven, long[] minOdd,
                                       long[] maxEvenSuf, long[] maxOddSuf,
                                       long INF) {
        if (((sum ^ needParity) & 1L) == 0L) {
            return sum;
        }
        long best = -1;
        if (needParity == 0) { // хотим чётную сумму
            if (minOdd[x] != INF && maxEvenSuf[x] != -1) {
                best = Math.max(best, sum - minOdd[x] + maxEvenSuf[x]);
            }
            if (minEven[x] != INF && maxOddSuf[x] != -1) {
                best = Math.max(best, sum - minEven[x] + maxOddSuf[x]);
            }
        } else { // хотим нечётную сумму
            if (minEven[x] != INF && maxOddSuf[x] != -1) {
                best = Math.max(best, sum - minEven[x] + maxOddSuf[x]);
            }
            if (minOdd[x] != INF && maxEvenSuf[x] != -1) {
                best = Math.max(best, sum - minOdd[x] + maxEvenSuf[x]);
            }
        }
        return best;
    }

    private static int nextInt() throws Exception {
        int c;
        do {
            c = read();
        } while (c <= ' ' && c != -1);
        int v = 0;
        while (c > ' ') {
            v = v * 10 + (c - '0');
            c = read();
        }
        return v;
    }

    private static long nextLong() throws Exception {
        int c;
        do {
            c = read();
        } while (c <= ' ' && c != -1);
        long v = 0;
        while (c > ' ') {
            v = v * 10 + (c - '0');
            c = read();
        }
        return v;
    }

    private static int read() throws Exception {
        if (ptr >= len) {
            len = System.in.read(BUFFER);
            ptr = 0;
            if (len <= 0) {
                return -1;
            }
        }
        return BUFFER[ptr++];
    }
}
