package ru.urfu.labs.lab01;

import java.util.Scanner;

public class Task02_DayNumberByName_IfElse {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Введите название дня недели: ");
		String input = scanner.nextLine();
		String normalized = input.trim().toLowerCase();

		Integer dayNumber;
		if ("понедельник".equals(normalized)) {
			dayNumber = 1;
		} else if ("вторник".equals(normalized)) {
			dayNumber = 2;
		} else if ("среда".equals(normalized)) {
			dayNumber = 3;
		} else if ("четверг".equals(normalized)) {
			dayNumber = 4;
		} else if ("пятница".equals(normalized)) {
			dayNumber = 5;
		} else if ("суббота".equals(normalized)) {
			dayNumber = 6;
		} else if ("воскресенье".equals(normalized)) {
			dayNumber = 7;
		} else {
			dayNumber = null;
		}

		if (dayNumber == null) {
			System.out.println("Такого дня нет");
		} else {
			System.out.println("Порядковый номер дня: " + dayNumber);
		}

		scanner.close();
	}
}
