package ru.urfu.labs.lab01;

import java.util.Scanner;

public class Task01_DayOfWeekByNumber_Switch {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Введите целое число от 1 до 7: ");
		if (!scanner.hasNextInt()) {
			System.out.println("Некорректное значение");
			scanner.close();
			return;
		}

		int number = scanner.nextInt();
		String dayName;
		switch (number) {
			case 1:
				dayName = "Понедельник";
				break;
			case 2:
				dayName = "Вторник";
				break;
			case 3:
				dayName = "Среда";
				break;
			case 4:
				dayName = "Четверг";
				break;
			case 5:
				dayName = "Пятница";
				break;
			case 6:
				dayName = "Суббота";
				break;
			case 7:
				dayName = "Воскресенье";
				break;
			default:
				dayName = null;
		}

		if (dayName == null) {
			System.out.println("Некорректное значение");
		} else {
			System.out.println(dayName);
		}

		scanner.close();
	}
}
