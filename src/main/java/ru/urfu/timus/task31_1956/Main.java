package ru.urfu.timus.task31_1956;

public class Main {
    static final byte[] INPUT_BUFFER = new byte[1 << 16];
    static int bufferLength, bufferPointer, pointCount;
    static double[] xCoordinates, yCoordinates, projections;

    public static void main(String[] args) throws Exception {
        pointCount = nextInt();
        xCoordinates = new double[pointCount];
        yCoordinates = new double[pointCount];
        projections = new double[pointCount];
        for (int i = 0; i < pointCount; i++) {
            xCoordinates[i] = nextInt();
            yCoordinates[i] = nextInt();
        }

        int angleSamples = 6000;
        double pi = Math.PI, angleStep = pi / angleSamples, answer = 1e300;
        double[] sampleValues = new double[angleSamples];
        for (int i = 0; i < angleSamples; i++) {
            sampleValues[i] = costAtAngle((i + .5) * angleStep);
            if (sampleValues[i] < answer) answer = sampleValues[i];
        }
        for (int i = 0; i < angleSamples; i++) {
            int prev = (i - 1 + angleSamples) % angleSamples;
            int next = (i + 1) % angleSamples;
            if (sampleValues[i] <= sampleValues[prev] && sampleValues[i] <= sampleValues[next]) {
                double left = (i - 1 + angleSamples) % angleSamples * angleStep;
                double right = (i + 2) % angleSamples * angleStep;
                if (left < right) answer = Math.min(answer, refineMinimum(left, right));
                else answer = Math.min(answer, Math.min(refineMinimum(0, right), refineMinimum(left, pi)));
            }
        }
        System.out.printf("%.10f%n", answer);
    }

    static double refineMinimum(double left, double right) {
        double goldenPart = 0.6180339887498949;
        double leftProbe = right - (right - left) * goldenPart;
        double rightProbe = left + (right - left) * goldenPart;
        double leftCost = costAtAngle(leftProbe), rightCost = costAtAngle(rightProbe);
        for (int iteration = 0; iteration < 70; iteration++) {
            if (leftCost <= rightCost) {
                right = rightProbe;
                rightProbe = leftProbe;
                rightCost = leftCost;
                leftProbe = right - (right - left) * goldenPart;
                leftCost = costAtAngle(leftProbe);
            } else {
                left = leftProbe;
                leftProbe = rightProbe;
                leftCost = rightCost;
                rightProbe = left + (right - left) * goldenPart;
                rightCost = costAtAngle(rightProbe);
            }
        }
        return Math.min(leftCost, rightCost);
    }

    static double costAtAngle(double angle) {
        double cos = Math.cos(angle), sin = Math.sin(angle);
        for (int i = 0; i < pointCount; i++) projections[i] = xCoordinates[i] * cos + yCoordinates[i] * sin;
        int medianPos = pointCount >> 1;
        double median = selectKth(medianPos);
        if ((pointCount & 1) == 0) median = (median + selectKth(medianPos - 1)) * .5;
        double sum = 0;
        for (int i = 0; i < pointCount; i++) sum += Math.abs(projections[i] - median);
        return sum;
    }

    static double selectKth(int index) {
        int left = 0, right = pointCount - 1;
        while (true) {
            double pivot = projections[(left + right) >>> 1];
            int leftPointer = left, rightPointer = right;
            while (leftPointer <= rightPointer) {
                while (projections[leftPointer] < pivot) leftPointer++;
                while (projections[rightPointer] > pivot) rightPointer--;
                if (leftPointer <= rightPointer) {
                    double temp = projections[leftPointer];
                    projections[leftPointer++] = projections[rightPointer];
                    projections[rightPointer--] = temp;
                }
            }
            if (index <= rightPointer) right = rightPointer;
            else if (index >= leftPointer) left = leftPointer;
            else return projections[index];
        }
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
