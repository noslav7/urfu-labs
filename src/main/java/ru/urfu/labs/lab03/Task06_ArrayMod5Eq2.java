package ru.urfu.labs.lab03;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Task06_ArrayMod5Eq2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Введите размер массива: ");
		int n;
		try {
			n = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Ошибка ввода: требуется целое число");
			scanner.close();
			return;
		}
		if (n <= 0) {
			System.out.println("Некорректное значение размера");
			scanner.close();
			return;
		}

		int[] arr = new int[n];
		int value = 2;
		for (int i = 0; i < n; i++) {
			arr[i] = value;
			value += 5;
		}

		System.out.println(Arrays.toString(arr));
		scanner.close();
	}
}
