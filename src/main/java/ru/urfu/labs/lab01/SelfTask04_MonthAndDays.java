package ru.urfu.labs.lab01;

import java.util.Scanner;

public class SelfTask04_MonthAndDays {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String month = in.nextLine();
		int days = in.nextInt();
		System.out.println(month + " содержит " + days + " дней");
		in.close();
	}
}
