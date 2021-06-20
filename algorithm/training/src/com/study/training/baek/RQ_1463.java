package com.study.training.baek;

import java.util.Scanner;

public class RQ_1463 {

    public static int[] d;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        d = new int[n + 1];

        if (n == 1) {
            System.out.println(0);
            return;
        }

        d[1] = 1;
        if (n > 1) {
            d[2] = 1;
        }

        if (n > 2) {
            d[3] = 1;
        }

        for (int i = 4; i <= n; i++) {
            d[i] = d[i - 1] + 1;

            if (i % 2 == 0) {
                d[i] = Math.min(d[i], d[i / 2] + 1);
            }

            if (i % 3 == 0) {
                d[i] = Math.min(d[i], d[i / 3] + 1);
            }
        }
        System.out.println(d[n]);

    }

}
