package ru.urfu.labs.lab10;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * Утилита для корректного UTF-8 вывода в Windows-консоли.
 */
public final class ConsoleEncodingUtil {

    private ConsoleEncodingUtil() {
    }

    /**
     * Переводит Windows-консоль в UTF-8 и настраивает stdout/stderr на UTF-8.
     * На других ОС ничего не делает.
     */
    public static void ensureUtf8Console() {
        if (!isWindows()) {
            return;
        }

        try {
            // Кодовая страница 65001 (UTF-8) для текущей консоли.
            new ProcessBuilder("cmd", "/c", "chcp 65001 > nul")
                    .redirectErrorStream(true)
                    .start()
                    .waitFor();

            System.setOut(new PrintStream(
                    new FileOutputStream(FileDescriptor.out),
                    true,
                    StandardCharsets.UTF_8
            ));
            System.setErr(new PrintStream(
                    new FileOutputStream(FileDescriptor.err),
                    true,
                    StandardCharsets.UTF_8
            ));
        } catch (Exception ignored) {
            // Если не получилось, оставляем поведение по умолчанию.
        }
    }

    private static boolean isWindows() {
        return System.getProperty("os.name", "")
                .toLowerCase(Locale.ROOT)
                .contains("win");
    }
}
