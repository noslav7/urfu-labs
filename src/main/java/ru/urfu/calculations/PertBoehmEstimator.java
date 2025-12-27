package ru.urfu.calculations;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Scanner;

public class PertBoehmEstimator {

    /**
     * Тип элементарных пакетов работ:
     * UI / Action / Business Object / Business Method
     */
    static class WorkType {
        final String name;
        final int count;
        final double o; // optimistic (Oi)
        final double m; // most likely (Mi)
        final double p; // pessimistic (Pi)

        WorkType(String name, int count, double o, double m, double p) {
            this.name = name;
            this.count = count;
            this.o = o;
            this.m = m;
            this.p = p;
        }

        // (1) Ei = (Pi + 4Mi + Oi) / 6
        double expectedHours() {
            return (p + 4.0 * m + o) / 6.0;
        }

        // (2) СКОi = (Pi - Oi) / 6
        double skoHours() {
            return (p - o) / 6.0;
        }

        // count * Ei
        double totalExpectedHours() {
            return count * expectedHours();
        }

        /**
         * Вклад типа работ в формулу (4):
         * Σ СКО_i^2
         * Поскольку пакеты независимы и одинаковы,
         * получаем: count * (СКОi)^2
         */
        double sumOfSquaresForSKO() {
            double sko = skoHours();
            return count * sko * sko;
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Scanner sc = new Scanner(System.in);
        DecimalFormat df2 = new DecimalFormat("0.00");
        DecimalFormat df3 = new DecimalFormat("0.000");

        System.out.println("PERT + Boehm estimator");
        System.out.println("Ввод значений в часах (десятичный разделитель — точка)");
        System.out.println("Для каждого типа работ введите: count O M P");
        System.out.println();

        WorkType ui  = readWorkType(sc, "UI Form (экран)");
        WorkType act = readWorkType(sc, "Action (обработчик)");
        WorkType bo  = readWorkType(sc, "Business Object (BO)");
        WorkType bm  = readWorkType(sc, "Business Method (BM)");

        // (3) E = Σ Ei
        double E = ui.totalExpectedHours()
                + act.totalExpectedHours()
                + bo.totalExpectedHours()
                + bm.totalExpectedHours();

        // (4) СКО = sqrt( Σ СКО_i^2 )
        double SKO = Math.sqrt(
                ui.sumOfSquaresForSKO()
                        + act.sumOfSquaresForSKO()
                        + bo.sumOfSquaresForSKO()
                        + bm.sumOfSquaresForSKO()
        );

        // (5) E95% = E + 2 * СКО
        double E95 = E + 2.0 * SKO;

        // (6) Δ = (СКО / E) * 100
        double delta = (E == 0.0) ? 0.0 : (SKO / E) * 100.0;

        // (7) EΣ = 4 * E95%
        double Esum = 4.0 * E95;

        // (9) N = EΣ / 132 (чел*мес)
        double N = Esum / 132.0;

        // (8) T = 2.5 * (N)^(1/3)
        double T = 2.5 * Math.cbrt(N);

        // (10) K = N / T
        double K = (T == 0.0) ? 0.0 : (N / T);

        System.out.println();
        System.out.println("=== Результаты ===");
        printWorkType(ui, df2);
        printWorkType(act, df2);
        printWorkType(bo, df2);
        printWorkType(bm, df2);

        System.out.println();
        System.out.println("E (средняя трудоемкость кодирования) = " + df2.format(E) + " чел*час");
        System.out.println("СКО (суммарное)                      = " + df2.format(SKO) + " чел*час");
        System.out.println("E95% (не превысим с 95%)             = " + df2.format(E95) + " чел*час");
        System.out.println("Δ (относительная погрешность)        = " + df2.format(delta) + " %");
        System.out.println("EΣ (общая трудоемкость проекта)      = " + df2.format(Esum) + " чел*час");
        System.out.println("N (трудоемкость)                     = " + df3.format(N) + " чел*мес");
        System.out.println("T (оптимальная длительность)         = " + df3.format(T) + " мес");
        System.out.println("K (средняя численность команды)      = " + df3.format(K) + " чел");
    }

    private static WorkType readWorkType(Scanner sc, String name) {
        System.out.println("[" + name + "] Введите: count O M P");
        int count = readInt(sc);
        double o = readDouble(sc, "  O (optimistic) = ");
        double m = readDouble(sc, "  M (most likely)= ");
        double p = readDouble(sc, "  P (pessimistic)= ");

        if (!(o <= m && m <= p)) {
            System.out.println("  Внимание: обычно ожидается O <= M <= P. Продолжаю расчёт.");
        }
        System.out.println();
        return new WorkType(name, count, o, m, p);
    }

    private static int readInt(Scanner sc) {
        while (true) {
            System.out.print("  count = ");
            try {
                int v = Integer.parseInt(sc.nextLine().trim());
                if (v < 0 || v > Integer.MAX_VALUE) {
                    System.out.println("  Ошибка: значение вне диапазона.");
                    continue;
                }
                return v;
            } catch (NumberFormatException e) {
                System.out.println("  Ошибка: введите целое число.");
            }
        }
    }

    private static double readDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double v = Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
                if (v < 0.0 || v > Double.MAX_VALUE) {
                    System.out.println("  Ошибка: значение вне диапазона.");
                    continue;
                }
                return v;
            } catch (NumberFormatException e) {
                System.out.println("  Ошибка: введите число.");
            }
        }
    }

    private static void printWorkType(WorkType w, DecimalFormat df2) {
        System.out.println("— " + w.name);
        System.out.println("  count = " + w.count);
        System.out.println("  Ei    = " + df2.format(w.expectedHours()) + " чел*час");
        System.out.println("  СКОi  = " + df2.format(w.skoHours()) + " чел*час");
        System.out.println("  count*Ei = " + df2.format(w.totalExpectedHours()) + " чел*час");
    }
}