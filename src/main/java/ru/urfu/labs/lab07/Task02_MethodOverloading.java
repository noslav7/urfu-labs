package ru.urfu.labs.lab07;

/**
 * Задание 2: Наследование с переопределением и перегрузкой методов
 * Суперкласс с методом присваивания и подкласс с перегрузкой методов
 */
class SuperClass02 {
    private String textField;

    public SuperClass02(String textField) {
        this.textField = textField;
    }

    public void setValue(String textField) {
        this.textField = textField;
    }

    public int getLength() {
        return textField != null ? textField.length() : 0;
    }
}

class SubClass02 extends SuperClass02 {
    public int intField;

    public SubClass02(int intField, String textField) {
        super(textField);
        this.intField = intField;
    }

    // Переопределение метода без параметров
    public void setValue() {
        super.setValue("");
        this.intField = 0;
    }

    // Переопределение метода с текстовым параметром
    @Override
    public void setValue(String textField) {
        super.setValue(textField);
    }

    // Перегрузка метода с целочисленным параметром
    public void setValue(int intField) {
        this.intField = intField;
    }

    // Перегрузка метода с текстовым и целочисленным параметром
    public void setValue(String textField, int intField) {
        super.setValue(textField);
        this.intField = intField;
    }
}

public class Task02_MethodOverloading {
    public static void main(String[] args) {
        SuperClass02 superObj = new SuperClass02("Исходный текст");
        System.out.println("Длина текста в суперклассе: " + superObj.getLength());

        SubClass02 subObj = new SubClass02(10, "Текст из подкласса");
        System.out.println("Исходные значения: intField=" + subObj.intField + ", длина текста=" + subObj.getLength());

        subObj.setValue();
        System.out.println("После setValue(): intField=" + subObj.intField + ", длина текста=" + subObj.getLength());

        subObj.setValue("Новый текст");
        System.out.println("После setValue(String): intField=" + subObj.intField + ", длина текста=" + subObj.getLength());

        subObj.setValue(20);
        System.out.println("После setValue(int): intField=" + subObj.intField + ", длина текста=" + subObj.getLength());

        subObj.setValue("Финальный текст", 30);
        System.out.println("После setValue(String, int): intField=" + subObj.intField + ", длина текста=" + subObj.getLength());
    }
}

