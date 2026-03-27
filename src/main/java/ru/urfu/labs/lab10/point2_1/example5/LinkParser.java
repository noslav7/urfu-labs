package ru.urfu.labs.lab10.point2_1.example5;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Подключается к учебной странице и выводит в консоль все найденные абсолютные ссылки.
 */
public class LinkParser {

    /**
     * Загружает веб-страницу и печатает абсолютные URL всех ссылок.
     */
    public static void main(String[] args) {
        String url = "https://itlearn.ru/first-steps";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("a[href]");

            for (Element link : links) {
                System.out.println(link.attr("abs:href"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
