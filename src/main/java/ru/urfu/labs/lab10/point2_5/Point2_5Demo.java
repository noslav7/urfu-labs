package ru.urfu.labs.lab10.point2_5;

import ru.urfu.labs.lab10.ConsoleEncodingUtil;
import ru.urfu.labs.lab10.point2_5.ExcelReadService.ExcelReadException;

import java.nio.file.Path;
import java.util.List;

/**
 * Небольшая автоматическая проверка обработчика ошибок и успешного чтения Excel.
 */
public class Point2_5Demo {

    private static final ExcelReadService SERVICE = new ExcelReadService();

    /**
     * Демонстрирует 3 сценария: успех, отсутствующий лист и неверное расширение файла.
     */
    public static void main(String[] args) {
        ConsoleEncodingUtil.ensureUtf8Console();

        Path validPath = Path.of("src", "main", "resources", "lab10", "point2_1", "example3", "example3.xlsx");
        runSuccessScenario(validPath, "Товары");
        runErrorScenario(validPath, "НесуществующийЛист");
        runErrorScenario(Path.of("src", "main", "resources", "lab10", "point2_3", "animals.json"), "Товары");
    }

    private static void runSuccessScenario(Path path, String sheetName) {
        try {
            List<String> lines = SERVICE.readSheet(path, sheetName);
            System.out.println("SUCCESS: файл прочитан, строк: " + lines.size());
        } catch (ExcelReadException e) {
            System.out.println("UNEXPECTED ERROR: " + e.getMessage());
        }
    }

    private static void runErrorScenario(Path path, String sheetName) {
        try {
            SERVICE.readSheet(path, sheetName);
            System.out.println("UNEXPECTED SUCCESS: ожидалась ошибка для " + path);
        } catch (ExcelReadException e) {
            System.out.println("ERROR: " + e.getMessage());
            System.out.println("HINT: " + e.getRecommendation());
        }
    }
}
