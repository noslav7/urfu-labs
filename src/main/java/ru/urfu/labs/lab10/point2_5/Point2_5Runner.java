package ru.urfu.labs.lab10.point2_5;

import ru.urfu.labs.lab10.ConsoleEncodingUtil;
import ru.urfu.labs.lab10.point2_5.ExcelReadService.ExcelReadException;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

/**
 * Интерактивная программа чтения Excel с улучшенной обработкой ошибок.
 */
public class Point2_5Runner {

    private static final Path DEFAULT_PATH = Path.of(
            "src", "main", "resources", "lab10", "point2_1", "example3", "example3.xlsx"
    );
    private static final String DEFAULT_SHEET = "Товары";

    /**
     * Запускает цикл чтения Excel с возможностью повторного запуска после исправления ошибок.
     */
    public static void main(String[] args) {
        ConsoleEncodingUtil.ensureUtf8Console();
        ExcelReadService service = new ExcelReadService();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n===== Excel Parser point2_5 =====");
                System.out.println("Нажмите Enter для значения по умолчанию.");
                System.out.println("Файл по умолчанию: " + DEFAULT_PATH);
                System.out.println("Лист по умолчанию: " + DEFAULT_SHEET);

                System.out.print("Путь к .xlsx файлу (или 'exit'): ");
                String fileInput = scanner.nextLine().trim();
                if ("exit".equalsIgnoreCase(fileInput)) {
                    System.out.println("Выход из программы.");
                    return;
                }
                Path targetPath = fileInput.isEmpty() ? DEFAULT_PATH : Path.of(fileInput);

                System.out.print("Название листа: ");
                String sheetInput = scanner.nextLine().trim();
                String targetSheet = sheetInput.isEmpty() ? DEFAULT_SHEET : sheetInput;

                try {
                    List<String> lines = service.readSheet(targetPath, targetSheet);
                    System.out.println("\nЧтение успешно. Строки листа:");
                    lines.forEach(System.out::println);
                } catch (ExcelReadException e) {
                    System.out.println("\nОшибка: " + e.getMessage());
                    System.out.println("Рекомендация: " + e.getRecommendation());
                }

                System.out.print("\nЗапустить чтение снова? (y/n): ");
                String again = scanner.nextLine().trim();
                if (!"y".equalsIgnoreCase(again)) {
                    System.out.println("Выход из программы.");
                    return;
                }
            }
        }
    }
}
