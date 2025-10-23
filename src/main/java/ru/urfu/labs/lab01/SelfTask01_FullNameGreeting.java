package ru.urfu.labs.lab01;

import java.util.Scanner;

public class SelfTask01_FullNameGreeting {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String surname = in.nextLine();
		String name = in.nextLine();
		String patronymic = in.nextLine();
		System.out.println("Hello " + surname + ", " + name + ", " + patronymic);
		in.close();
	}
}
