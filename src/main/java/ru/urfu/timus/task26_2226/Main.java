package ru.urfu.timus.task26_2226;

import java.io.BufferedInputStream;
import java.io.IOException;

public class Main {
    private static int[] edgeFrom;
    private static int[] edgeTo;
    private static int[] bestWeightByVertex;
    private static int[] secondBestWeightByVertex;
    private static int[] bestNextVertexByVertex;
    private static int[] bestStateByVertex;
    private static int[] secondBestStateByVertex;
    private static int[] terminalVertexByState;
    private static boolean[] isStateOnStack;
    private static int[] stateStack;

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();
        int vertexCount = scanner.nextInt();
        int undirectedEdgeCount = vertexCount - 1;
        int directedStateCount = 2 * undirectedEdgeCount;

        edgeFrom = new int[directedStateCount];
        edgeTo = new int[directedStateCount];
        bestWeightByVertex = new int[vertexCount + 1];
        secondBestWeightByVertex = new int[vertexCount + 1];
        bestNextVertexByVertex = new int[vertexCount + 1];
        bestStateByVertex = new int[vertexCount + 1];
        secondBestStateByVertex = new int[vertexCount + 1];
        final int INF = Integer.MAX_VALUE;
        for (int vertex = 1; vertex <= vertexCount; vertex++) {
            bestWeightByVertex[vertex] = INF;
            secondBestWeightByVertex[vertex] = INF;
            bestStateByVertex[vertex] = -1;
            secondBestStateByVertex[vertex] = -1;
        }

        int stateIndex = 0;
        for (int i = 0; i < undirectedEdgeCount; i++) {
            int firstVertex = scanner.nextInt();
            int secondVertex = scanner.nextInt();
            int weight = scanner.nextInt();

            edgeFrom[stateIndex] = firstVertex;
            edgeTo[stateIndex] = secondVertex;
            registerTransition(firstVertex, secondVertex, stateIndex, weight);
            stateIndex++;

            edgeFrom[stateIndex] = secondVertex;
            edgeTo[stateIndex] = firstVertex;
            registerTransition(secondVertex, firstVertex, stateIndex, weight);
            stateIndex++;
        }

        terminalVertexByState = new int[directedStateCount];
        isStateOnStack = new boolean[directedStateCount];
        stateStack = new int[directedStateCount];

        StringBuilder output = new StringBuilder();
        for (int startVertex = 1; startVertex <= vertexCount; startVertex++) {
            int firstState = bestStateByVertex[startVertex];
            int answerVertex = (firstState == -1) ? startVertex : resolveTerminalVertex(firstState);

            if (startVertex > 1) {
                output.append(' ');
            }
            output.append(answerVertex);
        }
        System.out.println(output);
    }

    private static void registerTransition(int fromVertex, int toVertex, int state, int weight) {
        if (weight < bestWeightByVertex[fromVertex]) {
            secondBestWeightByVertex[fromVertex] = bestWeightByVertex[fromVertex];
            secondBestStateByVertex[fromVertex] = bestStateByVertex[fromVertex];
            bestWeightByVertex[fromVertex] = weight;
            bestNextVertexByVertex[fromVertex] = toVertex;
            bestStateByVertex[fromVertex] = state;
        } else if (weight < secondBestWeightByVertex[fromVertex]) {
            secondBestWeightByVertex[fromVertex] = weight;
            secondBestStateByVertex[fromVertex] = state;
        }
    }

    private static int getNextState(int currentState) {
        int currentVertex = edgeTo[currentState];
        if (bestStateByVertex[currentVertex] == -1) {
            return -1;
        }
        return (bestNextVertexByVertex[currentVertex] != edgeFrom[currentState])
                ? bestStateByVertex[currentVertex]
                : secondBestStateByVertex[currentVertex];
    }

    private static int resolveTerminalVertex(int startState) {
        if (terminalVertexByState[startState] != 0) {
            return terminalVertexByState[startState];
        }

        int stackSize = 0;
        int currentState = startState;
        int answerVertex = 0;

        while (true) {
            if (terminalVertexByState[currentState] != 0) {
                answerVertex = terminalVertexByState[currentState];
                break;
            }
            if (isStateOnStack[currentState]) {
                answerVertex = edgeTo[currentState];
                break;
            }

            isStateOnStack[currentState] = true;
            stateStack[stackSize++] = currentState;

            int nextState = getNextState(currentState);
            if (nextState == -1) {
                answerVertex = edgeTo[currentState];
                break;
            }
            currentState = nextState;
        }

        while (stackSize > 0) {
            int state = stateStack[--stackSize];
            isStateOnStack[state] = false;
            terminalVertexByState[state] = answerVertex;
        }
        return answerVertex;
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
