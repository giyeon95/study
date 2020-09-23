package com.study.training.baek;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q_15988 {

    static long d[];

    final static long mod = 1000000009L;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int c = sc.nextInt();
        List<Long> anws = new ArrayList<>();

        for (int i = 0; i < c; i++) {

            int n = sc.nextInt();
            d = new long[n + 1];

            anws.add(run(n) % mod);
        }

        anws.forEach(System.out::println);
    }

    static long run(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (n == 3) {
            return 4;
        }

        if (d[n] > 0) {
            return d[n];
        }

        d[n] = (run(n - 1) + run(n - 2) + run(n - 3)) % mod;
        return d[n];
    }
}
