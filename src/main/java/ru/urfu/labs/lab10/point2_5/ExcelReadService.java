package ru.urfu.labs.lab10.point2_5;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Сервис безопасного чтения Excel с подробной диагностикой ошибок.
 */
public class ExcelReadService {

    /**
     * Читает лист Excel и возвращает его строки в виде текста.
     */
    public List<String> readSheet(Path filePath, String sheetName) throws ExcelReadException {
        validatePath(filePath);
        validateExtension(filePath);

        try (FileInputStream inputStream = new FileInputStream(filePath.toFile());
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw sheetNotFoundException(sheetName, workbook);
            }

            List<String> lines = new ArrayList<>();
            for (Row row : sheet) {
                StringBuilder rowText = new StringBuilder();
                for (Cell cell : row) {
                    if (!rowText.isEmpty()) {
                        rowText.append('\t');
                    }
                    rowText.append(cell);
                }
                lines.add(rowText.toString());
            }
            return lines;
        } catch (ExcelReadException e) {
            throw e;
        } catch (IOException e) {
            throw new ExcelReadException(
                    "Ошибка чтения файла Excel: " + e.getMessage(),
                    "Проверьте, что файл не открыт в другой программе и доступен для чтения."
            );
        } catch (RuntimeException e) {
            throw new ExcelReadException(
                    "Неверный формат Excel или поврежденный файл: " + e.getMessage(),
                    "Убедитесь, что это корректный .xlsx файл. При необходимости пересохраните файл."
            );
        }
    }

    private void validatePath(Path filePath) throws ExcelReadException {
        if (!Files.exists(filePath)) {
            throw new ExcelReadException(
                    "Файл не найден: " + filePath,
                    "Проверьте путь к файлу и повторите запуск."
            );
        }
        if (!Files.isRegularFile(filePath)) {
            throw new ExcelReadException(
                    "Указанный путь не является файлом: " + filePath,
                    "Укажите путь именно к файлу .xlsx."
            );
        }
    }

    private void validateExtension(Path filePath) throws ExcelReadException {
        String fileName = filePath.getFileName().toString().toLowerCase(Locale.ROOT);
        if (!fileName.endsWith(".xlsx")) {
            throw new ExcelReadException(
                    "Неподдерживаемый формат файла: " + fileName,
                    "Используйте файл формата .xlsx."
            );
        }
    }

    private ExcelReadException sheetNotFoundException(String sheetName, XSSFWorkbook workbook) {
        List<String> availableSheets = new ArrayList<>();
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            availableSheets.add(workbook.getSheetName(i));
        }
        return new ExcelReadException(
                "Лист '" + sheetName + "' не найден в файле.",
                "Доступные листы: " + availableSheets
        );
    }

    /**
     * Ошибка чтения Excel с рекомендацией по исправлению.
     */
    public static class ExcelReadException extends Exception {
        private final String recommendation;

        public ExcelReadException(String message, String recommendation) {
            super(message);
            this.recommendation = recommendation;
        }

        public String getRecommendation() {
            return recommendation;
        }
    }
}
