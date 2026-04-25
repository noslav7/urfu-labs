package ru.urfu.timus.task28_2219;

import java.util.Arrays;

public class Main {
    private static final int INPUT_BUFFER_SIZE = 1 << 16;
    private static final byte[] INPUT_BUFFER = new byte[INPUT_BUFFER_SIZE];
    private static int bufferLength = 0;
    private static int bufferPointer = 0;

    public static void main(String[] args) throws Exception {
        int vertexCount = nextInt();
        int edgeCount = nextInt();
        int cupCount = nextInt();

        int[] firstEdgeByVertex = new int[vertexCount + 1];
        int[] edgeTo = new int[2 * edgeCount];
        int[] nextEdge = new int[2 * edgeCount];
        Arrays.fill(firstEdgeByVertex, -1);
        int directedEdgeCount = 0;
        for (int i = 0; i < edgeCount; i++) {
            int from = nextInt();
            int to = nextInt();
            edgeTo[directedEdgeCount] = to;
            nextEdge[directedEdgeCount] = firstEdgeByVertex[from];
            firstEdgeByVertex[from] = directedEdgeCount++;

            edgeTo[directedEdgeCount] = from;
            nextEdge[directedEdgeCount] = firstEdgeByVertex[to];
            firstEdgeByVertex[to] = directedEdgeCount++;
        }

        long[] cupValues = new long[cupCount];
        for (int i = 0; i < cupCount; i++) {
            cupValues[i] = nextLong();
        }

        int[] distances = computeDistancesFromVertexOne(vertexCount, firstEdgeByVertex, edgeTo, nextEdge);

        int[] answer = solve(distances, cupValues);

        StringBuilder output = new StringBuilder();
        for (int vertex = 1; vertex <= vertexCount; vertex++) {
            output.append(answer[vertex]);
            if (vertex + 1 <= vertexCount) {
                output.append(' ');
            }
        }
        System.out.print(output.toString());
    }

    private static int[] computeDistancesFromVertexOne(int vertexCount, int[] firstEdgeByVertex,
                                                        int[] edgeTo, int[] nextEdge) {
        int[] distances = new int[vertexCount + 1];
        Arrays.fill(distances, -1);
        int[] queue = new int[vertexCount];
        int queueLeft = 0, queueRight = 0;
        queue[queueRight++] = 1;
        distances[1] = 0;
        while (queueLeft < queueRight) {
            int vertex = queue[queueLeft++];
            for (int edge = firstEdgeByVertex[vertex]; edge != -1; edge = nextEdge[edge]) {
                int neighbor = edgeTo[edge];
                if (distances[neighbor] == -1) {
                    distances[neighbor] = distances[vertex] + 1;
                    queue[queueRight++] = neighbor;
                }
            }
        }
        return distances;
    }

