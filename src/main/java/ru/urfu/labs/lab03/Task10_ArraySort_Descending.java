package ru.urfu.labs.lab03;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Task10_ArraySort_Descending {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Введите размер массива: ");
		if (!scanner.hasNextInt()) {
			System.out.println("Некорректное значение размера");
			scanner.close();
			return;
		}
		int n = scanner.nextInt();
		if (n <= 0) {
			System.out.println("Некорректное значение размера");
			scanner.close();
			return;
		}

		int[] arr = new int[n];
		Random rnd = new Random();
		for (int i = 0; i < n; i++) {
			arr[i] = rnd.nextInt(2001) - 1000; // [-1000, 1000]
		}

		// сортировка по убыванию (пузырьком)
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - 1 - i; j++) {
				if (arr[j] < arr[j + 1]) {
					int tmp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp;
				}
			}
		}

		System.out.println(Arrays.toString(arr));

		scanner.close();
	}
}
