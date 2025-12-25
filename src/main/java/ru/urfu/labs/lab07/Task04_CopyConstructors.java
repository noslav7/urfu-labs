package ru.urfu.labs.lab07;

/**
 * Задание 4: Цепочка наследования с конструкторами копирования
 * Три класса с конструкторами на основе значений полей и конструкторами копирования
 */
class FirstClass04 {
    public char charField;

    public FirstClass04(char charField) {
        this.charField = charField;
    }

    // Конструктор копирования
    public FirstClass04(FirstClass04 other) {
        this.charField = other.charField;
    }
}

class SecondClass04 extends FirstClass04 {
    public String textField;

    public SecondClass04(char charField, String textField) {
        super(charField);
        this.textField = textField;
    }

    // Конструктор копирования
    public SecondClass04(SecondClass04 other) {
        super(other.charField);
        this.textField = other.textField;
    }
}

class ThirdClass04 extends SecondClass04 {
    public int intField;

    public ThirdClass04(char charField, String textField, int intField) {
        super(charField, textField);
        this.intField = intField;
    }

    // Конструктор копирования
    public ThirdClass04(ThirdClass04 other) {
        super(other.charField, other.textField);
        this.intField = other.intField;
    }

    @Override
    public String toString() {
        return "ThirdClass04{charField='" + charField + "', textField='" + textField + "', intField=" + intField + "}";
    }
}

public class Task04_CopyConstructors {
    public static void main(String[] args) {
        // Создание объектов через конструкторы с параметрами
        FirstClass04 first1 = new FirstClass04('A');
        System.out.println("FirstClass04: charField='" + first1.charField + "'");

        SecondClass04 second1 = new SecondClass04('B', "Текст");
        System.out.println("SecondClass04: charField='" + second1.charField + "', textField='" + second1.textField + "'");

        ThirdClass04 third1 = new ThirdClass04('C', "Пример", 42);
        System.out.println(third1.toString());

        // Создание копий через конструкторы копирования
        FirstClass04 first2 = new FirstClass04(first1);
        System.out.println("\nКопия FirstClass04: charField='" + first2.charField + "'");

        SecondClass04 second2 = new SecondClass04(second1);
        System.out.println("Копия SecondClass04: charField='" + second2.charField + "', textField='" + second2.textField + "'");

        ThirdClass04 third2 = new ThirdClass04(third1);
        System.out.println("Копия ThirdClass04: " + third2.toString());

        // Изменение оригинала и проверка, что копия не изменилась
        third1.intField = 100;
        System.out.println("\nПосле изменения оригинала:");
        System.out.println("Оригинал: " + third1.toString());
        System.out.println("Копия: " + third2.toString());
    }
}

