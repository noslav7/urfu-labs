package ru.urfu.labs.lab01;

import java.util.Scanner;

public class SelfTask09_SequenceAndSquare {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int a = n - 1;
		int b = n;
		int c = n + 1;
		int sum = a + b + c;
		long d = (long) sum * (long) sum;
		System.out.println(a + " " + b + " " + c + " " + d);
		in.close();
	}
}
