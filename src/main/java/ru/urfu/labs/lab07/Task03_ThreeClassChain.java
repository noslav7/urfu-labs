package ru.urfu.labs.lab07;

/**
 * Задание 3: Цепочка наследования из трех классов
 * Каждый класс добавляет новое поле и перегружает метод присваивания
 */
class FirstClass03 {
    public int intField;

    public FirstClass03(int intField) {
        this.intField = intField;
    }

    public void setValue(int intField) {
        this.intField = intField;
    }

    @Override
    public String toString() {
        return "FirstClass03{intField=" + intField + "}";
    }
}

class SecondClass03 extends FirstClass03 {
    public char charField;

    public SecondClass03(int intField, char charField) {
        super(intField);
        this.charField = charField;
    }

    public void setValue(int intField, char charField) {
        super.setValue(intField);
        this.charField = charField;
    }

    @Override
    public String toString() {
        return "SecondClass03{intField=" + intField + ", charField='" + charField + "'}";
    }
}

class ThirdClass03 extends SecondClass03 {
    public String textField;

    public ThirdClass03(int intField, char charField, String textField) {
        super(intField, charField);
        this.textField = textField;
    }

    public void setValue(int intField, char charField, String textField) {
        super.setValue(intField, charField);
        this.textField = textField;
    }

    @Override
    public String toString() {
        return "ThirdClass03{intField=" + intField + ", charField='" + charField + "', textField='" + textField + "'}";
    }
}

public class Task03_ThreeClassChain {
    public static void main(String[] args) {
        FirstClass03 first = new FirstClass03(10);
        System.out.println(first.toString());
        first.setValue(20);
        System.out.println("После изменения: " + first.toString());

        SecondClass03 second = new SecondClass03(100, 'A');
        System.out.println(second.toString());
        second.setValue(200, 'B');
        System.out.println("После изменения: " + second.toString());

        ThirdClass03 third = new ThirdClass03(1000, 'X', "Текст");
        System.out.println(third.toString());
        third.setValue(2000, 'Y', "Новый текст");
        System.out.println("После изменения: " + third.toString());
    }
}

