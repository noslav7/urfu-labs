package ru.urfu.labs.lab08;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Задание 3: из текста выбрать слова, начинающиеся с согласных,
 * указав номер исходной строки и количество найденных слов.
 */
public class Task03_ConsonantWords {

    private static final Path BASE_DIR = Paths.get("lab08_data", "task03");
    private static final Path SOURCE_FILE = BASE_DIR.resolve("source.txt");
    private static final Path RESULT_FILE = BASE_DIR.resolve("consonants.txt");
    private static final String CONSONANTS = "бвгджзйклмнпрстфхцчшщ";
    private static final Locale RU_LOCALE = Locale.forLanguageTag("ru-RU");

    public static void main(String[] args) throws IOException {
        Files.createDirectories(BASE_DIR);
        ensureSourcePrepared();
        processText();
        printResult();
    }

    private static void ensureSourcePrepared() throws IOException {
        if (!Files.exists(SOURCE_FILE)) {
            List<String> lines = List.of(
                    "Свободы сеятель пустынный,",
                    "Я вышел рано, до звезды;",
                    "Рукою чистой и безвинной",
                    "В порабощённые бразды",
                    "Бросал живительное семя —",
                    "Но потерял я только время,",
                    "Благие мысли и труды."
            );
            Files.write(SOURCE_FILE, lines, StandardCharsets.UTF_8);
        }
    }

    private static void processText() throws IOException {
        Files.deleteIfExists(RESULT_FILE);
        try (var reader = Files.newBufferedReader(SOURCE_FILE, StandardCharsets.UTF_8);
             var writer = Files.newBufferedWriter(RESULT_FILE, StandardCharsets.UTF_8)) {

            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                List<String> consonantWords = collectConsonantWords(line);
                writer.write("Строка " + lineNumber + " (" + consonantWords.size() + " слов): ");
                writer.write(String.join(" ", consonantWords));
                writer.newLine();
            }
            writer.flush();
        }
    }

    private static List<String> collectConsonantWords(String line) {
        String[] words = line.split("\\s+");
        List<String> result = new ArrayList<>();
        for (String word : words) {
            String clean = clearWord(word);
            if (!clean.isEmpty() && startsWithConsonant(clean)) {
                result.add(clean);
            }
        }
        return result;
    }

    private static String clearWord(String word) {
        String trimmed = word.replaceAll("^[^\\p{L}]+|[^\\p{L}]+$", "");
        return trimmed.toLowerCase(RU_LOCALE);
    }

    private static boolean startsWithConsonant(String word) {
        char first = word.charAt(0);
        return CONSONANTS.indexOf(first) >= 0;
    }

    private static void printResult() throws IOException {
        System.out.println("Содержимое файла результатов: " + RESULT_FILE.toAbsolutePath());
        for (String line : Files.readAllLines(RESULT_FILE, StandardCharsets.UTF_8)) {
            System.out.println(line);
        }
    }
}
