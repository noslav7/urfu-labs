package ru.urfu.labs.lab01;

import java.time.LocalDate;
import java.util.Scanner;

public class SelfTask06_NameAndAgeByBirthYear {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String name = in.nextLine();
		int birthYear = in.nextInt();
		int currentYear = LocalDate.now().getYear();
		int age = currentYear - birthYear;
		System.out.println("Name: " + name + ", Age: " + age);
		in.close();
	}
}
