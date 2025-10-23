package ru.urfu.labs.lab03;

public class Task07_CharArray_StepTwo {
	public static void main(String[] args) {
		int size = 10;
		char[] chars = new char[size];
		char c = 'a';
		for (int i = 0; i < size; i++) {
			chars[i] = c;
			c += 2; // через одну букву
		}

		// прямой порядок
		for (int i = 0; i < size; i++) {
			System.out.print(chars[i]);
			if (i < size - 1) System.out.print(" ");
		}
		System.out.println();

		// обратный порядок
		for (int i = size - 1; i >= 0; i--) {
			System.out.print(chars[i]);
			if (i > 0) System.out.print(" ");
		}
		System.out.println();
	}
}
