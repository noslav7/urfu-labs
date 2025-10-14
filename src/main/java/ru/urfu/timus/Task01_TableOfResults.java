package ru.urfu.timus;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Task01_TableOfResults {

    private static final class Rec {
        final int id;
        final int m;
        final int idx;

        Rec(int id, int m, int idx) {
            this.id = id;
            this.m = m;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        List<Rec> a = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int id = fs.nextInt();
            int m = fs.nextInt();
            a.add(new Rec(id, m, i));
        }

        a.sort(Comparator.comparingInt((Rec r) -> r.m).reversed());

        StringBuilder sb = new StringBuilder(n * 12);
        for (Rec r : a) {
            sb.append(r.id).append(' ').append(r.m).append('\n');
        }
        System.out.print(sb);
    }

    private static final class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream is) {
            in = is;
        }
        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }
        int nextInt() throws IOException {
            int c, s = 1, x = 0;

            do c = read();
            while (c <= ' ');

            if (c == '-') {
                s = -1; c = read();
            }
            while (c > ' ') {
                x = x * 10 + (c - '0'); c = read();
            }
            return x * s;
        }
    }
}

/*
1100. Таблица результатов
Ограничение времени: 1.0 секунды
Ограничение памяти: 16 МБ
Старое программное обеспечение для проведения соревнований использует пузырьковую сортировку
для создания таблицы результатов. Однако сейчас команд слишком много, и программное обеспечение
работает слишком медленно. Вас попросили написать программу, которая создаёт такую же таблицу
результатов, как и старое программное обеспечение, но быстро.
Исходные данные
Первая строка входных данных содержит только целое число 1 < N ≤ 150 000 — количество команд.
Каждая из следующих N строк содержит два целых числа: 1 ≤ ID ≤ 107 и 0 ≤ M ≤ 100.
ID — уникальный номер команды, а M — количество решённых этой командой задач.
Результат
Вывод должен содержать N строк с двумя целыми числами ID и M в каждой.
Строки должны идти по убыванию M в порядке, полученном с помощью пузырьковой сортировки (см. ниже).
Пример
https://acm.timus.ru/problem.aspx?space=1&num=1100
Замечания
Пузырьковая сортировка работает следующим образом:
пока существуют A[i] и A[i+1], такие что A[i] < A[i+1],
   обменять(A[i], A[i+1]);
*/
