package ru.urfu.labs.lab01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Task09_ArrayMinAndIndices {
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
		System.out.println(Arrays.toString(arr));

		int min = arr[0];
		for (int i = 1; i < n; i++) {
			if (arr[i] < min) min = arr[i];
		}

		List<Integer> indices = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (arr[i] == min) indices.add(i);
		}

		System.out.println("Минимальное значение: " + min);
		System.out.println("Индексы минимальных элементов: " + indices);

		scanner.close();
	}
}
