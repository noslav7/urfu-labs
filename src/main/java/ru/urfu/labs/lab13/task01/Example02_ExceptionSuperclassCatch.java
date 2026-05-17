package ru.urfu.labs.lab13.task01;

/**
 * Пример 2. Исключение перехвачено перехватчиком предка.
 * <p>
 * В методичке после throw указан {@code System.out.println("1")} — этот код недостижим
 * и не компилируется в Java, поэтому строка приведена в комментарии.
 */
public class Example02_ExceptionSuperclassCatch {

    public static void main(String[] args) {
        try {
            System.out.println("0");
            throw new RuntimeException("Непроверяемая ошибка");
            // System.out.println("1");
        } catch (Exception e) {
            System.out.println("2 " + e);
        }
        System.out.println("3");
    }
}
