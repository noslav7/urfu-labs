package ru.urfu.timus.task30_1989;

public class Main {
    static final int INPUT_BUFFER_SIZE = 1 << 16;
    static final byte[] INPUT_BUFFER = new byte[INPUT_BUFFER_SIZE];
    static int bufferLength, bufferPointer;

    static final long HASH_BASE = 911_382_323L;

    static int stringLength, treeSize;
    static long[] basePowers, forwardHashTree, reverseHashTree;
    static int[] segmentLengths;

    public static void main(String[] args) throws Exception {
        String initialString = nextToken();
        stringLength = initialString.length();
        treeSize = 1;
        while (treeSize < stringLength) treeSize <<= 1;

        basePowers = new long[stringLength + 1];
        basePowers[0] = 1;
        for (int i = 1; i <= stringLength; i++) basePowers[i] = basePowers[i - 1] * HASH_BASE;

        int treeArraySize = treeSize << 1;
        forwardHashTree = new long[treeArraySize];
        reverseHashTree = new long[treeArraySize];
        segmentLengths = new int[treeArraySize];

        for (int i = 0; i < stringLength; i++) {
            int leaf = treeSize + i;
            long charValue = initialString.charAt(i) - 'a' + 1L;
            forwardHashTree[leaf] = reverseHashTree[leaf] = charValue;
            segmentLengths[leaf] = 1;
        }
        for (int node = treeSize - 1; node > 0; node--) rebuildParentNode(node);

        int queryCount = nextInt();
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < queryCount; i++) {
            if (nextToken().charAt(0) == 'c') {
                int position = nextInt() - 1;
                long newCharValue = nextToken().charAt(0) - 'a' + 1L;
                int node = treeSize + position;
                forwardHashTree[node] = reverseHashTree[node] = newCharValue;
                for (node >>= 1; node > 0; node >>= 1) rebuildParentNode(node);
            } else {
                int leftInclusive = nextInt() - 1;
                int rightExclusive = nextInt();
                output.append(isPalindrome(leftInclusive, rightExclusive) ? "Yes\n" : "No\n");
            }
        }
        System.out.print(output);
    }

    static boolean isPalindrome(int left, int right) { // [left, right)
        long leftForwardHash = 0, leftReverseHash = 0;
        long rightForwardHash = 0, rightReverseHash = 0;
        int leftLength = 0, rightLength = 0;
        for (left += treeSize, right += treeSize; left < right; left >>= 1, right >>= 1) {
            if ((left & 1) == 1) {
                leftForwardHash = leftForwardHash * basePowers[segmentLengths[left]] + forwardHashTree[left];
                leftReverseHash = reverseHashTree[left] * basePowers[leftLength] + leftReverseHash;
                leftLength += segmentLengths[left++];
            }
            if ((right & 1) == 1) {
                --right;
                rightForwardHash = forwardHashTree[right] * basePowers[rightLength] + rightForwardHash;
                rightReverseHash = rightReverseHash * basePowers[segmentLengths[right]] + reverseHashTree[right];
                rightLength += segmentLengths[right];
            }
        }
        long forwardHash = leftForwardHash * basePowers[rightLength] + rightForwardHash;
        long reverseHash = rightReverseHash * basePowers[leftLength] + leftReverseHash;
        return forwardHash == reverseHash;
    }

    static void rebuildParentNode(int node) {
        int leftChild = node << 1, rightChild = leftChild + 1;
        segmentLengths[node] = segmentLengths[leftChild] + segmentLengths[rightChild];
        forwardHashTree[node] =
                forwardHashTree[leftChild] * basePowers[segmentLengths[rightChild]] + forwardHashTree[rightChild];
        reverseHashTree[node] =
                reverseHashTree[rightChild] * basePowers[segmentLengths[leftChild]] + reverseHashTree[leftChild];
    }

    static String nextToken() throws Exception {
        int currentChar;
        do currentChar = read(); while (currentChar <= ' ' && currentChar != -1);
        if (currentChar == -1) return null;
        StringBuilder token = new StringBuilder();
        while (currentChar > ' ') {
            token.append((char) currentChar);
            currentChar = read();
        }
        return token.toString();
    }

    static int nextInt() throws Exception {
        int currentChar;
        do currentChar = read(); while (currentChar <= ' ' && currentChar != -1);
        int value = 0;
        while (currentChar > ' ') {
            value = value * 10 + (currentChar - '0');
            currentChar = read();
        }
        return value;
    }

    static int read() throws Exception {
        if (bufferPointer >= bufferLength) {
            bufferLength = System.in.read(INPUT_BUFFER);
            bufferPointer = 0;
            if (bufferLength <= 0) return -1;
        }
        return INPUT_BUFFER[bufferPointer++];
    }
}
