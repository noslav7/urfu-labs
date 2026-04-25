package ru.urfu.timus.task29_2218;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    private static final int INPUT_BUFFER_SIZE = 1 << 16;
    private static final byte[] INPUT_BUFFER = new byte[INPUT_BUFFER_SIZE];
    private static int bufferLength = 0;
    private static int bufferPointer = 0;

    private static class Point {
        final long transformedU;
        final int transformedVIndex;

        Point(long transformedU, int transformedVIndex) {
            this.transformedU = transformedU;
            this.transformedVIndex = transformedVIndex;
        }
    }

    private static class Event {
        final long uLimit;
        final int leftVIndex;
        final int rightVIndex;
        final int queryId;
        final int radiusType; // 0 -> radius d, 1 -> radius d-1
        final int sign;  // +1 or -1

        Event(long uLimit, int leftVIndex, int rightVIndex, int queryId, int radiusType, int sign) {
            this.uLimit = uLimit;
            this.leftVIndex = leftVIndex;
            this.rightVIndex = rightVIndex;
            this.queryId = queryId;
            this.radiusType = radiusType;
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
            int result = 0;
            for (int i = idx; i > 0; i -= i & -i) {
                result += bit[i];
            }
            return result;
        }

        int rangeSum(int left, int right) {
            if (left > right) return 0;
            return sumPrefix(right) - sumPrefix(left - 1);
        }
    }

    public static void main(String[] args) throws Exception {
        int pointCount = nextInt();
        long[] transformedUByPoint = new long[pointCount];
        long[] transformedVByPoint = new long[pointCount];
        List<Long> allVValues = new ArrayList<>(2 * pointCount);
        for (int i = 0; i < pointCount; i++) {
            long x = nextLong();
            long y = nextLong();
            long transformedU = x + y;
            long transformedV = x - y;
            transformedUByPoint[i] = transformedU;
            transformedVByPoint[i] = transformedV;
            allVValues.add(transformedV);
        }

        int queryCount = nextInt();
        long[] queryU = new long[queryCount];
        long[] queryV = new long[queryCount];
        long[] queryRadius = new long[queryCount];
        for (int i = 0; i < queryCount; i++) {
            long x = nextLong();
            long y = nextLong();
            long radius = nextLong();
            queryU[i] = x + y;
            queryV[i] = x - y;
            queryRadius[i] = radius;
            allVValues.add(queryV[i] - radius);
            allVValues.add(queryV[i] + radius);
            if (radius > 0) {
                long reducedRadius = radius - 1;
                allVValues.add(queryV[i] - reducedRadius);
                allVValues.add(queryV[i] + reducedRadius);
            }
        }

        long[] sortedUniqueV = allVValues.stream().distinct().sorted().mapToLong(Long::longValue).toArray();

        Point[] points = new Point[pointCount];
        for (int i = 0; i < pointCount; i++) {
            int vIndex = lowerBound(sortedUniqueV, transformedVByPoint[i]) + 1; // Fenwick is 1-based
            points[i] = new Point(transformedUByPoint[i], vIndex);
        }
        Arrays.sort(points, Comparator.comparingLong(point -> point.transformedU));

        List<Event> events = new ArrayList<>(2 * queryCount * 2);
        for (int i = 0; i < queryCount; i++) {
            addEvents(events, queryU[i], queryV[i], queryRadius[i], i, 0, sortedUniqueV);
            if (queryRadius[i] > 0) {
                addEvents(events, queryU[i], queryV[i], queryRadius[i] - 1, i, 1, sortedUniqueV);
            }
        }
        events.sort(Comparator.comparingLong(e -> e.uLimit));

        Fenwick fenwick = new Fenwick(sortedUniqueV.length);
        long[][] partialCounts = new long[queryCount][2];
        int pointPointer = 0;
        for (Event event : events) {
            while (pointPointer < pointCount && points[pointPointer].transformedU <= event.uLimit) {
                fenwick.add(points[pointPointer].transformedVIndex, 1);
                pointPointer++;
            }
            int pointsInRectangle = fenwick.rangeSum(event.leftVIndex, event.rightVIndex);
            partialCounts[event.queryId][event.radiusType] += event.sign * pointsInRectangle;
        }

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < queryCount; i++) {
            long exactDistanceCount = partialCounts[i][0] - partialCounts[i][1];
            output.append(exactDistanceCount);
            if (i + 1 < queryCount) output.append('\n');
        }
        System.out.print(output.toString());
    }

    private static void addEvents(List<Event> events, long queryU, long queryV, long radius,
                                  int queryId, int radiusType, long[] sortedUniqueV) {
        long leftULimit = queryU - radius;
        long rightULimit = queryU + radius;
        long minV = queryV - radius;
        long maxV = queryV + radius;
        int leftVIndex = lowerBound(sortedUniqueV, minV) + 1;
        int rightVIndex = upperBound(sortedUniqueV, maxV); // returns 1-based inclusive index
        events.add(new Event(rightULimit, leftVIndex, rightVIndex, queryId, radiusType, +1));
        events.add(new Event(leftULimit - 1, leftVIndex, rightVIndex, queryId, radiusType, -1));
    }

    private static int lowerBound(long[] arr, long x) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (arr[mid] < x) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    private static int upperBound(long[] arr, long x) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (arr[mid] <= x) left = mid + 1;
            else right = mid;
        }
        return left; // 0-based
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
        int value = 0;
        while (c > ' ') {
            value = value * 10 + (c - '0');
            c = read();
        }
        return value * sign;
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
        long value = 0;
        while (c > ' ') {
            value = value * 10 + (c - '0');
            c = read();
        }
        return value * sign;
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
