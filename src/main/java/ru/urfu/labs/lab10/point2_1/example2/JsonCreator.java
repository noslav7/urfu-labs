package ru.urfu.labs.lab10.point2_1.example2;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.urfu.labs.lab10.ConsoleEncodingUtil;

import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Формирует JSON-файл с данными о книгах и сохраняет его в resources.
 */
public class JsonCreator {

    private static final Path JSON_PATH = Path.of(
            "src", "main", "resources", "lab10", "point2_1", "example2", "example-json.json"
    );

    /**
     * Создаёт JSON-объект библиотеки и сохраняет его в файл.
     */
    public static void main(String[] args) {
        ConsoleEncodingUtil.ensureUtf8Console();
        JSONObject library = new JSONObject();
        JSONArray books = new JSONArray();

        JSONObject book1 = new JSONObject();
        book1.put("title", "Война и мир");
        book1.put("author", "Лев Толстой");
        book1.put("year", 1869);

        JSONObject book2 = new JSONObject();
        book2.put("title", "Мастер и Маргарита");
        book2.put("author", "Михаил Булгаков");
        book2.put("year", 1967);

        books.add(book1);
        books.add(book2);

        library.put("books", books);

        try {
            Files.createDirectories(JSON_PATH.getParent());
            try (FileWriter file = new FileWriter(JSON_PATH.toFile(), StandardCharsets.UTF_8)) {
                file.write(library.toJSONString());
            }
            System.out.println("JSON-файл успешно создан: " + JSON_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
