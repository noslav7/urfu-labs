package ru.urfu.labs.lab01;

import java.time.LocalDate;
import java.util.Scanner;

public class SelfTask07_BirthYearByAge {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int age = in.nextInt();
		int currentYear = LocalDate.now().getYear();
		int birthYear = currentYear - age;
		System.out.println("Год рождения: " + birthYear);
		in.close();
	}
}
