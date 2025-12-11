package ru.urfu.calculations;

import java.util.Scanner;

public class CscscCalculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Расчёт показателей C/SCSC (Earned Value) ===");

        System.out.print("Плановый бюджет проекта (BAC): ");
        double bac = sc.nextDouble();

        System.out.print("Плановая продолжительность проекта (T, в месяцах): ");
        double plannedDuration = sc.nextDouble();

        System.out.print("Текущее время от начала проекта (t_now, в месяцах): ");
        double tNow = sc.nextDouble();

        System.out.print("Фактический процент готовности проекта (%): ");
        double percentComplete = sc.nextDouble();
        double p = percentComplete / 100.0; // перевод в доли

        System.out.print("Фактические затраты на текущий момент (AC): ");
        double ac = sc.nextDouble();

        // Плановая доля готовности (линейное планирование)
        double plannedCompletion = tNow / plannedDuration;

        // Базовые величины
        double pv = bac * plannedCompletion;
        double ev = bac * p;

        double sv = ev - pv; // отклонение по срокам
        double cv = ev - ac; // отклонение по стоимости

        // Индексы SPI и CPI
        Double spi = null;
        if (pv > 0) {
            spi = ev / pv;
        }

        Double cpi = null;
        if (ac > 0) {
            cpi = ev / ac;
        }

        // Прогноз сроков
        Double estimatedDuration = null;
        Double scheduleDelay = null;
        if (spi != null && spi > 0) {
            estimatedDuration = plannedDuration / spi;
            scheduleDelay = estimatedDuration - plannedDuration;
        }

        // Прогноз бюджета
        Double eac = null;
        Double costOverrun = null;
        if (cpi != null && cpi > 0) {
            eac = bac / cpi;
            costOverrun = eac - bac;
        }

        System.out.println();
        System.out.println("===== Входные данные =====");
        System.out.printf("BAC: %.2f%n", bac);
        System.out.printf("T: %.2f%n", plannedDuration);
        System.out.printf("t_now: %.2f%n", tNow);
        System.out.printf("Фактическая готовность: %.2f %% %n", percentComplete);
        System.out.printf("AC: %.2f%n", ac);

        System.out.println();
        System.out.println("===== Промежуточные величины =====");
        System.out.printf("Плановая доля готовности: %.4f%n", plannedCompletion);
        System.out.printf("PV: %.2f%n", pv);
        System.out.printf("EV: %.2f%n", ev);

        System.out.println();
        System.out.println("===== Отклонения =====");
        System.out.printf("SV: %.2f%n", sv);
        System.out.printf("CV: %.2f%n", cv);

        System.out.println();
        System.out.println("===== Индексы выполнения =====");

        if (spi != null) {
            System.out.printf("SPI: %.3f%n", spi);
        } else {
            System.out.println("SPI: нельзя вычислить (PV = 0).");
        }

        if (cpi != null) {
            System.out.printf("CPI: %.3f%n", cpi);
        } else {
            System.out.println("CPI: нельзя вычислить (AC = 0).");
        }

        System.out.println();
        System.out.println("===== Прогноз =====");

        if (estimatedDuration != null) {
            System.out.printf("Оценка длительности проекта: %.2f%n", estimatedDuration);
            System.out.printf("Задержка: %.2f%n", scheduleDelay);
        } else {
            System.out.println("Прогноз сроков невозможен (SPI отсутствует или ≤ 0).");
        }

        if (eac != null) {
            System.out.printf("Оценка итогового бюджета: %.2f%n", eac);
            System.out.printf("Перерасход: %.2f%n", costOverrun);
        } else {
            System.out.println("Прогноз бюджета невозможен (CPI отсутствует или ≤ 0).");
        }

        sc.close();
    }
}

