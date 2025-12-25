package ru.urfu.labs.lab07;

/**
 * Задание 5: Полиморфизм с двумя подклассами
 * Суперкласс и два подкласса с переопределением метода отображения
 */
class SuperClass05 {
    private String textField;

    public SuperClass05(String textField) {
        this.textField = textField;
    }

    protected String getTextField() {
        return textField;
    }

    public void display() {
        System.out.println("Название класса: " + this.getClass().getSimpleName());
        System.out.println("Значение поля: " + textField);
    }
}

class FirstSubClass05 extends SuperClass05 {
    protected int intField;

    public FirstSubClass05(String textField, int intField) {
        super(textField);
        this.intField = intField;
    }

    @Override
    public void display() {
        System.out.println("Название класса: " + this.getClass().getSimpleName());
        System.out.println("Текстовое поле: " + getTextField());
        System.out.println("Целочисленное поле: " + intField);
    }
}

class SecondSubClass05 extends SuperClass05 {
    protected char charField;

    public SecondSubClass05(String textField, char charField) {
        super(textField);
        this.charField = charField;
    }

    @Override
    public void display() {
        System.out.println("Название класса: " + this.getClass().getSimpleName());
        System.out.println("Текстовое поле: " + getTextField());
        System.out.println("Символьное поле: " + charField);
    }
}

public class Task05_Polymorphism {
    public static void main(String[] args) {
        // Создание объектов каждого класса
        SuperClass05 superObj = new SuperClass05("Текст суперкласса");
        FirstSubClass05 firstSubObj = new FirstSubClass05("Текст первого подкласса", 100);
        SecondSubClass05 secondSubObj = new SecondSubClass05("Текст второго подкласса", 'Z');

        System.out.println("=== Вызов метода через объект суперкласса ===");
        superObj.display();

        System.out.println("\n=== Вызов метода через объект первого подкласса ===");
        firstSubObj.display();

        System.out.println("\n=== Вызов метода через объект второго подкласса ===");
        secondSubObj.display();

        // Полиморфизм: вызов метода через объектную переменную суперкласса
        System.out.println("\n=== Полиморфизм: вызов через переменную суперкласса ===");
        SuperClass05 ref1 = new FirstSubClass05("Полиморфный текст 1", 200);
        ref1.display();

        SuperClass05 ref2 = new SecondSubClass05("Полиморфный текст 2", 'M');
        ref2.display();
    }
}

