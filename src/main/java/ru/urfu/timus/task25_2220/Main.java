package ru.urfu.timus.task25_2220;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int MOD = 1_000_000_007;
    private static StringTokenizer tokenizer;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = nextInt(reader);

        int[] phiPrefix = buildPhiPrefix(n);
        int[] invPrefix = buildInvPrefix(n);

        long sum = 0L;
        int left = 1;
        while (left <= n) {
            int value = n / left;
            int right = n / value;

            int invRange = invPrefix[right] - invPrefix[left - 1];
            if (invRange < 0) {
                invRange += MOD;
            }

            sum = (sum + (long) phiPrefix[value] * invRange) % MOD;

            left = right + 1;
        }

        long pairs = (long) n * (n + 1) / 2 % MOD;
        long answer = sum * modPow(pairs, MOD - 2) % MOD;
        System.out.println(answer);
    }

    private static int[] buildPhiPrefix(int n) {
        int[] phi = new int[n + 1];
        int[] primes = new int[n + 1];
        boolean[] composite = new boolean[n + 1];
        int primeCount = 0;

        phi[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (!composite[i]) {
                primes[primeCount++] = i;
                phi[i] = i - 1;
            }
            for (int j = 0; j < primeCount; j++) {
                int p = primes[j];
                long value = (long) i * p;
                if (value > n) {
                    break;
                }
                int x = (int) value;
                composite[x] = true;
                if (i % p == 0) {
                    phi[x] = phi[i] * p;
                    break;
                } else {
                    phi[x] = phi[i] * (p - 1);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            int next = phi[i - 1] + phi[i];
            if (next >= MOD) {
                next -= MOD;
            }
            phi[i] = next;
        }
        return phi;
    }

    private static int[] buildInvPrefix(int n) {
        int[] inv = new int[n + 1];
        int[] prefix = new int[n + 1];
        if (n >= 1) {
            inv[1] = 1;
            prefix[1] = 1;
        }
        for (int i = 2; i <= n; i++) {
            inv[i] = (int) (MOD - (long) (MOD / i) * inv[MOD % i] % MOD);
            int next = prefix[i - 1] + inv[i];
            if (next >= MOD) {
                next -= MOD;
            }
            prefix[i] = next;
        }
        return prefix;
    }

    private static long modPow(long base, long exp) {
        long result = 1L;
        long value = base % MOD;
        long power = exp;
        while (power > 0) {
            if ((power & 1L) != 0L) {
                result = result * value % MOD;
            }
            value = value * value % MOD;
            power >>= 1;
        }
        return result;
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
