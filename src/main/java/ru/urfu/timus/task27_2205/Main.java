package ru.urfu.timus.task27_2205;

public class Main {
    private static final int MAX_VALUE = 10_000_000;
    private static final int INPUT_BUFFER_SIZE = 1 << 16;
    private static final byte[] INPUT_BUFFER = new byte[INPUT_BUFFER_SIZE];
    private static final int[] smallestPrimeFactor = new int[MAX_VALUE + 1];
    private static final int[] depthCache = new int[MAX_VALUE + 1];
    private static final int[] primes = new int[700_000];
    private static final int[] tempPath = new int[32];

    private static int bufferLength = 0;
    private static int bufferPointer = 0;

    public static void main(String[] args) throws Exception {
        buildSpf();

        int queryCount = nextInt();
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < queryCount; i++) {
            int first = nextInt();
            int second = nextInt();
            output.append(findLowestCommonAncestorValue(first, second)).append('\n');
        }
        System.out.print(output.toString());
    }

    private static int findLowestCommonAncestorValue(int first, int second) {
        int firstDepth = getDepth(first);
        int secondDepth = getDepth(second);
        int firstValue = first, secondValue = second;
        while (firstDepth > secondDepth) {
            firstValue = getParent(firstValue);
            firstDepth--;
        }
        while (secondDepth > firstDepth) {
            secondValue = getParent(secondValue);
            secondDepth--;
        }
        while (firstValue != secondValue) {
            firstValue = getParent(firstValue);
            secondValue = getParent(secondValue);
        }
        return firstValue;
    }

    private static int getDepth(int value) {
        int cached = depthCache[value];
        if (cached != 0) return cached;

        int pathLength = 0;
        int current = value;
        while (current != 1 && depthCache[current] == 0) {
            tempPath[pathLength++] = current;
            current = getParent(current);
        }
        int knownDepth = depthCache[current];
        for (int i = pathLength - 1; i >= 0; i--) {
            depthCache[tempPath[i]] = knownDepth + (pathLength - i);
        }
        return depthCache[value];
    }

    private static int getParent(int value) {
        return value / smallestPrimeFactor[value];
    }

    private static void buildSpf() {
        int primeCount = 0;
        for (int value = 2; value <= MAX_VALUE; value++) {
            if (smallestPrimeFactor[value] == 0) {
                smallestPrimeFactor[value] = value;
                primes[primeCount++] = value;
            }
            for (int j = 0; j < primeCount; j++) {
                int prime = primes[j];
                int composite = prime * value;
                if (composite > MAX_VALUE) break;
                smallestPrimeFactor[composite] = prime;
                if (prime == smallestPrimeFactor[value]) break;
            }
        }
        smallestPrimeFactor[1] = 1;
    }

    private static int nextInt() throws Exception {
        int c;
        do {
            c = read();
        } while (c <= ' ' && c != -1);
        int value = 0;
        while (c > ' ') {
            value = value * 10 + (c - '0');
            c = read();
        }
        return value;
    }

    private static int read() throws Exception {
        if (bufferPointer >= bufferLength) {
            bufferLength = System.in.read(INPUT_BUFFER);
            bufferPointer = 0;
            if (bufferLength <= 0) return -1;
        }
        return INPUT_BUFFER[bufferPointer++];
    }
}
