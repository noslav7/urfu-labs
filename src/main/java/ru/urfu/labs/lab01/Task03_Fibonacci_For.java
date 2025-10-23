package ru.urfu.labs.lab01;

import java.util.Scanner;

public class Task03_Fibonacci_For {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Введите количество чисел Фибоначчи: ");
		if (!scanner.hasNextInt()) {
			System.out.println("Некорректное значение");
			scanner.close();
			return;
		}

		int n = scanner.nextInt();
		if (n <= 0) {
			System.out.println("Некорректное значение");
			scanner.close();
			return;
		}

		long a = 1;
		long b = 1;
		for (int i = 1; i <= n; i++) {
			if (i == 1 || i == 2) {
				System.out.print("1");
			} else {
				long c = a + b;
				System.out.print(c);
				a = b;
				b = c;
			}
			if (i < n) {
				System.out.print(", ");
			}
		}
		System.out.println();

		scanner.close();
	}
}
