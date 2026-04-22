package ru.urfu.timus.task29_2218;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    private static final int BUF = 1 << 16;
    private static final byte[] BUFFER = new byte[BUF];
    private static int len = 0;
    private static int ptr = 0;

    private static class Point {
        final long u;
        final int vIdx;

        Point(long u, int vIdx) {
            this.u = u;
            this.vIdx = vIdx;
        }
    }

    private static class Event {
        final long uLimit;
        final int lIdx;
        final int rIdx;
        final int queryId;
        final int which; // 0 -> radius d, 1 -> radius d-1
        final int sign;  // +1 or -1

        Event(long uLimit, int lIdx, int rIdx, int queryId, int which, int sign) {
            this.uLimit = uLimit;
            this.lIdx = lIdx;
            this.rIdx = rIdx;
            this.queryId = queryId;
            this.which = which;
            this.sign = sign;
        }
    }

    private static class Fenwick {
        private final int size;
        private final int[] bit;

        Fenwick(int n) {
            this.size = n;
            this.bit = new int[n + 1];
        }

        void add(int idx, int delta) {
            for (int i = idx; i <= size; i += i & -i) {
                bit[i] += delta;
            }
        }

        int sumPrefix(int idx) {
            int res = 0;
            for (int i = idx; i > 0; i -= i & -i) {
                res += bit[i];
            }
            return res;
        }

        int rangeSum(int l, int r) {
            if (l > r) return 0;
            return sumPrefix(r) - sumPrefix(l - 1);
        }
    }

    public static void main(String[] args) throws Exception {
        int n = nextInt();
        long[] uPts = new long[n];
        long[] vPts = new long[n];
        List<Long> vVals = new ArrayList<>(2 * n);
        for (int i = 0; i < n; i++) {
            long x = nextLong();
            long y = nextLong();
            long u = x + y;
            long v = x - y;
            uPts[i] = u;
            vPts[i] = v;
            vVals.add(v);
        }

        int q = nextInt();
        long[] uq = new long[q];
        long[] vq = new long[q];
        long[] d = new long[q];
        for (int i = 0; i < q; i++) {
            long x = nextLong();
            long y = nextLong();
            long dist = nextLong();
            uq[i] = x + y;
            vq[i] = x - y;
            d[i] = dist;
            vVals.add(vq[i] - dist);
            vVals.add(vq[i] + dist);
            if (dist > 0) {
                long d0 = dist - 1;
                vVals.add(vq[i] - d0);
                vVals.add(vq[i] + d0);
            }
        }

        long[] vUnique = vVals.stream().distinct().sorted().mapToLong(Long::longValue).toArray();

        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int vIdx = lowerBound(vUnique, vPts[i]) + 1; // Fenwick is 1-based
            points[i] = new Point(uPts[i], vIdx);
        }
        Arrays.sort(points, Comparator.comparingLong(p -> p.u));

        List<Event> events = new ArrayList<>(2 * q * 2);
        for (int i = 0; i < q; i++) {
            addEvents(events, uq[i], vq[i], d[i], i, 0, vUnique);
            if (d[i] > 0) {
                addEvents(events, uq[i], vq[i], d[i] - 1, i, 1, vUnique);
            }
        }
        events.sort(Comparator.comparingLong(e -> e.uLimit));

        Fenwick fenwick = new Fenwick(vUnique.length);
        long[][] res = new long[q][2];
        int ptrPts = 0;
        for (Event ev : events) {
            while (ptrPts < n && points[ptrPts].u <= ev.uLimit) {
                fenwick.add(points[ptrPts].vIdx, 1);
                ptrPts++;
            }
            int cnt = fenwick.rangeSum(ev.lIdx, ev.rIdx);
            res[ev.queryId][ev.which] += ev.sign * cnt;
        }

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < q; i++) {
            long ans = res[i][0] - res[i][1];
            out.append(ans);
            if (i + 1 < q) out.append('\n');
        }
        System.out.print(out.toString());
    }

    private static void addEvents(List<Event> events, long uq, long vq, long rad,
                                  int id, int which, long[] vUnique) {
        long left = uq - rad;
        long right = uq + rad;
        long vLow = vq - rad;
        long vHigh = vq + rad;
        int lIdx = lowerBound(vUnique, vLow) + 1;
        int rIdx = upperBound(vUnique, vHigh); // returns 1-based inclusive index
        events.add(new Event(right, lIdx, rIdx, id, which, +1));
        events.add(new Event(left - 1, lIdx, rIdx, id, which, -1));
    }

    private static int lowerBound(long[] arr, long x) {
        int l = 0, r = arr.length;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (arr[m] < x) l = m + 1;
            else r = m;
        }
        return l;
    }

    private static int upperBound(long[] arr, long x) {
        int l = 0, r = arr.length;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (arr[m] <= x) l = m + 1;
            else r = m;
        }
        return l; // 0-based
    }

    private static int nextInt() throws Exception {
        int c;
        do {
            c = read();
        } while (c <= ' ' && c != -1);
        int sign = 1;
        if (c == '-') {
            sign = -1;
            c = read();
        }
        int v = 0;
        while (c > ' ') {
            v = v * 10 + (c - '0');
            c = read();
        }
        return v * sign;
    }

    private static long nextLong() throws Exception {
        int c;
        do {
            c = read();
        } while (c <= ' ' && c != -1);
        int sign = 1;
        if (c == '-') {
            sign = -1;
            c = read();
        }
        long v = 0;
        while (c > ' ') {
            v = v * 10 + (c - '0');
            c = read();
        }
        return v * sign;
    }

    private static int read() throws Exception {
        if (ptr >= len) {
            len = System.in.read(BUFFER);
            ptr = 0;
            if (len <= 0) return -1;
        }
        return BUFFER[ptr++];
    }
}
