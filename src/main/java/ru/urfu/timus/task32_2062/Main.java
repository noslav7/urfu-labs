package ru.urfu.timus.task32_2062;

public class Main {
    static final byte[] INPUT_BUFFER = new byte[1 << 16];
    static int bufferLength, bufferPointer;
    static int particleCount, blockSize;
    static long[] initialCharge, addByIndex, lazyByBlock;
    static int[] divStart, divisors;

    public static void main(String[] args) throws Exception {
        particleCount = nextInt();
        initialCharge = new long[particleCount + 1];
        for (int i = 1; i <= particleCount; i++) initialCharge[i] = nextInt();
        buildDivisors();

        blockSize = 700;
        addByIndex = new long[particleCount + 1];
        lazyByBlock = new long[(particleCount + blockSize - 1) / blockSize];

        int queryCount = nextInt();
        StringBuilder output = new StringBuilder();
        while (queryCount-- > 0) {
            if (nextInt() == 1) output.append(getExpectedCharge(nextInt())).append('\n');
            else applyRadiation(nextInt(), nextInt(), nextInt());
        }
        System.out.print(output);
    }

    static void buildDivisors() {
        int[] divisorCountByIndex = new int[particleCount + 1];
        for (int divisor = 1; divisor <= particleCount; divisor++) {
            for (int multiple = divisor; multiple <= particleCount; multiple += divisor) {
                divisorCountByIndex[multiple]++;
            }
        }

        divStart = new int[particleCount + 2];
        for (int i = 1; i <= particleCount; i++) divStart[i + 1] = divStart[i] + divisorCountByIndex[i];

        divisors = new int[divStart[particleCount + 1]];
        int[] currentFillPosition = divStart.clone();
        for (int divisor = 1; divisor <= particleCount; divisor++) {
            for (int multiple = divisor; multiple <= particleCount; multiple += divisor) {
                divisors[currentFillPosition[multiple]++] = divisor;
            }
        }
    }

    static long getExpectedCharge(int particleIndex) {
        long expectedCharge = initialCharge[particleIndex];
        for (int pos = divStart[particleIndex]; pos < divStart[particleIndex + 1]; pos++) {
            int divisor = divisors[pos];
            expectedCharge += addByIndex[divisor] + lazyByBlock[(divisor - 1) / blockSize];
        }
        return expectedCharge;
    }

    static void applyRadiation(int left, int right, long power) {
        int leftBlock = (left - 1) / blockSize;
        int rightBlock = (right - 1) / blockSize;
        if (leftBlock == rightBlock) {
            for (int i = left; i <= right; i++) addByIndex[i] += power;
            return;
        }
        int leftEdge = (leftBlock + 1) * blockSize;
        for (int i = left; i <= leftEdge; i++) addByIndex[i] += power;
        for (int block = leftBlock + 1; block < rightBlock; block++) lazyByBlock[block] += power;
        int rightStart = rightBlock * blockSize + 1;
        for (int i = rightStart; i <= right; i++) addByIndex[i] += power;
    }

    static int nextInt() throws Exception {
        int currentChar;
        do currentChar = read(); while (currentChar <= 32 && currentChar != -1);
        int sign = 1, value = 0;
        if (currentChar == '-') {
            sign = -1;
            currentChar = read();
        }
        while (currentChar > 32) {
            value = value * 10 + currentChar - '0';
            currentChar = read();
        }
        return value * sign;
    }

    static int read() throws Exception {
        if (bufferPointer >= bufferLength) {
            bufferLength = System.in.read(INPUT_BUFFER);
            bufferPointer = 0;
            if (bufferLength < 0) return -1;
        }
        return INPUT_BUFFER[bufferPointer++];
    }
}
