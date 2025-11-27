package ru.urfu.labs.lab05;

import java.util.Scanner;

public class Task04_CharIntConstructors {

    private final char symbol;
    private final int number;

    public Task04_CharIntConstructors(int number, char symbol) {
        this.symbol = symbol;
        this.number = number;
    }

    public Task04_CharIntConstructors(double value) {
        int codePoint = (int) value;
        this.symbol = clampToCharRange(codePoint);
        this.number = extractHundredths(value);
    }

    public void printInfo() {
        System.out.println("Символ: " + symbol + " (" + (int) symbol + "), число: " + number);
    }

    private static char clampToCharRange(int codePoint) {
        if (codePoint < Character.MIN_VALUE) {
            return Character.MIN_VALUE;
        }
        if (codePoint > Character.MAX_VALUE) {
            return Character.MAX_VALUE;
        }
        return (char) codePoint;
    }

    private static int extractHundredths(double value) {
        double scaled = Math.abs(value) * 100.0;
        int truncated = (int) Math.floor(scaled + 1e-9);
        return truncated % 100;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите целое число для поля: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Ошибка: требуется целое число.");
            scanner.close();
            return;
        }
        int numberValue = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введите символ для поля: ");
        String symbolLine = scanner.nextLine();
        if (symbolLine.isEmpty()) {
            System.out.println("Ошибка: требуется символ.");
            scanner.close();
            return;
        }
        char symbolValue = symbolLine.charAt(0);

        Task04_CharIntConstructors fromArgs = new Task04_CharIntConstructors(numberValue, symbolValue);
        System.out.print("Объект из конструктора с двумя аргументами: ");
        fromArgs.printInfo();

        System.out.print("Введите число типа double для конструктора: ");
        if (!scanner.hasNextDouble()) {
            System.out.println("Ошибка: требуется число с плавающей точкой.");
            scanner.close();
            return;
        }
        double doubleValue = scanner.nextDouble();

        Task04_CharIntConstructors fromDouble = new Task04_CharIntConstructors(doubleValue);
        System.out.print("Объект из конструктора с double: ");
        fromDouble.printInfo();

        scanner.close();
    }
}


