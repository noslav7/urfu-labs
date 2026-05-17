package ru.urfu.labs.lab13.task01;

/**
 * Пример 6. Последовательность перехвата должна соответствовать иерархии классов исключений.
 * Предок не должен перехватывать исключения раньше потомков.
 * <p>
 * Указанный в методичке пример выдаёт ошибку компилятора — программу запустить невозможно.
 * Исходный код приведён в комментарии ниже.
 */
public class Example06_InvalidCatchOrder {

    /*
    public static void main(String[] args) {
        try {
            System.out.println("0");
            throw new NullPointerException("ошибка");
        } catch (ArithmeticException e) {
            System.out.println("1");
        } catch (Exception e) {
            System.out.println("2");
        } catch (RuntimeException e) {
            System.out.println("3");
        }
        System.out.println("4");
    }
    */

    public static void main(String[] args) {
        System.out.println("Пример 6: код из методички не компилируется "
                + "(catch Exception перед catch RuntimeException).");
    }
}
