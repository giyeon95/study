package com.study.training.baek;

import java.util.Arrays;
import java.util.Scanner;

public class Q_16194 {

    static int d[];
    static int p[];


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        p = new int[n + 1];
        d = new int[n + 1];

        Arrays.fill(d, Integer.MAX_VALUE);
        d[0] = 0;

        for (int i = 1; i <= n; i++) {
            p[i] = sc.nextInt();
        }

        System.out.println(getMin(n));

    }

    static int getMin(int n) {
        if (n == 1) {
            return p[n];
        }

        if (d[n] < Integer.MAX_VALUE) {
            return d[n];
        }

        for (int i = 1; i <= n; i++) {
            d[n] = Math.min(d[n], getMin(n - i) + p[i]);
        }

        return d[n];
    }
}
