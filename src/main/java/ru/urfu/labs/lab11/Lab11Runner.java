package ru.urfu.labs.lab11;

/**
 * Запускает все задания лабораторной работы 11 по очереди.
 */
public class Lab11Runner {

    public static void main(String[] args) {
        run("Задание 1: фильтрация четных чисел", () -> Task01_FilterEvenNumbers.main(new String[0]));
        run("Задание 2: пересечение массивов", () -> Task02_IntersectionOfArrays.main(new String[0]));
        run("Задание 3: строки с заглавной буквы", () -> Task03_FilterCapitalizedStrings.main(new String[0]));
        run("Задание 4: квадраты чисел", () -> Task04_SquareNumbers.main(new String[0]));
        run("Задание 5: фильтрация по подстроке", () -> Task05_FilterStringsBySubstring.main(new String[0]));
        run("Задание 6: числа, делящиеся без остатка", () -> Task06_FilterDivisibleNumbers.main(new String[0]));
        run("Задание 7: строки длиннее заданного значения", () -> Task07_FilterStringsByLength.main(new String[0]));
        run("Задание 8: числа больше заданного значения", () -> Task08_FilterGreaterThanValue.main(new String[0]));
        run("Задание 9: строки только из букв", () -> Task09_FilterAlphabeticStrings.main(new String[0]));
        run("Задание 10: числа меньше заданного значения", () -> Task10_FilterLessThanValue.main(new String[0]));
    }

    private static void run(String title, Runnable action) {
        System.out.println("\n==================================================");
        System.out.println(title);
        System.out.println("==================================================");
        action.run();
    }
}
