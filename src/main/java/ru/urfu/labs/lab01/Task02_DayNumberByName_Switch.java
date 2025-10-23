package ru.urfu.labs.lab01;

import java.util.Scanner;

public class Task02_DayNumberByName_Switch {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Введите название дня недели: ");
		String input = scanner.nextLine();
		String normalized = input.trim().toLowerCase();

		Integer dayNumber;
		switch (normalized) {
			case "понедельник":
				dayNumber = 1;
				break;
			case "вторник":
				dayNumber = 2;
				break;
			case "среда":
				dayNumber = 3;
				break;
			case "четверг":
				dayNumber = 4;
				break;
			case "пятница":
				dayNumber = 5;
				break;
			case "суббота":
				dayNumber = 6;
				break;
			case "воскресенье":
				dayNumber = 7;
				break;
			default:
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
