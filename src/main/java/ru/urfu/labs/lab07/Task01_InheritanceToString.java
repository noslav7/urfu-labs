package ru.urfu.labs.lab07;

/**
 * Задание 1: Наследование с переопределением toString()
 * Суперкласс с приватным текстовым полем и подкласс с дополнительным полем
 */
class SuperClass01 {
    private String textField;

    public SuperClass01(String textField) {
        this.textField = textField;
    }

    protected String getTextField() {
        return textField;
    }

    @Override
    public String toString() {
        return "SuperClass01{textField='" + textField + "'}";
    }
}

class SubClass01 extends SuperClass01 {
    private String additionalTextField;

    public SubClass01(String textField) {
        super(textField);
        this.additionalTextField = "";
    }

    public SubClass01(String textField, String additionalTextField) {
        super(textField);
        this.additionalTextField = additionalTextField;
    }

    @Override
    public String toString() {
        return "SubClass01{textField='" + getTextField() + "', additionalTextField='" + additionalTextField + "'}";
    }
}

public class Task01_InheritanceToString {
    public static void main(String[] args) {
        SuperClass01 superObj = new SuperClass01("Текст из суперкласса");
        System.out.println(superObj.toString());

        SubClass01 subObj1 = new SubClass01("Текст из подкласса");
        System.out.println(subObj1.toString());

        SubClass01 subObj2 = new SubClass01("Первый текст", "Второй текст");
        System.out.println(subObj2.toString());
    }
}

