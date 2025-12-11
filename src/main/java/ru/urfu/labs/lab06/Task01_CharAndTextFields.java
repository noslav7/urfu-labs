package ru.urfu.labs.lab06;

public class Task01_CharAndTextFields {

    private char charValue;
    private boolean charInitialized;
    private String textValue;

    public void setValue(char value) {
        charValue = value;
        charInitialized = true;
    }

    public void setValue(String value) {
        textValue = value;
    }

    public void setValue(char[] values) {
        if (values == null || values.length == 0) {
            return;
        }
        if (values.length == 1) {
            setValue(values[0]);
        } else {
            setValue(new String(values));
        }
    }

    public void printValues() {
        if (charInitialized) {
            System.out.println("Символьное поле: " + charValue);
        } else {
            System.out.println("Символьное поле: (не задано)");
        }
        if (textValue != null) {
            System.out.println("Текстовое поле: " + textValue);
        } else {
            System.out.println("Текстовое поле: (не задано)");
        }
    }

    public static void main(String[] args) {
        Task01_CharAndTextFields data = new Task01_CharAndTextFields();

        data.printValues();

        data.setValue('A');
        data.printValues();

        data.setValue("Привет, мир!");
        data.printValues();

        data.setValue(new char[] {'J', 'a', 'v', 'a'});
        data.printValues();

        data.setValue(new char[] {'Z'});
        data.printValues();
    }
}



