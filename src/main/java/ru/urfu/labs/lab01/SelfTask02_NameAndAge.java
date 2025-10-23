package ru.urfu.labs.lab01;

import java.util.Scanner;

public class SelfTask02_NameAndAge {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String name = in.nextLine();
		int age = in.nextInt();
		System.out.println("Name: " + name + ", Age: " + age);
		in.close();
	}
}
