package com.study.training.baek;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q_15990 {

    static long[][] d;
    static long mod = 1000000009;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        d = new long[100001][4];

        List<Long> anws = new ArrayList<>();

        for (int i = 1; i <= 100000; i++) {
            if (i - 1 >= 0) {
                d[i][1] = d[i - 1][2] + d[i - 1][3];
                if (i == 1) {
                    d[i][1] = 1;
                }
            }
            if (i - 2 >= 0) {
                d[i][2] = d[i - 2][1] + d[i - 2][3];
                if (i == 2) {
                    d[i][2] = 1;
                }
            }
            if (i - 3 >= 0) {
                d[i][3] = d[i - 3][1] + d[i - 3][2];
                if (i == 3) {
                    d[i][3] = 1;
                }
            }
            d[i][1] %= mod;
            d[i][2] %= mod;
            d[i][3] %= mod;
        }

        for (int i = 0; i < n; i++) {
            int c = sc.nextInt();

            long anw = d[c][1] + d[c][2] + d[c][3];
            anws.add(anw % mod);
        }
        anws.forEach(System.out::println);
    }
}
