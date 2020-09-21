package com.study.training.baek;

import java.util.Scanner;

public class Q_1463 {

    static int[] d;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        d = new int[n + 1];

        System.out.println(run(n));

    }

    static int run(int n) {
        if (n == 1) {
            return 0;
        }
        if (d[n] > 0) {
            return d[n];
        }

        d[n] = run(n - 1) + 1;

        if (n % 2 == 0) {
            d[n] = min(d[n], run(n / 2) + 1);
        }

        if (n % 3 == 0) {
            d[n] = min(d[n], run(n / 3) + 1);
        }

        return d[n];
    }

    static int min(int a, int b) {
        if (a > b) {
            return b;
        }

        return a;
    }
}
