package com.study.training.baek;

import java.util.Scanner;

public class RQ_10844 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] d = new int[n + 1][10];

        for (int i = 1; i < 10; i++) {
            d[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {

            for (int k = 0; k < 10; k++) {

                if (k - 1 >= 0) {
                    d[i][k] += d[i - 1][k - 1];
                }

                if (k + 1 < 10) {
                    d[i][k] += d[i - 1][k + 1];
                }

                d[i][k] %= 1_000_000_000L;
            }
        }

        long anw = 0;
        for (int i = 0; i < 10; i++) {
            anw += d[n][i];
        }
        System.out.println(anw % 1_000_000_000L);
    }
}
