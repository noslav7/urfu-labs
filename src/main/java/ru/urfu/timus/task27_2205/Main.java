package ru.urfu.timus.task27_2205;

public class Main {
    private static final int MAX = 10_000_000;
    private static final int BUF = 1 << 16;
    private static final byte[] BUF_ARR = new byte[BUF];
    private static final int[] SPF = new int[MAX + 1];
    private static final int[] DEPTH = new int[MAX + 1];
    private static final int[] PRIMES = new int[700_000];
    private static final int[] TMP = new int[32];

    private static int len = 0;
    private static int ptr = 0;

    public static void main(String[] args) throws Exception {
        buildSpf();

        int n = nextInt();
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int a = nextInt();
            int b = nextInt();
            out.append(lcaValue(a, b)).append('\n');
        }
        System.out.print(out.toString());
    }

    private static int lcaValue(int a, int b) {
        int da = depth(a);
        int db = depth(b);
        int x = a, y = b;
        while (da > db) {
            x = parent(x);
            da--;
        }
        while (db > da) {
            y = parent(y);
            db--;
        }
        while (x != y) {
            x = parent(x);
            y = parent(y);
        }
        return x;
    }

    private static int depth(int x) {
        int cached = DEPTH[x];
        if (cached != 0) return cached;

        int cnt = 0;
        int cur = x;
        while (cur != 1 && DEPTH[cur] == 0) {
            TMP[cnt++] = cur;
            cur = parent(cur);
        }
        int base = DEPTH[cur];
        for (int i = cnt - 1; i >= 0; i--) {
            DEPTH[TMP[i]] = base + (cnt - i);
        }
        return DEPTH[x];
    }

    private static int parent(int x) {
        return x / SPF[x];
    }

    private static void buildSpf() {
        int pCount = 0;
        for (int i = 2; i <= MAX; i++) {
            if (SPF[i] == 0) {
                SPF[i] = i;
                PRIMES[pCount++] = i;
            }
            for (int j = 0; j < pCount; j++) {
                int p = PRIMES[j];
                int v = p * i;
                if (v > MAX) break;
                SPF[v] = p;
                if (p == SPF[i]) break;
            }
        }
        SPF[1] = 1;
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

    private static int read() throws Exception {
        if (ptr >= len) {
            len = System.in.read(BUF_ARR);
            ptr = 0;
            if (len <= 0) return -1;
        }
        return BUF_ARR[ptr++];
    }
}
