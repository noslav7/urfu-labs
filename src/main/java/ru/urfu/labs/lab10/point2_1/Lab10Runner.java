package ru.urfu.labs.lab10.point2_1;

import ru.urfu.labs.lab10.ConsoleEncodingUtil;
import ru.urfu.labs.lab10.point2_1.example1.CreateXmlFile;
import ru.urfu.labs.lab10.point2_1.example1.XmlParser;
import ru.urfu.labs.lab10.point2_1.example2.JsonCreator;
import ru.urfu.labs.lab10.point2_1.example2.JsonParser;
import ru.urfu.labs.lab10.point2_1.example3.ReadExcelFileExample;
import ru.urfu.labs.lab10.point2_1.example3.WriteExcelFileExample;
import ru.urfu.labs.lab10.point2_1.example4.NewsParser;
import ru.urfu.labs.lab10.point2_1.example5.LinkParser;

/**
 * Запускает все примеры из лабораторной работы 10 и выводит результат по разделам.
 */
public class Lab10Runner {

    /**
     * Последовательно запускает примеры по XML, JSON, Excel и HTML-парсингу.
     */
    public static void main(String[] args) {
        ConsoleEncodingUtil.ensureUtf8Console();
        run("1.2.1 Создание XML", () -> CreateXmlFile.main(new String[0]));
        run("1.2.2 Чтение XML", () -> XmlParser.main(new String[0]));

        run("1.3.2 Создание JSON", () -> JsonCreator.main(new String[0]));
        run("1.3.3 Чтение JSON", () -> JsonParser.main(new String[0]));

        run("1.5.2 Создание Excel", () -> WriteExcelFileExample.main(new String[0]));
        run("1.5.3 Чтение Excel", () -> ReadExcelFileExample.main(new String[0]));

        run("1.4.2 Парсинг ссылок (itlearn)", () -> LinkParser.main(new String[0]));
        run("1.4.3 Парсинг новостей (fat.urfu)", () -> NewsParser.main(new String[0]));
    }

    /**
     * Выполняет один пример, печатает заголовок раздела и обрабатывает возможные ошибки.
     *
     * @param title  заголовок выполняемого примера
     * @param action действие, которое нужно запустить
     */
    private static void run(String title, ThrowingRunnable action) {
        System.out.println("\n==================================================");
        System.out.println(title);
        System.out.println("==================================================");
        try {
            action.run();
        } catch (Exception e) {
            System.out.println("Ошибка при выполнении: " + title);
            e.printStackTrace();
        }
    }

    /**
     * Функциональный интерфейс для запуска примера с возможностью выброса checked-исключений.
     */
    @FunctionalInterface
    private interface ThrowingRunnable {
        /**
         * Выполняет действие примера.
         *
         * @throws Exception любая ошибка во время выполнения примера
         */
        void run() throws Exception;
    }
}
