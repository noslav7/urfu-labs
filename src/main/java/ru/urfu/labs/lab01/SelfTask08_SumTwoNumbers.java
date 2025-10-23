package ru.urfu.labs.lab01;

import java.util.Scanner;

public class SelfTask08_SumTwoNumbers {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		double a = in.nextDouble();
		double b = in.nextDouble();
		double sum = a + b;
		System.out.println("Sum: " + sum);
		in.close();
	}
}
