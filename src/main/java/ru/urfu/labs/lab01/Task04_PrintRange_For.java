package ru.urfu.labs.lab01;

import java.util.Scanner;

public class Task04_PrintRange_For {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Введите два целых числа через пробел: ");
		if (!scanner.hasNextInt()) {
			System.out.println("Некорректное значение");
			scanner.close();
			return;
		}
		int a = scanner.nextInt();
		if (!scanner.hasNextInt()) {
			System.out.println("Некорректное значение");
			scanner.close();
			return;
		}
		int b = scanner.nextInt();

		int start = Math.min(a, b);
		int end = Math.max(a, b);

		for (int x = start; x <= end; x++) {
			System.out.print(x);
			if (x < end) {
				System.out.print(" ");
			}
		}
		System.out.println();

		scanner.close();
	}
}
