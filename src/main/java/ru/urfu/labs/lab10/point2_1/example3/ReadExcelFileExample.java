package ru.urfu.labs.lab10.point2_1.example3;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.urfu.labs.lab10.ConsoleEncodingUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Открывает Excel-файл и выводит все ячейки листа "Товары" в консоль.
 */
public class ReadExcelFileExample {

    private static final Path XLSX_PATH = Path.of(
            "src", "main", "resources", "lab10", "point2_1", "example3", "example3.xlsx"
    );

    /**
     * Открывает Excel-файл и построчно выводит содержимое листа "Товары".
     */
    public static void main(String[] args) throws IOException {
        ConsoleEncodingUtil.ensureUtf8Console();
        try (FileInputStream inputStream = new FileInputStream(XLSX_PATH.toFile());
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {

            XSSFSheet sheet = workbook.getSheet("Товары");

            for (Row row : sheet) {
                for (Cell cell : row) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            }
        }
    }
}
