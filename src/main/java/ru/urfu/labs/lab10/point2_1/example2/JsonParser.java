package ru.urfu.labs.lab10.point2_1.example2;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import ru.urfu.labs.lab10.ConsoleEncodingUtil;

import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

/**
 * Разбирает JSON-файл библиотеки и печатает содержимое массива книг.
 */
public class JsonParser {

    private static final Path JSON_PATH = Path.of(
            "src", "main", "resources", "lab10", "point2_1", "example2", "example-json.json"
    );

    /**
     * Читает JSON-файл библиотеки и печатает в консоль каждую книгу.
     */
    public static void main(String[] args) {
        ConsoleEncodingUtil.ensureUtf8Console();
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(JSON_PATH.toFile(), StandardCharsets.UTF_8));

            JSONObject jsonObject = (JSONObject) obj;
            System.out.println("Корневой элемент: " + jsonObject.keySet().iterator().next());

            JSONArray jsonArray = (JSONArray) jsonObject.get("books");
            for (Object o : jsonArray) {
                JSONObject book = (JSONObject) o;
                System.out.println("\nТекущий элемент: book");
                System.out.println("Название книги: " + book.get("title"));
                System.out.println("Автор: " + book.get("author"));
                System.out.println("Год издания: " + book.get("year"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
