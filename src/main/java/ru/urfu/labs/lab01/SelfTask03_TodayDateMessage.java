package ru.urfu.labs.lab01;

import java.util.Scanner;

public class SelfTask03_TodayDateMessage {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String weekday = in.nextLine();
		String month = in.nextLine();
		int day = in.nextInt();
		System.out.println("Сегодня: " + weekday + ", " + day + ", " + month);
		in.close();
	}
}