    private static int[] solve(int[] distances, long[] cupValues) {
        int vertexCount = distances.length - 1;
        int cupCount = cupValues.length;

        Arrays.sort(cupValues);
        for (int left = 0, right = cupCount - 1; left < right; left++, right--) {
            long temp = cupValues[left];
            cupValues[left] = cupValues[right];
            cupValues[right] = temp;
        }

        long[] prefixSum = new long[cupCount + 1];
        long[] minEven = new long[cupCount + 1];
        long[] minOdd = new long[cupCount + 1];
        final long INF = Long.MAX_VALUE / 4;
        minEven[0] = minOdd[0] = INF;
        for (int i = 1; i <= cupCount; i++) {
            prefixSum[i] = prefixSum[i - 1] + cupValues[i - 1];
            if ((cupValues[i - 1] & 1L) == 0L) {
                minEven[i] = Math.min(minEven[i - 1], cupValues[i - 1]);
                minOdd[i] = minOdd[i - 1];
            } else {
                minOdd[i] = Math.min(minOdd[i - 1], cupValues[i - 1]);
                minEven[i] = minEven[i - 1];
            }
        }

        long[] maxEvenSuffix = new long[cupCount + 1];
        long[] maxOddSuffix = new long[cupCount + 1];
        maxEvenSuffix[cupCount] = maxOddSuffix[cupCount] = -1;
        for (int i = cupCount - 1; i >= 0; i--) {
            long value = cupValues[i];
            if ((value & 1L) == 0L) {
                maxEvenSuffix[i] = Math.max(maxEvenSuffix[i + 1], value);
                maxOddSuffix[i] = maxOddSuffix[i + 1];
            } else {
                maxOddSuffix[i] = Math.max(maxOddSuffix[i + 1], value);
                maxEvenSuffix[i] = maxEvenSuffix[i + 1];
            }
        }

        long[] bestEven = new long[cupCount + 1];
        long[] bestOdd = new long[cupCount + 1];
        Arrays.fill(bestEven, -1);
        Arrays.fill(bestOdd, -1);
        for (int chosenCupCount = 0; chosenCupCount <= cupCount; chosenCupCount++) {
            long currentSum = prefixSum[chosenCupCount];
            bestEven[chosenCupCount] =
                    bestWithParity(currentSum, 0, chosenCupCount, minEven, minOdd, maxEvenSuffix, maxOddSuffix, INF);
            bestOdd[chosenCupCount] =
                    bestWithParity(currentSum, 1, chosenCupCount, minEven, minOdd, maxEvenSuffix, maxOddSuffix, INF);
        }

        for (int i = 1; i <= cupCount; i++) {
            if (bestEven[i] < bestEven[i - 1]) {
                bestEven[i] = bestEven[i - 1];
            }
            if (bestOdd[i] < bestOdd[i - 1]) {
                bestOdd[i] = bestOdd[i - 1];
            }
        }

        int[] minRequiredCups = new int[vertexCount + 1];
        for (int vertex = 1; vertex <= vertexCount; vertex++) {
            int distance = distances[vertex];
            if (distance == -1) {
                minRequiredCups[vertex] = -1;
                continue;
            }
            int distanceParity = distance & 1;
            long[] bestByParity = distanceParity == 0 ? bestEven : bestOdd;
            int left = 0, right = cupCount, answer = -1;
            while (left <= right) {
                int mid = (left + right) >>> 1;
                if (bestByParity[mid] >= distance) {
                    answer = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            minRequiredCups[vertex] = answer;
        }
        return minRequiredCups;
    }

    private static long bestWithParity(long sum, int requiredParity, int chosenCupCount,
                                       long[] minEven, long[] minOdd,
                                       long[] maxEvenSuffix, long[] maxOddSuffix,
                                       long INF) {
        if (((sum ^ requiredParity) & 1L) == 0L) {
            return sum;
        }
        long best = -1;
        if (requiredParity == 0) { // хотим чётную сумму
            if (minOdd[chosenCupCount] != INF && maxEvenSuffix[chosenCupCount] != -1) {
                best = Math.max(best, sum - minOdd[chosenCupCount] + maxEvenSuffix[chosenCupCount]);
            }
            if (minEven[chosenCupCount] != INF && maxOddSuffix[chosenCupCount] != -1) {
                best = Math.max(best, sum - minEven[chosenCupCount] + maxOddSuffix[chosenCupCount]);
            }
        } else { // хотим нечётную сумму
            if (minEven[chosenCupCount] != INF && maxOddSuffix[chosenCupCount] != -1) {
                best = Math.max(best, sum - minEven[chosenCupCount] + maxOddSuffix[chosenCupCount]);
            }
            if (minOdd[chosenCupCount] != INF && maxEvenSuffix[chosenCupCount] != -1) {
                best = Math.max(best, sum - minOdd[chosenCupCount] + maxEvenSuffix[chosenCupCount]);
            }
        }
        return best;
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

    private static long nextLong() throws Exception {
        int c;
        do {
            c = read();
        } while (c <= ' ' && c != -1);
        long value = 0;
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
            if (bufferLength <= 0) {
                return -1;
            }
        }
        return INPUT_BUFFER[bufferPointer++];
    }
}
