package ru.urfu.labs.lab10.point2_4;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import ru.urfu.labs.lab10.ConsoleEncodingUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Парсер новостей с сохранением результата в файл и повторными попытками подключения.
 */
public class Point2_4NewsParser {

    private static final String NEWS_URL = "http://fat.urfu.ru/index.html";
    private static final int MAX_ATTEMPTS = 3;
    private static final int RETRY_DELAY_MS = 2000;

    private static final Path OUTPUT_PATH = Path.of(
            "src", "main", "resources", "lab10", "point2_4", "news-log.txt"
    );

    /**
     * Загружает страницу с retry-логикой, парсит новости и сохраняет их в файл.
     */
    public static void main(String[] args) {
        ConsoleEncodingUtil.ensureUtf8Console();
        try {
            Document doc = fetchWithRetry(NEWS_URL, MAX_ATTEMPTS, RETRY_DELAY_MS);
            List<NewsItem> newsItems = parseNews(doc);

            printNews(newsItems);
            saveToFile(newsItems);

            System.out.println("Данные сохранены в файл: " + OUTPUT_PATH);
        } catch (Exception e) {
            System.out.println("Не удалось получить и обработать новости: " + e.getMessage());
        }
    }

    private static Document fetchWithRetry(String url, int maxAttempts, int delayMs) throws Exception {
        IOException lastError = null;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                System.out.printf("Попытка подключения %d/%d...%n", attempt, maxAttempts);
                return Jsoup.connect(url)
                        .timeout(10_000)
                        .get();
            } catch (IOException e) {
                lastError = e;
                System.out.println("Ошибка получения HTML: " + e.getMessage());
                if (attempt < maxAttempts) {
                    System.out.println("Повторная попытка через " + (delayMs / 1000.0) + " сек.");
                    Thread.sleep(delayMs);
                }
            }
        }

        throw new IOException("Сайт недоступен после " + maxAttempts + " попыток", lastError);
    }

    private static List<NewsItem> parseNews(Document doc) {
        List<NewsItem> result = new ArrayList<>();

        Elements titles = doc.getElementsByClass("blocktitle");
        Elements dates = doc.getElementsByClass("blockdate");

        int count = Math.min(titles.size(), dates.size());
        for (int i = 0; i < count; i++) {
            String title = titles.get(i).text().trim();
            String date = dates.get(i).text().trim();
            if (!title.isEmpty()) {
                result.add(new NewsItem(title, date));
            }
        }

        return result;
    }

    private static void printNews(List<NewsItem> items) {
        if (items.isEmpty()) {
            System.out.println("Новости не найдены. Возможно, структура страницы изменилась.");
            return;
        }

        for (NewsItem item : items) {
            System.out.println("Тема : " + item.title());
            System.out.println("Дата : " + item.date());
            System.out.println();
        }
    }

    private static void saveToFile(List<NewsItem> items) throws Exception {
        Files.createDirectories(OUTPUT_PATH.getParent());

        List<String> lines = new ArrayList<>();
        lines.add("Источник: " + NEWS_URL);
        lines.add("Время сохранения: " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        lines.add("Количество новостей: " + items.size());
        lines.add("--------------------------------------------------");

        if (items.isEmpty()) {
            lines.add("Новости не найдены.");
        } else {
            for (int i = 0; i < items.size(); i++) {
                NewsItem item = items.get(i);
                lines.add((i + 1) + ". " + item.title());
                lines.add("   Дата: " + item.date());
            }
        }

        Files.write(OUTPUT_PATH, lines, StandardCharsets.UTF_8);
    }

    private record NewsItem(String title, String date) {
    }
}
