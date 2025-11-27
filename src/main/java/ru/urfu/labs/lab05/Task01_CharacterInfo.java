package ru.urfu.labs.lab05;

import java.util.Scanner;

public class Task01_CharacterInfo {

    private char character;

    public void setCharacter(char character) {
        this.character = character;
    }

    public int getCharacterCode() {
        return character;
    }

    public void printCharacterInfo() {
        System.out.println("Символ: " + character + ", код: " + (int) character);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите символ: ");
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            System.out.println("Вы не ввели символ.");
            scanner.close();
            return;
        }

        char ch = input.charAt(0);

        Task01_CharacterInfo info = new Task01_CharacterInfo();
        info.setCharacter(ch);

        System.out.println("Код символа: " + info.getCharacterCode());
        info.printCharacterInfo();

        scanner.close();
    }
}


