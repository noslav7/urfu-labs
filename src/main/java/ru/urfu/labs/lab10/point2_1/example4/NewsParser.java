package ru.urfu.labs.lab10.point2_1.example4;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.urfu.labs.lab10.ConsoleEncodingUtil;

import java.io.IOException;
import java.util.Locale;

/**
 * Загружает страницу ФТИ УрФУ, пытается извлечь новости и выводит их заголовки и даты.
 */
public class NewsParser {

    /**
     * Загружает страницу новостей, извлекает заголовки и даты, либо выводит подсказки при изменении верстки.
     */
    public static void main(String[] args) {
        ConsoleEncodingUtil.ensureUtf8Console();
        try {
            Document doc = Jsoup.connect("http://fat.urfu.ru/index.html").get();

            Elements newsRows = doc.select(
                    "body > table > tbody > tr > td > div > table > tbody > tr:nth-child(5) > td:nth-child(3) > table > tbody > tr > td:nth-child(1)"
            );

            for (int i = 3; i < Math.min(newsRows.size(), 20); i++) {
                if (i % 2 == 0) {
                    Element row = newsRows.get(i);
                    String title = row.getElementsByClass("blocktitle").get(0).text();
                    String date = row.getElementsByClass("blockdate").get(0).text();

                    System.out.println("Тема : " + title);
                    System.out.println("Дата : " + date);
                    System.out.println();
                }
            }

            if (newsRows.isEmpty()) {
                System.out.println("Новости не найдены по исходному CSS-селектору.");
                System.out.println("Похоже, структура страницы изменилась.");
                System.out.println("Ссылка для проверки: http://fat.urfu.ru/index.html");

                Elements links = doc.select("a[href]");
                int printed = 0;
                for (Element link : links) {
                    String text = link.text().trim();
                    if (!text.isEmpty() && text.toLowerCase(Locale.ROOT).contains("нов")) {
                        System.out.println("Найден похожий пункт: " + text);
                        printed++;
                    }
                    if (printed >= 10) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
