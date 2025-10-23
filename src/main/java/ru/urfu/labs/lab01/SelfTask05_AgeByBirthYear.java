package ru.urfu.labs.lab01;

import java.time.LocalDate;
import java.util.Scanner;

public class SelfTask05_AgeByBirthYear {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int birthYear = in.nextInt();
		int currentYear = LocalDate.now().getYear();
		int age = currentYear - birthYear;
		System.out.println("Возраст: " + age);
		in.close();
	}
}
