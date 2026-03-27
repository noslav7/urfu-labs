package ru.urfu.labs.lab10.point2_1.example3;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Создаёт Excel-файл с демонстрационной таблицей товаров и сохраняет его в resources.
 */
public class WriteExcelFileExample {

    private static final Path XLSX_PATH = Path.of(
            "src", "main", "resources", "lab10", "point2_1", "example3", "example3.xlsx"
    );

    /**
     * Создаёт книгу Excel, заполняет лист "Товары" и сохраняет файл на диск.
     */
    public static void main(String[] args) throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Товары");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Товар");
            headerRow.createCell(1).setCellValue("Характеристики");
            headerRow.createCell(2).setCellValue("Стоимость");

            Row dataRow1 = sheet.createRow(1);
            dataRow1.createCell(0).setCellValue("Книга");
            dataRow1.createCell(1).setCellValue("Жанр: Фантастика, Автор: Иванов И.И.");
            dataRow1.createCell(2).setCellValue(500.0);

            Row dataRow2 = sheet.createRow(2);
            dataRow2.createCell(0).setCellValue("Компьютер");
            dataRow2.createCell(1).setCellValue("Процессор: Intel Core i5, Оперативная память: 8 Гб");
            dataRow2.createCell(2).setCellValue(25000.0);

            Files.createDirectories(XLSX_PATH.getParent());
            try (FileOutputStream outputStream = new FileOutputStream(XLSX_PATH.toFile())) {
                workbook.write(outputStream);
            }
        }

        System.out.println("Данные записаны в файл: " + XLSX_PATH);
    }
}
