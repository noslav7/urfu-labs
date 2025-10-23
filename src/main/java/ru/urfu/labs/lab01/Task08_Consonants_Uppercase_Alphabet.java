package ru.urfu.labs.lab01;

import java.util.Arrays;

public class Task08_Consonants_Uppercase_Alphabet {
	public static void main(String[] args) {
		int size = 10;
		char[] letters = new char[size];
		int index = 0;
		for (char ch = 'A'; ch <= 'Z' && index < size; ch++) {
			if (isConsonant(ch)) {
				letters[index++] = ch;
			}
		}
		System.out.println(Arrays.toString(letters));
	}

	private static boolean isConsonant(char ch) {
		char c = Character.toUpperCase(ch);
		return c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U' && c != 'Y';
	}
}
