package ru.urfu.labs.lab01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task05_SumCriteria_For {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Введите количество чисел для суммы: ");
		if (!scanner.hasNextInt()) {
			System.out.println("Некорректное значение");
			scanner.close();
			return;
		}
		int k = scanner.nextInt();
		if (k <= 0) {
			System.out.println("Некорректное значение");
			scanner.close();
			return;
		}

		long sum = 0;
		List<Integer> selected = new ArrayList<>();
		for (int n = 0; selected.size() < k; n++) {
			if (n % 5 == 2 || n % 3 == 1) {
				selected.add(n);
				sum += n;
			}
		}

		System.out.println("Числа: " + selected);
		System.out.println("Сумма: " + sum);

		scanner.close();
	}
}
